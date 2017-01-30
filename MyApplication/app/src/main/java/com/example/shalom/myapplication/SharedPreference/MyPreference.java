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

    public MyPreference(Context context)
    {
        pref = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();
        counter = pref.getInt("counter",0);
    }


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

    public int isUserOnPhone(User u)//will return the index of the user (if such user doesn't exist return 0)
    {
        for(int i = 0; i < counter;i++)//going over all the users and checking for a match
        {
            String s1 = pref.getString("username" + i,"no such name");
            String s2 = pref.getString("password" + i,"no such name");
            if(pref.getString("username" + i,"no such name").equals(u.getUsername())&&pref.getString("password" + i,"password not match").equals(u.getPassword()))
                return i;
        }
        return 0;
    }

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
