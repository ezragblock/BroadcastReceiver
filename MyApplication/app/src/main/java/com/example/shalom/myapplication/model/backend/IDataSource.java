package com.example.shalom.myapplication.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shalom on 11/29/2016.
 */

public interface IDataSource
{
    void addUser(ContentValues values) throws IOException;
    void addBusiness(ContentValues values) throws IOException;
    void addActivity(ContentValues values) throws IOException;

    Cursor getActivities() throws Exception;
    Cursor getUsers() throws Exception;
    Cursor getBusinesses() throws Exception;

    Boolean isActivitiesUpdated();
    Boolean isUsersUpdated();
    Boolean isBusinessesUpdated();
}
