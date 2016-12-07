package com.example.shalom.myapplication.model.datasource;

import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.ActivityType;
import com.example.shalom.myapplication.model.entities.Address;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;
import com.example.shalom.myapplication.model.entities.Activity;

import java.util.ArrayList;

/**
 * Created by Shalom on 11/26/2016.
 */

public class ListDataSource implements IDataSource
{
    public ArrayList<Business> businesses;
    public ArrayList<Activity> activities;
    public ArrayList<User> users;

    private boolean UserUpdate;
    private boolean activityUpdate;
    private boolean businessUpdate;

    public void addUser(ContentValues user)
    {
        UserUpdate=true;
        users.add(new User(user.getAsInteger("userNum"),
                           user.getAsString("username"),
                           user.getAsString("password")));
    }

    public void addBusiness(ContentValues values)
    {
        businessUpdate=true;
        businesses.add(new Business(values.getAsInteger("id"),
                                    values.getAsString("name"),
                                    new Address(values.getAsString("state"),
                                                values.getAsString("city"),
                                                values.getAsString("street")),
                                    values.getAsString("telephoneNumber"),
                                    values.getAsString("telephoneNumber"),
                                    values.getAsString("websiteAddress")));
    }

    public void addActivity(ContentValues values)
    {
        activityUpdate=true;
        activities.add(new Activity(ActivityType.valueOf(values.getAsString("activityType")),
                                    values.getAsString("description"),
                                    values.getAsString("state"),
                                    new Date(values.getAsString("beginingDate")),
                                    new Date(values.getAsString("endDate")),
                                    values.getAsInteger("price"),
                                    values.getAsInteger("businessId")));
    }

    //this functions convert the data from the list to matrixCursor for the contentProvider
    public Cursor getBusinesses()
    {
        MatrixCursor c = new MatrixCursor(Business.COLUMNS());

        for (Business business:businesses)
        {
            ArrayList<String> temp = new ArrayList<>();
            try
            {
                temp.add(String.valueOf(business.getId()));
                temp.add(business.getName());
                temp.add(business.getTelephoneNumber());
                temp.add(business.getEmail());
                temp.add(business.getWebsiteAddress());

                c.addRow(temp);
                return c;
            }
            catch (Exception e)//we dont know yet what kind of exception can happened hear (not yet tested)
            {
                return null;
            }
        }
        return null;
    }

    public Cursor getActivities()
    {
        MatrixCursor c = new MatrixCursor(Activity.COLUMNS());

        for (Activity activity:activities)
        {
            ArrayList<String> temp = new ArrayList<>();
            try {
                temp.add(String.valueOf(activity.getActivityType()));
                temp.add(activity.getDescription());
                temp.add(activity.getState());
                temp.add(String.valueOf(activity.getBeginningDate()));
                temp.add(String.valueOf(activity.getFinishDate()));
                temp.add(String.valueOf(activity.getPrice()));
                temp.add(String.valueOf(activity.getBusinessId()));

                c.addRow(temp);
                return c;
            }
            catch (Exception e)//we dont know yet what kind of exception can happened hear (not yet tested)
            {
                return null;
            }
        }
        return null;
    }

    public Cursor getUsers()
    {
        MatrixCursor c = new MatrixCursor(Activity.COLUMNS());

        for (User user:users)
        {
            ArrayList<String> temp = new ArrayList<>();
            try
            {
                temp.add(String.valueOf(user.getUserNum()));
                temp.add(user.getUsername());
                temp.add(user.getPassword());

                c.addRow(temp);
                return c;
            }
            catch (Exception e)//we dont know yet what kind of exception can happened hear (not yet tested)
            {
                return null;
            }
        }
        return null;
    }
}
