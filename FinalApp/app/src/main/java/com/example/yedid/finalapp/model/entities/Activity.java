package com.example.yedid.finalapp.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.yedid.finalapp.R;

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

    /**
     * Constructor for the activity
     * @param activityType the activity type of the activity - (what type of activity it is)
     * @param description a quick description of the activity
     * @param state the state of the activity
     * @param beginningDate the date when the activity is planned to begin
     * @param finishDate the date when the activity is planned to end
     * @param price the price of the activity
     * @param businessId the identification number of the activity's business
     */
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

    /**
     *
     * @return returns the activity type
     */
    public ActivityType getActivityType()
    {
        return activityType;
    }

    /**
     * sets the activity type
     * @param activityType
     */
    public void setActivityType(ActivityType activityType)
    {
        this.activityType = activityType;
    }

    /**
     *
     * @return the description of the activity
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * sets the activities description
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     *
     * @return the activity's state
     */
    public String getState()
    {
        return state;
    }

    /**
     * sets the state
     * @param state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     *
     * @return a Gregorian Calander containing the date
     */
    public GregorianCalendar getBeginningDate()
    {
        return beginningDate;
    }

    /**
     * sets the beginning date of the activity
     * @param beginningDate
     */
    public void setBeginningDate(GregorianCalendar beginningDate)
    {
        this.beginningDate = beginningDate;
    }

    /**
     *
     * @return a Gregorian Calander of the ending date of the activity
     */
    public GregorianCalendar getFinishDate()
    {
        return finishDate;
    }

    /**
     *
     * @param finishDate of the activity
     */
    public void setFinishDate(GregorianCalendar finishDate)
    {
        this.finishDate = finishDate;
    }

    /**
     *
     * @return the activity's cost
     */
    public int getPrice()
    {
        return price;
    }

    /**
     *
     * @param price sets the activities cost
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /** returns the id of the activity's business
     *
     * @return the id of the activity's business
     */
    public int getBusinessId()
    {
        return businessId;
    }

    /**
     * sets the business id of the activity
     * @param businessId
     */
    public void setBusinessId(int businessId)
    {
        this.businessId = businessId;
    }


    //functions to help the content provider

    /**
     *
     * @return the columns of the activity parts in the database (for the content provider)
     */
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

    /**
     *  returns a cursor from an ArrayList of activities
     * @param a the arraylist being converted
     * @return the cursor
     */
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

    /**
     * converts a cursor to an arraylist of activities
     * @param cursor the cursor being convrted
     * @return the arraylist
     */
    public static ArrayList<Activity> getListFromCursor(Cursor cursor)
    {
        if(cursor == null)
            return new ArrayList<Activity>();

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

    /**
     * returns the activity in Content Values form
     * @return the content values of the acitivity
     */
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

    /**
     * returns the activity type from a string
     * @param type the string
     * @return the activity type
     */
    public static ActivityType fromStringTOType(String type)
    {
        switch(type)
        {
            case "Hotel vacation package":
                return ActivityType.HOTEL_VACATION_PACKAGE;
            case "Travel agency trip":
                return ActivityType.TRAVEL_AGENCY_TRIP;
            case "Entertainment":
                return ActivityType.ENTERTAINMENT;
            case "Airline":
                return ActivityType.AIRLINE;
            default:
                throw new IllegalArgumentException("You must choose an Activity Type");
        }
    }

}