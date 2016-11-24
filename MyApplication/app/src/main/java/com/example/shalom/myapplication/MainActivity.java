package com.example.shalom.myapplication;

import android.app.Activity;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends Activity
{
    private List<Item> myItemList;

    void initItemList(int size)
    {
        myItemList = new ArrayList<Item>();
        for (int i = 0; i < size; i++)
        {
            int day = i % 27 + 1;
            int month = i % 12;
            int year = i % 10 + 1990;
            Calendar calender = new GregorianCalendar(year, month, day);
            Item item = new Item(i, "item " + i, calender.getTime());
            myItemList.add(item);
        }
    }
}
