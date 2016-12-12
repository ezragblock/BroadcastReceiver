package com.example.shalom.myapplication.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

/**
 * Created by Shalom on 11/29/2016.
 */

public interface IDataSource
{
    void addUser(ContentValues values);
    void addBusiness(ContentValues values);
    void addActivity(ContentValues values);

    Cursor getActivities();
    Cursor getUsers();
    Cursor getBusinesses();

    Boolean isUpdated();
}
