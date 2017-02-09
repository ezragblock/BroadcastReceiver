package com.example.yedid.finalapp.model.entities;

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

    /**
     *
     * @return address in string format
     */
    @Override
    public String toString() {
        return street + "," + city+ "," + state;
    }
}
