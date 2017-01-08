package com.example.shalom.myapplication.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.ActivityType;
import com.example.shalom.myapplication.model.entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.util.Patterns.WEB_URL;

/**
 * Created by yedid on 1/8/2017.
 */

public class SQLDataBase implements IDataSource
{
    @Override
    public void addUser(ContentValues values)
    {

    }

    @Override
    public void addBusiness(ContentValues values)
    {

    }

    @Override
    public void addActivity(ContentValues values)
    {

    }

    @Override
    public Cursor getActivities()
    {
        MatrixCursor agenciesCursor = new MatrixCursor(Activity.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/getActivities.php")).getJSONArray("activities");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject activities = array.getJSONObject(i);
            agenciesCursor.addRow(new Object[]{
                    activities.getString(Activity.COLUMNS()[0]),
                    activities.getString(Activity.COLUMNS()[1]),
                    activities.getString(Activity.COLUMNS()[2]),
                    activities.getString(Activity.COLUMNS()[3]),
                    activities.getString(Activity.COLUMNS()[4]),
                    activities.getInt(Activity.COLUMNS()[5]),
                    activities.getString(Activity.COLUMNS()[6]),
                    activities.getString(Activity.COLUMNS()[7])
            });
        }
        return agenciesCursor;
    }

    @Override
    public Cursor getUsers()
    {
        MatrixCursor agenciesCursor = new MatrixCursor(User.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/getUsers.php")).getJSONArray("users");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject agency = array.getJSONObject(i);
            agenciesCursor.addRow(new Object[]{
                    agency.getInt(Activity.COLUMNS()[0]),
                    agency.getString(Activity.COLUMNS()[1]),
                    agency.getString(Activity.COLUMNS()[2]),
                    agency.getString(Activity.COLUMNS()[3]),
                    agency.getString(Activity.COLUMNS()[4]),
                    agency.getInt(Activity.COLUMNS()[5]),
                    agency.getString(Activity.COLUMNS()[6]),
                    agency.getString(Activity.COLUMNS()[7])
            });
        }
        return agenciesCursor;
    }

    @Override
    public Cursor getBusinesses()
    {
        MatrixCursor agenciesCursor = new MatrixCursor(Activity.COLUMNS());
        JSONArray array = new JSONObject(GET(WEB_URL + "/getActivities.php")).getJSONArray("activities");
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject agency = array.getJSONObject(i);
            agenciesCursor.addRow(new Object[]{
                    agency.getInt("id"),
                    agency.getString("name"),
                    agency.getString("country"),
                    agency.getString("city"),
                    agency.getString("street"),
                    agency.getInt("house_no"),
                    agency.getString("phone_number"),
                    agency.getString("email")
            });
        }
        return agenciesCursor;
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
