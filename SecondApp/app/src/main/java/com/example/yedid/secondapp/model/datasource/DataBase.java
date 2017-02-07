package com.example.yedid.secondapp.model.datasource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.yedid.secondapp.model.entities.ActivityType;
import com.example.yedid.secondapp.model.entities.Business;
import com.example.yedid.secondapp.model.entities.Activity;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yedid on 1/26/2017.
 */

public class DataBase implements IDS_manager {

    private ArrayList<Business> businesses;
    private ArrayList<Activity> activities;
    Context context;
    public final String PROVIDER_NAME = "content://com.example.shalom.myapplication";

    /**
     * constructor for the database
     * @param context the context from which it was created
     */
    public DataBase(Context context)
    {
        this.context = context;

        activities = new ArrayList<Activity>();
        businesses = new ArrayList<Business>();

        updateList();
    }

    /**
     *
     * @return a list of all the businesses in the database
     */
    @Override
    public List<Business> getBusinesses() {
        return (ArrayList)businesses.clone();
    }

    /**
     *
     * @return a list of all the activities in the database with
     */
    @Override
    public List<Activity> getActivities() {
        ArrayList<Activity> a = new ArrayList<>();

        //I choose only the travels activities
        for(Activity activity:activities)
        {
            if(activity.getActivityType().equals(ActivityType.TRAVEL_AGENCY_TRIP))
                a.add(activity);
        }
        return a;
    }

    /**
     * This method is in charge of updating the list from the server
     */
    @Override
    public void updateList() {
        if(context == null)
            return;

        //update the activities
        (new AsyncTask<String,String,ArrayList<Activity>>(){
            /**
             * Here the activity list is taken from the server for update
             * @param params
             * @return the arraylist of activities
             */
            @Override
            protected ArrayList<Activity> doInBackground(String... params) {
                ArrayList<Activity> a = new ArrayList<Activity>();
                try
                {
                    Uri url = Uri.parse(PROVIDER_NAME + "/activities");
                    Cursor cursor = context.getContentResolver().acquireContentProviderClient(url).query(url,null,null,null,null);
                    a = Activity.getListFromCursor(cursor);

                    if(a == null)
                        return new ArrayList<Activity>();

                    return a;

                }catch (Exception e)
                {
                    publishProgress(e.getMessage());
                    return a;
                }
            }

            /**
             * puts the list of activities from the server into the activities list
             * @param a
             */
            @Override
            protected void onPostExecute(ArrayList<Activity> a) {
                Log.i("LIST",a.toString());
                activities = a;
            }
        }).execute();

        //update the businesses
        (new AsyncTask<String,String,ArrayList<Business>>(){
            @Override
            protected ArrayList<Business> doInBackground(String... params) {
                ArrayList<Business> b = new ArrayList<Business>();
                try{
                    Uri url = Uri.parse(PROVIDER_NAME + "/businesses");
                    Cursor cursor = context.getContentResolver().acquireContentProviderClient(url).query(url,null,null,null,null);
                    b = Business.getListFromCursor(cursor);

                    if(b == null)
                        return new ArrayList<Business>();

                    return b;

                }catch (Exception e)
                {
                    publishProgress(e.getMessage());
                    return b;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<Business> b) {
                businesses = b;
            }
        }).execute();
    }
}
