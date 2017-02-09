package com.example.yedid.finalapp.SharedPreference;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.yedid.finalapp.model.entities.ActivityType;
import com.example.yedid.finalapp.model.entities.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yedid on 12/22/2016.
 */

public class SettingPreference
{
    public static final String MY_PREFS_NAME = "SettingFiles";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    /**
     * constructor of the proference
     * @param context
     */
    public SettingPreference(Context context)
    {
        pref = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }

    public void ChangeActivityFilterSetting(String Type,Boolean value)
    {
        editor.remove(Type);
        editor.putBoolean(Type,value);
        editor.commit();
    }

    public Boolean getSettingFilter(String Type)
    {
        if(Type == ActivityType.TRAVEL_AGENCY_TRIP.toString())
            return pref.getBoolean(Type,true);//in this trpe my default is true
        return pref.getBoolean(Type,false);
    }
}
