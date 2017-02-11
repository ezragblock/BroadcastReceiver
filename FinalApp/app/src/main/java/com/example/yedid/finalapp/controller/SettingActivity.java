package com.example.yedid.finalapp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.yedid.finalapp.R;
import com.example.yedid.finalapp.SharedPreference.SettingPreference;
import com.example.yedid.finalapp.model.entities.Activity;
import com.example.yedid.finalapp.model.entities.ActivityType;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SettingPreference settingPreference = new SettingPreference(this);

        Switch s = (Switch) findViewById(R.id.trip_switch);
        s.setChecked(settingPreference.getSettingFilter(ActivityType.TRAVEL_AGENCY_TRIP));

        s = (Switch) findViewById(R.id.hotel_vaction_switch);
        s.setChecked(settingPreference.getSettingFilter(ActivityType.HOTEL_VACATION_PACKAGE));

        s = (Switch) findViewById(R.id.airline_switch);
        s.setChecked(settingPreference.getSettingFilter(ActivityType.AIRLINE));

        s = (Switch) findViewById(R.id.entertainment_switch);
        s.setChecked(settingPreference.getSettingFilter(ActivityType.ENTERTAINMENT));
    }


    public void UpdateSetting(View view) {
        SettingPreference settingPreference = new SettingPreference(this);

        Switch s = (Switch) findViewById(R.id.trip_switch);
        settingPreference.ChangeActivityFilterSetting(ActivityType.TRAVEL_AGENCY_TRIP,s.isChecked());

        s = (Switch) findViewById(R.id.hotel_vaction_switch);
        settingPreference.ChangeActivityFilterSetting(ActivityType.HOTEL_VACATION_PACKAGE,s.isChecked());

        s = (Switch) findViewById(R.id.airline_switch);
        settingPreference.ChangeActivityFilterSetting(ActivityType.AIRLINE,s.isChecked());

        s = (Switch) findViewById(R.id.entertainment_switch);
        settingPreference.ChangeActivityFilterSetting(ActivityType.ENTERTAINMENT,s.isChecked());
    }
}
