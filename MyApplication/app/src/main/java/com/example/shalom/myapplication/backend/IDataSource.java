package com.example.shalom.myapplication.backend;

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

    ArrayList<Activity> getActivities();
    ArrayList<User> getUsers();
    ArrayList<Business> getBusinesses();
}
