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
    @Override
    public void onCreate(Bundle bundle)
    {
        //on Create
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }
    public void registerAct(View view)
    {
        startActivity(new Intent(MainActivity.this,Register.class));
    }
    public void loginAct(View view)
    {
        startActivity(new Intent(MainActivity.this,Login.class));
    }

}
