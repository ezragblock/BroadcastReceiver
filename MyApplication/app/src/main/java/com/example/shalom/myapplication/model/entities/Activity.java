package com.example.shalom.myapplication.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Activity {
    ActivityType activityType;
    String description;
    String state;
    Date beginningDate;
    Date finishDate;
    int price;
    int businessId;


    public Activity(ActivityType activityType, String description, String state, Date beginningDate, Date finishDate, int price, int businessId)
    {
        this.activityType = activityType;
        this.description = description;
        this.state = state;
        this.beginningDate = beginningDate;
        this.finishDate = finishDate;
        this.price = price;
        this.businessId = businessId;
    }

    public ActivityType getActivityType()
    {
        return activityType;
    }

    public void setActivityType(ActivityType activityType)
    {
        this.activityType = activityType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public Date getBeginningDate()
    {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate)
    {
        this.beginningDate = beginningDate;
    }

    public Date getFinishDate()
    {
        return finishDate;
    }

    public void setFinishDate(Date finishDate)
    {
        this.finishDate = finishDate;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(int businessId)
    {
        this.businessId = businessId;
    }


    //functions to help the content provider
    public static String [] COLUMNS()
    {
        String[] COLUMNS = {
                "ActivityType",
                "description",
                "state",
                "beginningDate",
                "finishDate",
                "price",
                "businessId"
        };
        return COLUMNS;
    }

    public static Cursor getCursorFromList(ArrayList<Activity> a)
    {
        MatrixCursor c = new MatrixCursor(Activity.COLUMNS());

        for (Activity activity:a)
        {
            ArrayList<String> temp = new ArrayList<>();
            try {
                temp.add(String.valueOf(activity.getActivityType()));
                temp.add(activity.getDescription());
                temp.add(activity.getState());
                temp.add(String.valueOf(activity.getBeginningDate()));
                temp.add(String.valueOf(activity.getFinishDate()));
                temp.add(String.valueOf(activity.getPrice()));
                temp.add(String.valueOf(activity.getBusinessId()));

                c.addRow(temp);
                return c;
            }
            catch (Exception e)//we dont know yet what kind of exception can happened hear (not yet tested)
            {
                return null;
            }
        }
        return null;
    }

    public static ArrayList<Activity> getListFromCursor(Cursor cursor)
    {
        if(cursor == null)
            return new ArrayList<Activity>();

        if(!Activity.COLUMNS().equals(cursor.getColumnNames()))
            throw new IllegalArgumentException("The columns must match the entity's paramters");

        ArrayList<Activity> a = new ArrayList<>();//this is the list that we will return with all the activities
        cursor.moveToFirst();

        do
        {
            a.add(new Activity(ActivityType.valueOf(cursor.getString(cursor.getColumnIndex(COLUMNS()[0]))),
                               cursor.getString(cursor.getColumnIndex(COLUMNS()[1])),
                               cursor.getString(cursor.getColumnIndex(COLUMNS()[2])),
                               new Date(cursor.getInt(cursor.getColumnIndex(COLUMNS()[3]))),
                               new Date(cursor.getInt(cursor.getColumnIndex(COLUMNS()[4]))),
                               cursor.getInt(cursor.getColumnIndex(COLUMNS()[5])),
                               cursor.getInt(cursor.getColumnIndex(COLUMNS()[6]))));

        }while (cursor.moveToNext());
        return a;
    }

}
