package com.example.shalom.myapplication.Controller;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.shalom.myapplication.R;
public class MainActivity extends Activity
{
    /**
     * Constructor for the activity
     * @param bundle
     */
    @Override
    public void onCreate(Bundle bundle)
    {
        //on Create
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method for calling up the register window when the register button is pressed
     *
     * @param view
     */
    public void registerAct(View view)
    {
        startActivity(new Intent(MainActivity.this,Register.class));
    }

    /**
     * Method for calling up the login window when the login button is pressed
     * @param view
     */
    public void loginAct(View view)
    {
        startActivity(new Intent(MainActivity.this,Login.class));
    }

}
