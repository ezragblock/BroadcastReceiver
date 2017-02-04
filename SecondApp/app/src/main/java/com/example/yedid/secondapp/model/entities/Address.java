package com.example.yedid.secondapp.model.entities;

import java.io.Serializable;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return street + "," + city+ "," + state;
    }
}
