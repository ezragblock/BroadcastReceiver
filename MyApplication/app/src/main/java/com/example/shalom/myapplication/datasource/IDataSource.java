package com.example.shalom.myapplication.datasource;

import com.example.shalom.myapplication.entities.Activity;
import com.example.shalom.myapplication.entities.Business;
import com.example.shalom.myapplication.entities.User;

import java.util.ArrayList;

/**
 * Created by Shalom on 11/29/2016.
 */

public interface IDataSource
{
    void addUser(User user);
    void addActivity(Activity activity);
    void addBusiness(Business user);

    public ArrayList<Business> getBusinesses();
    public ArrayList<com.example.shalom.myapplication.entities.Activity> getActivities();
    public ArrayList<User> getUsers();
}
