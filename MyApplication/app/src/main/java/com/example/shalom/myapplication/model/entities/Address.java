package com.example.shalom.myapplication.model.entities;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Address
{
    public String state;
    public String city;
    public String street;

    /**
     * Constructor of an address
     * @param state
     * @param city
     * @param street
     */
    public Address(String state, String city, String street)
    {
        this.state = state;
        this.city = city;
        this.street = street;
    }

    /**
     *
     * @return string array of all the columns in order for the database
     */
    public static String [] COLUMNS()
    {
        String[] COLUMNS = {
              "state",
              "city",
              "street"
        };
        return COLUMNS;
    }

}
