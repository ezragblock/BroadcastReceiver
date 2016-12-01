package com.example.shalom.myapplication.model.backend;

import android.content.ContentValues;

import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

/**
 * Created by Shalom on 11/29/2016.
 */

public interface IDataSource
{
    Boolean add(ContentValues values);

    ArrayList<Activity> getActivities();
    ArrayList<User> getUsers();
    ArrayList<Business> getBusinesses();
}
