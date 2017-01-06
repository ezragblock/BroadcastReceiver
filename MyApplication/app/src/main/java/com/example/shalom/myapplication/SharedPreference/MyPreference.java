package com.example.shalom.myapplication.SharedPreference;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yedid on 12/22/2016.
 */

public class MyPreference
{
    public static final String MY_PREFS_NAME = "YedidyaFiles";
    Context temp;
    SharedPreferences.Editor editor = temp.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

    public void addUser()
    {

    }


}
