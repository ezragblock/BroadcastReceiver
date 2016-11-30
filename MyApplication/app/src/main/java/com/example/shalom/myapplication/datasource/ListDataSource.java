package com.example.shalom.myapplication.datasource;

import com.example.shalom.myapplication.entities.Business;
import com.example.shalom.myapplication.entities.User;

import java.util.ArrayList;

/**
 * Created by Shalom on 11/26/2016.
 */

public class ListDataSource implements IDataSource
{
    public ArrayList<Business> businesses;
    public ArrayList<com.example.shalom.myapplication.entities.Activity> activities;
    public ArrayList<User> users;

    public void addUser(User user)
    {
        users.add(user);
    }

    public void addBusiness(Business b)
    {
        businesses.add(b);
    }

    public void addActivity(com.example.shalom.myapplication.entities.Activity activity)
    {
        activities.add(activity);
    }

    public ArrayList<Business> getBusinesses() {
        return (ArrayList<Business>)businesses.clone();
    }

    public ArrayList<com.example.shalom.myapplication.entities.Activity> getActivities()
    {
        return (ArrayList<com.example.shalom.myapplication.entities.Activity>)activities.clone();
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>)users.clone();
    }
}
