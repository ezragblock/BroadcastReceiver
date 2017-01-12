package com.example.shalom.myapplication.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.ActivityType;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.util.Patterns.WEB_URL;
import static java.lang.System.in;

/**
 * Created by yedid on 1/8/2017.
 */

public class SQLDataBase implements IDataSource
{
    private static String GET(String url) throws Exception
    {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK)
        { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            // print result
            return response.toString();
        }
        else
        {
            return "";
        }
    }

    private static String POST(String url, Map<String,Object> params) throws IOException
    {
        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet())
        {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else
            return "";
    }


    @Override
    public void addUser(ContentValues values) throws IOException
    {
        try
        {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put(User.COLUMNS()[0], values.getAsString(User.COLUMNS()[0]));
            params.put(User.COLUMNS()[1], values.getAsString(User.COLUMNS()[1]));
            String results = POST(WEB_URL + "UserToServer.php", params);
            if(results.equals(""))
            {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error"))
            {
                throw new Exception(results.substring(5));
            }
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void addBusiness(ContentValues values) throws IOException
    {
        try
        {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put(Business.COLUMNS()[0], values.getAsInteger(Business.COLUMNS()[0]));
            params.put(Business.COLUMNS()[1], values.getAsString(Business.COLUMNS()[1]));
            params.put(Business.COLUMNS()[2], values.getAsString(Business.COLUMNS()[2]));
            params.put(Business.COLUMNS()[3], values.getAsString(Business.COLUMNS()[3]));
            params.put(Business.COLUMNS()[4], values.getAsString(Business.COLUMNS()[4]));
            params.put(Business.COLUMNS()[5], values.getAsString(Business.COLUMNS()[5]));
            params.put(Business.COLUMNS()[6], values.getAsString(Business.COLUMNS()[6]));
            params.put(Business.COLUMNS()[7], values.getAsString(Business.COLUMNS()[7]));
            String results = POST(WEB_URL + "BusinessToServer.php", params);
            if(results.equals(""))
            {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error"))
            {
                throw new Exception(results.substring(5));
            }
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void addActivity(ContentValues values) throws IOException
    {
        try
        {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put(Activity.COLUMNS()[0], values.getAsString(Activity.COLUMNS()[0]));
            params.put(Activity.COLUMNS()[1], values.getAsString(Activity.COLUMNS()[1]));
            params.put(Activity.COLUMNS()[2], values.getAsString(Activity.COLUMNS()[2]));
            params.put(Activity.COLUMNS()[3], values.getAsInteger(Activity.COLUMNS()[3]));
            params.put(Activity.COLUMNS()[4], values.getAsInteger(Activity.COLUMNS()[4]));
            params.put(Activity.COLUMNS()[5], values.getAsInteger(Activity.COLUMNS()[5]));
            params.put(Activity.COLUMNS()[6], values.getAsInteger(Activity.COLUMNS()[6]));
            params.put(Activity.COLUMNS()[7], values.getAsInteger(Activity.COLUMNS()[7]));
            params.put(Activity.COLUMNS()[8], values.getAsInteger(Activity.COLUMNS()[8]));
            params.put(Activity.COLUMNS()[9], values.getAsInteger(Activity.COLUMNS()[9]));
            params.put(Activity.COLUMNS()[10], values.getAsInteger(Activity.COLUMNS()[10]));
            String results = POST(WEB_URL + "ActivityToServer.php", params);
            if(results.equals(""))
            {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error"))
            {
                throw new Exception(results.substring(5));
            }
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Cursor getActivities() throws Exception
    {
        MatrixCursor agenciesCursor = new MatrixCursor(Activity.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/ActivityFromServer.php")).getJSONArray("activities");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject activities = array.getJSONObject(i);
            agenciesCursor.addRow(new Object[]
             {
                    activities.getString(Activity.COLUMNS()[0]),
                    activities.getString(Activity.COLUMNS()[1]),
                    activities.getString(Activity.COLUMNS()[2]),
                    String.valueOf(new GregorianCalendar(activities.getInt(Activity.COLUMNS()[3]),
                                                         activities.getInt(Activity.COLUMNS()[4]),
                                                         activities.getInt(Activity.COLUMNS()[5]))),
                    String.valueOf(new GregorianCalendar(activities.getInt(Activity.COLUMNS()[6]),
                                                         activities.getInt(Activity.COLUMNS()[7]),
                                                         activities.getInt(Activity.COLUMNS()[8]))),
                    activities.getInt(Activity.COLUMNS()[9]),
                    activities.getInt(Activity.COLUMNS()[10])
             });
        }
        return agenciesCursor;
    }

    @Override
    public Cursor getUsers() throws Exception
    {
        MatrixCursor usersCursor = new MatrixCursor(User.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/UserFromServer.php")).getJSONArray("users");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject user = array.getJSONObject(i);
            usersCursor.addRow(new Object[]
            {
                    user.getString(User.COLUMNS()[0]),
                    user.getString(User.COLUMNS()[1])
            });
        }
        return usersCursor;
    }

    @Override
    public Cursor getBusinesses() throws Exception
    {
        MatrixCursor buisnessCursor = new MatrixCursor(Business.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/BusinessFromServer.php")).getJSONArray("buisnesses");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject buisnesses = array.getJSONObject(i);
            buisnessCursor.addRow(new Object[]
                    {
                            buisnesses.getInt(Business.COLUMNS()[0]),
                            buisnesses.getString(Business.COLUMNS()[1]),
                            buisnesses.getString(Business.COLUMNS()[2]),
                            buisnesses.getString(Business.COLUMNS()[3]),
                            buisnesses.getString(Business.COLUMNS()[4]),
                            buisnesses.getString(Business.COLUMNS()[5]),
                            buisnesses.getString(Business.COLUMNS()[6]),
                            buisnesses.getString(Business.COLUMNS()[7])
                    });
        }
        return buisnessCursor;
    }

    @Override
    public Boolean isActivitiesUpdated()
    {
        return null;
    }

    @Override
    public Boolean isUsersUpdated()
    {
        return null;
    }

    @Override
    public Boolean isBusinessesUpdated()
    {
        return null;
    }
}
