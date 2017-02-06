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
    /**
     * Method in charge of adding a user which is in values to the database to the database
     * @param values the user being added to the database
     * @throws IOException throws an error in case the adding process failed
     */
    void addUser(ContentValues values) throws IOException;

    /**
     * Method in charge of adding a business to the database which is in values to the database
     * @param values the business being added to the database
     * @throws IOException throws an error in case the adding process failed
     */
    void addBusiness(ContentValues values) throws IOException;

    /**
     * Method in charge of adding an activity to the database which is in values to the database
     * @param values the activity being added to the database
     * @throws IOException throws an error in case the adding process failed
     */
    void addActivity(ContentValues values) throws IOException;

    /**
     * Method in charge of returning the activities from the database
     * @return a cursor containing the activities from the database
     * @throws Exception if the pulling of the activities fails
     */
    Cursor getActivities() throws Exception;

    /**
     * Method in charge of returning the users from the database
     * @return a cursor containing the users from the database
     * @throws Exception if the pulling of the users fails
     */
    Cursor getUsers() throws Exception;

    /**
     * Method in charge of returning the businesses from the database
     * @return a cursor containing the businesses from the database
     * @throws Exception if the pulling of the businesses fails
     */
    Cursor getBusinesses() throws Exception;

    /**
     * Method in charge of returning whether the activities list in the database has been updated
     * @return whether or not the database has been updated
     * @throws Exception if the check fails
     */
    Boolean isActivitiesUpdated()throws Exception;

    /**
     * Method in charge of returning whether the users list in the database has been updated
     * @return whether or not the database has been updated
     * @throws Exception if the check fails
     */
    Boolean isUsersUpdated()throws Exception;

    /**
     * Method in charge of returning whether the businesses list in the database has been updated
     * @return whether or not the database has been updated
     * @throws Exception if the check fails
     */
    Boolean isBusinessesUpdated()throws Exception;
}
