package com.example.shalom.myapplication.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.shalom.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Activity
{
    ActivityType activityType;
    String description;
    String state;
    GregorianCalendar beginningDate;
    GregorianCalendar finishDate;
    int price;
    int businessId;


    public Activity(ActivityType activityType, String description, String state, GregorianCalendar beginningDate, GregorianCalendar finishDate, int price, int businessId)
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

    public GregorianCalendar getBeginningDate()
    {
        return beginningDate;
    }

    public void setBeginningDate(GregorianCalendar beginningDate)
    {
        this.beginningDate = beginningDate;
    }

    public GregorianCalendar getFinishDate()
    {
        return finishDate;
    }

    public void setFinishDate(GregorianCalendar finishDate)
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
                "beginningDay",
                "beginningMonth",
                "beginningYear",
                "finishDay",
                "finishMonth",
                "finishYear",
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
                               new GregorianCalendar(cursor.getInt(cursor.getColumnIndex(COLUMNS()[3])),
                                       cursor.getInt(cursor.getColumnIndex(COLUMNS()[4])),
                                       cursor.getInt(cursor.getColumnIndex(COLUMNS()[5]))),
                               new GregorianCalendar(cursor.getInt(cursor.getColumnIndex(COLUMNS()[6])),
                                       cursor.getInt(cursor.getColumnIndex(COLUMNS()[7])),
                                       cursor.getInt(cursor.getColumnIndex(COLUMNS()[8]))),
                               cursor.getInt(cursor.getColumnIndex(COLUMNS()[9])),
                               cursor.getInt(cursor.getColumnIndex(COLUMNS()[10]))));

        }while (cursor.moveToNext());
        return a;
    }

    public final ContentValues getContentValue()
    {//this function convert this activity to content value
        ContentValues values = new ContentValues();

        values.put("activitytype",this.activityType.toString());
        values.put("description",this.description);
        values.put("state",this.state);
        values.put("beginningday", this.beginningDate.get(Calendar.DAY_OF_MONTH));
        values.put("beginningmonth",this.beginningDate.get(Calendar.MONTH));
        values.put("beginningyear", this.beginningDate.get(Calendar.YEAR));
        values.put("finishingday", this.finishDate.get(Calendar.DAY_OF_MONTH));
        values.put("finishingmonth",this.finishDate.get(Calendar.MONTH));
        values.put("finishingyear", this.finishDate.get(Calendar.YEAR));
        values.put("price",this.price);
        values.put("businessId",this.businessId);

        return values;
    }

    public static ActivityType fromStringTOType(String type)
    {
        switch(type)
        {
            case "HOTEL_VACATION_PACKAGE":
                return ActivityType.HOTEL_VACATION_PACKAGE;
            case "TRAVEL_AGENCY_TRIP":
                return ActivityType.TRAVEL_AGENCY_TRIP;
            case "ENTERTAINMENT":
                return ActivityType.ENTERTAINMENT;
            case "AIRLINE":
                return ActivityType.AIRLINE;
            default:
                throw new IllegalArgumentException("You must choose an Activity Type");
        }
    }


}
