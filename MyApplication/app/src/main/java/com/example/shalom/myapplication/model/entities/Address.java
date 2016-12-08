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

    public Address(String state, String city, String street)
    {
        this.state = state;
        this.city = city;
        this.street = street;
    }

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
