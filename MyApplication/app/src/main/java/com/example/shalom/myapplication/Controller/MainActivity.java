package com.example.shalom.myapplication.Controller;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);
    }

}
