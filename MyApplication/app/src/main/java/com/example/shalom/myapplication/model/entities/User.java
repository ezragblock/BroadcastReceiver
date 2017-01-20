package com.example.shalom.myapplication.model.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.EditText;

//import com.example.shalom.myapplication.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shalom on 11/26/2016.
 */

public class User
{
    String username;
    String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String[] COLUMNS()
    {
        String[] COLUMNS = {
                "username",
                "password"
        };
        return COLUMNS;
    }

    public static Cursor getCursorFromList(ArrayList<User> users)
    {
        MatrixCursor c = new MatrixCursor(User.COLUMNS());

        for (User user:users)
        {
            c.addRow(new Object[] {
                    user.getUsername(),
                    user.getPassword()
            });
            return c;
        }
        return c;
    }

    public static ArrayList<User> getListFromCursor(Cursor cursor)
    {
        if(cursor == null)
            return new ArrayList<User>();

        if(!User.COLUMNS().equals(cursor.getColumnNames()))
            throw new IllegalArgumentException("The columns must match the entity's paramters");

        ArrayList<User> users = new ArrayList<>();//this is the list that we will return with all the activities
        cursor.moveToFirst();

        do
        {
            users.add(new User(cursor.getString(cursor.getColumnIndex(COLUMNS()[0])),
                               cursor.getString(cursor.getColumnIndex(COLUMNS()[1]))));

        }while (cursor.moveToNext());
        return users;
    }

    public final ContentValues getContentValue()
    {
        ContentValues values = new ContentValues();
        values.put("username",this.username);
        values.put("password",this.password);
        return values;
    }

}
