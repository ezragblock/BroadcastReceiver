package com.example.yedid.secondapp.model.datasource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
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

    static ArrayList<Business> businesses = new ArrayList<Business>();
    static ArrayList<Activity> activities = new ArrayList<Activity>();
    Context context;
    String PROVIDER_NAME = "content://" + "com.example.shalom.myapplication";

    public DataBase(Context context)
    {
        this.context = context;

        activities = new ArrayList<Activity>();
        businesses = new ArrayList<Business>();

        updateList();
    }


    @Override
    public List<Business> getBusinesses() {
        return (ArrayList)businesses.clone();
    }

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

    @Override
    public void updateList() {
        if(context == null)
            return;

        (new AsyncTask<String,String,String>(){
            @Override
            protected String doInBackground(String... params) {
                try
                {
                    Cursor cursor = context.getContentResolver().query(Uri.parse(PROVIDER_NAME + "/businesses"),null,null,null,null);
                    ArrayList<Business> b = Business.getListFromCursor(cursor);
                    ArrayList<Activity> a = com.example.yedid.secondapp.model.entities.Activity.getListFromCursor(context.getContentResolver().query(Uri.parse(PROVIDER_NAME + "/activities"),null,null,null,null));

                    if(a == null || b == null)
                        throw new Exception("Erorr while updating");

                    activities = a;
                    businesses = b;

                        return "Succesfull update,refresh page to watch";
                }
                catch (Exception e)
                {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                //tell the user the message
            }
        }).execute();
    }
}
