package com.example.shalom.myapplication.model.datasource;

import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;

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
                                    values.getAsInteger("businessId"));
    }

    public Cursor getBusinesses()
    {
        //return (ArrayList<Business>)businesses.clone();
    } ///צריך להחזיר cursor

    public Cursor getActivities()
    {
        //return (ArrayList<Activity>)activities.clone();
    }

    public Cursor getUsers() {
        //return (ArrayList<User>)users.clone();
    }
}
