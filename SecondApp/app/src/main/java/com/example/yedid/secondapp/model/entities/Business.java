package com.example.yedid.secondapp.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Business implements Serializable
{
    private static final long serialVersionUID = 1L;

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
        String[] COLUMNS = {
            "id",
            "name",
            "state",
            "city",
            "street",
            "telephoneNumber",
            "email",
            "websiteAddress"
        };
        return COLUMNS;
    }

    public static Cursor getCursorFromList(ArrayList<Business> buisness)
    {
        MatrixCursor c = new MatrixCursor(Business.COLUMNS());

        for (Business b:buisness)
        {
            ArrayList<String> temp = new ArrayList<>();
            try
            {
                temp.add(String.valueOf(b.getId()));
                temp.add(b.getName());
                temp.add(b.getAddress().state);
                temp.add(b.getAddress().city);
                temp.add(b.getAddress().street);
                temp.add(b.getTelephoneNumber());
                temp.add(b.getEmail());
                temp.add(b.getWebsiteAddress());

                c.addRow(temp);
                return c;
            }
            catch (Exception e)//we don't know yet what kind of exception can happened here (not yet tested)
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

        //if(!Activity.COLUMNS().equals(cursor.getColumnNames()))
        //   throw new IllegalArgumentException("The columns must match the entity's paramters");

        ArrayList<Business> buisness = new ArrayList<>();//this is the list that we will return with all the activities
        cursor.moveToFirst();

        do
        {
            buisness.add(new Business(cursor.getInt(cursor.getColumnIndex(COLUMNS()[0])),
                                      cursor.getString(cursor.getColumnIndex(COLUMNS()[1])),
                                      new Address(cursor.getString(cursor.getColumnIndex(COLUMNS()[2])),
                                              cursor.getString(cursor.getColumnIndex(COLUMNS()[3])),
                                              cursor.getString(cursor.getColumnIndex(COLUMNS()[4]))),
                                      cursor.getString(cursor.getColumnIndex(COLUMNS()[5])),
                                      cursor.getString(cursor.getColumnIndex(COLUMNS()[6])),
                                      cursor.getString(cursor.getColumnIndex(COLUMNS()[7]))));

        }while (cursor.moveToNext());
        return buisness;
    }

    public final ContentValues getContentvalue()
    {
        final ContentValues values = new ContentValues();
        values.put("id",this.getId());
        values.put("name",this.getName());
        values.put("state",address.state);
        values.put("city",address.city);
        values.put("street",address.street);
        values.put("telephoneNumber",this.getTelephoneNumber());
        values.put("email",this.getEmail());
        values.put("websiteAddress",this.getWebsiteAddress());
        return values;
    }

}
