package com.example.shalom.myapplication.model.datasource;

import com.example.shalom.myapplication.model.backend.IDataSource;
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

    public void addUser(User user)
    {
        users.add(user);
    }

    public void addBusiness(Business b)
    {
        businesses.add(b);
    }

    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public ArrayList<Business> getBusinesses() {
        return (ArrayList<Business>)businesses.clone();
    }

    public ArrayList<Activity> getActivities()
    {
        return (ArrayList<Activity>)activities.clone();
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>)users.clone();
    }
}
