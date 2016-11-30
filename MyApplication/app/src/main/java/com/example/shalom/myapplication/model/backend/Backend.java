package com.example.shalom.myapplication.model.backend;

import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

/**
 * Created by yedid on 11/29/2016.
 */

public class Backend
{
    IDataSource theDataSource;
    Backend()
    {
        theDataSource = FactoryDataSource.getDataBase();
    }

    public void addUser(User user)
    {
        //check if there is a user with that username already
        theDataSource.addUser(user);
    }
    public void addBusiness(Business business)
    {
        //make sure the id is unique
        theDataSource.addBusiness(business);
    }
    public void addActivity(Activity activity)
    {
        //make sure the business id is of a existent business
        theDataSource.addActivity(activity);
    }

    public ArrayList<User> getAllUsers()
    {
        return theDataSource.getUsers();
    }

    public ArrayList<Business> getAllBusinesses()
    {
        return theDataSource.getBusinesses();
    }

    public ArrayList<Activity> getAllActivites()
    {
        return theDataSource.getActivities();
    }


}
