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
     * constructor of the preference
     * @param context
     */
    public SettingPreference(Context context)
    {
        pref = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * in charge of changing the activity filter setting so it will filter accordingly
     * @param Type
     * @param value
     */
    public void ChangeActivityFilterSetting(ActivityType Type,Boolean value)
    {
        editor.remove(Type.toString());
        editor.putBoolean(Type.toString(),value);
        editor.commit();
    }

    /**
     * get the setting filer
     * @param Type
     * @return
     */
    public Boolean getSettingFilter(ActivityType Type)
    {
        if(Type == ActivityType.TRAVEL_AGENCY_TRIP)
            return pref.getBoolean(Type.toString(),true);//in this trpe my default is true
        return pref.getBoolean(Type.toString(),false);
    }
}
