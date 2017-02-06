package com.example.shalom.myapplication.SharedPreference;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.shalom.myapplication.model.entities.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yedid on 12/22/2016.
 */

public class MyPreference
{
    public static final String MY_PREFS_NAME = "YedidyaFiles";//because i'm awesome
    static int counter = 0;//this count how much user i saved

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    /**
     * constructor of the proference
     * @param context
     */
    public MyPreference(Context context)
    {
        pref = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();
        counter = pref.getInt("counter",0);
    }

    /**
     * in charge of adding a user to the preference on the phone
     * @param u the user being added
     */
    public void addUser(User u)
    {
        editor.putString("username" + counter,u.getUsername());
        editor.putString("password" + counter,u.getPassword());
        editor.commit();
        counter++;
        editor.remove("counter");
        editor.putInt("counter",counter);
        editor.commit();
    }

    /**
     * Check whether or nor a user is saved on the phone
     * @param u the user being searched for
     * @return whether or not it's saved on the phone
     */
    public int isUserOnPhone(User u)//will return the index of the user (if such user doesn't exist return 0)
    {
        for(int i = 0; i < counter;i++)//going over all the users and checking for a match
        {
            String s1 = pref.getString("username" + i,"no such name");
            String s2 = pref.getString("password" + i,"no such name");
            if(s1.equals(u.getUsername()) && s2.equals(u.getPassword()))
                return i;
        }
        return 0;
    }

    /**
     * deletes a user from the phone (preference)
     * @param u the user being deleted
     */
    public void deleteUser(User u)
    {
        int i = isUserOnPhone(u);
        if(i != 0)
        {
            editor.remove("username" + i);
            editor.remove("password" + i);
            editor.commit();
        }
    }

}
