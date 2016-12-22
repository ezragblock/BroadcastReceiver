package com.example.shalom.myapplication.model.entities;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Business
{
    int id;
    String name;
    Address address;
    String telephoneNumber;
    String email;
    String websiteAddress;

    public Business(int id, String name, Address address, String telephoneNumber, String email, String websiteAddress)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.websiteAddress = websiteAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public static String[] COLUMNS()
    {
        ArrayList<String> c = new ArrayList<String>();
        c.add(1,"id");
        c.add(2,"name");
        c.add(3,"telephoneNumber");
        c.add(4,"email");
        c.add(5,"websiteAddress");

        for (int i = 6;i<Address.COLUMNS().length + 6;i++)
        {
            c.add(i,Address.COLUMNS()[i - 6]);
        }

        String[] COLOMNS = (String[])c.toArray();
        return COLOMNS;
    }////////////// יש צורך לתקן פונקציה זו ולשרשר לה את ההמשך כלור לשים בה את משתני הכתובת


    public static Cursor getCursorFromList(ArrayList<Business> businesses)
    {
        MatrixCursor c = new MatrixCursor(Business.COLUMNS());

        for (Business business:businesses)
        {
            ArrayList<String> temp = new ArrayList<>();
            try
            {
                temp.add(String.valueOf(business.getId()));
                temp.add(business.getName());
                temp.add(business.getTelephoneNumber());
                temp.add(business.getEmail());
                temp.add(business.getWebsiteAddress());
                temp.add(business.getAddress().state);
                temp.add(business.getAddress().state);
                temp.add(business.getAddress().street);

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

    public static ArrayList<Business> getListFromCursor(Cursor cursor)
    {
        if(cursor == null)
            return new ArrayList<Business>();

        if(!Activity.COLUMNS().equals(cursor.getColumnNames()))
            throw new IllegalArgumentException("The columns must match the entity's paramters");

        ArrayList<Business> a = new ArrayList<>();//this is the list that we will return with all the activities
        cursor.moveToFirst();

        do
        {
            a.add(new Business(cursor.getInt(cursor.getColumnIndex(COLUMNS()[0])),
                    cursor.getString(cursor.getColumnIndex(COLUMNS()[1])),
                    new Address(cursor.getString(cursor.getColumnIndex(Address.COLUMNS()[0])),
                            cursor.getString(cursor.getColumnIndex(Address.COLUMNS()[1])),
                            cursor.getString(cursor.getColumnIndex(Address.COLUMNS()[2]))),
                    cursor.getString(cursor.getColumnIndex(COLUMNS()[2])),
                    cursor.getString(cursor.getColumnIndex(COLUMNS()[3])),
                    cursor.getString(cursor.getColumnIndex(COLUMNS()[4]))));

        }while (cursor.moveToNext());
        return a;
    }
}
