package com.example.yedid.finalapp.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;

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

    /**
     * Constructor for a business
     * @param id the business identification number
     * @param name the business name
     * @param address the address of the business
     * @param telephoneNumber the business telephone number
     * @param email the business's email address
     * @param websiteAddress the business's website URL
     */
    public Business(int id, String name, Address address, String telephoneNumber, String email, String websiteAddress)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.websiteAddress = websiteAddress;
    }

    /**
     *
     * @return the business id
     */
    public int getId() {
        return id;
    }

    /**
     *  sets the business id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the business name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the business name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returns the business's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * sets the business's address
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return the email address of the business
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the telephone number
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * sets the telephone number
     * @param telephoneNumber
     */
    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     *
     * @return the website url
     */
    public String getWebsiteAddress() {
        return websiteAddress;
    }

    /**
     * sets the website url
     * @param websiteAddress
     */
    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    /**
     * returns the columns of the business in the database
     * @return
     */
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

    /**
     * converts a arraylist of businesses to a cursor
     * @param buisness the arraylist
     * @return the cursor
     */
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

    /**
     * converts a cursor to an arraylist of businesses
     * @param cursor the cursor being converted
     * @return the array;ist of businesses
     */
    public static ArrayList<Business> getListFromCursor(Cursor cursor)
    {
        if(cursor == null)
            return new ArrayList<Business>();

        ArrayList<Business> buisness = new ArrayList<>();//this is the list that we will return with all the businesses
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

    /**
     *
     * @return the business in content vaule form
     */
    public final ContentValues getContentvalue()
    {
        final ContentValues values = new ContentValues();
        values.put("id",this.getId());
        values.put("name",this.getName());
        values.put("state",address.state);
        values.put("city",address.city);
        values.put("street",address.street);
        values.put("websiteAddress",this.getWebsiteAddress());
        return values;
    }

    /**
     *
     * @return business in string format
     */
    @Override
    public String toString() {
        return this.name;
    }
}