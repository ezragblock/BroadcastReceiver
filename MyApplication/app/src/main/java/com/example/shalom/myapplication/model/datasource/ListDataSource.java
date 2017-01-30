package com.example.shalom.myapplication.model.datasource;

import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.entities.ActivityType;
import com.example.shalom.myapplication.model.entities.Address;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;
import com.example.shalom.myapplication.model.entities.Activity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Shalom on 11/26/2016.
 */

public class ListDataSource implements IDataSource
{
    public ArrayList<Business> businesses=new ArrayList<Business>();
    public ArrayList<Activity> activities=new ArrayList<Activity>();
    public ArrayList<User> users = new ArrayList<User>();

    private boolean UserUpdate = false;
    private boolean activityUpdate = false;
    private boolean businessUpdate = false;

    public void addUser(ContentValues user)
    {
        UserUpdate=true;
        users.add(new User(user.getAsString("username"),
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
                                    values.getAsString("email"),
                                    values.getAsString("websiteAddress")));
    }

    public void addActivity(ContentValues values)
    {
        activityUpdate=true;
        activities.add(new Activity(ActivityType.valueOf(values.getAsString("activitytype")),
                                    values.getAsString("description"),
                                    values.getAsString("state"),
                                    new GregorianCalendar(values.getAsInteger("beginningday"),values.getAsInteger("beginningmonth"), values.getAsInteger("beginningyear")),
                                    new GregorianCalendar(values.getAsInteger("finishingday"),values.getAsInteger("finishingmonth"),values.getAsInteger("finishingyear")),
                                    values.getAsInteger("price"),
                                    values.getAsInteger("businessId")));
    }
    //this functions convert the data from the list to matrixCursor for the contentProvider
    public Cursor getBusinesses()
    {
        businessUpdate = false;
        return Business.getCursorFromList(businesses);
    }

    public Cursor getActivities()
    {
        activityUpdate = false;
        return Activity.getCursorFromList(activities);
    }

    public Cursor getUsers()
    {
        UserUpdate = false;
        return User.getCursorFromList(users);
    }

    public Boolean isActivitiesUpdated()
    {
        return !activityUpdate;
    }

    public Boolean isUsersUpdated()
    {
        return !UserUpdate;
    }

    public Boolean isBusinessesUpdated()
    {
        return !businessUpdate;
    }
    /////////////////חשוב מאוד אני מניח שכאשר מוסיפים אובייקט הרשימה לא מעודכנת וכאשר מושכים את הרשימה היא מתעדכנת
}
