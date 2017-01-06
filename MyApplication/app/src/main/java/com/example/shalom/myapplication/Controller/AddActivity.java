package com.example.shalom.myapplication.Controller;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.ActivityType;

import java.util.Date;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = (Spinner) findViewById(R.id.actType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);/////////////תסביר אחר כך
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    public void createAct(View v)
    {
        //add activity to database
        ActivityType type;
        Spinner spinner = (Spinner)findViewById(R.id.actType);
        String text = spinner.getSelectedItem().toString();
        ActivityType a;
        switch(text)
        {
            case "Hotel vacation package":
                a = ActivityType.HOTEL_VACATION_PACKAGE;
                break;
            case "Travel agency trip":
                a = ActivityType.TRAVEL_AGENCY_TRIP;
                break;
            case "Entertainment":
                a = ActivityType.ENTERTAINMENT;
                break;
            case "Airline":
                a = ActivityType.AIRLINE;
                break;
            default:
                throw new IllegalArgumentException("You must choose an Activity Type");
        }////צריך לתקן ולהמיר באמצעות מספר//////////////////////////////////////////////
        Activity newActivity = new Activity(a,((EditText)findViewById(R.id.description)).getText().toString(),
                                            ((EditText)findViewById(R.id.state)).getText().toString(),
                                            new GregorianCalendar(((DatePicker)findViewById(R.id.beginningDate)).getDayOfMonth(),
                                                                  ((DatePicker)findViewById(R.id.beginningDate)).getMonth(),
                                                                  ((DatePicker)findViewById(R.id.beginningDate)).getYear()),
                                            new GregorianCalendar(((DatePicker)findViewById(R.id.finishingDate)).getDayOfMonth(),
                                                                  ((DatePicker)findViewById(R.id.finishingDate)).getMonth(),
                                                                  ((DatePicker)findViewById(R.id.finishingDate)).getYear()),
                                            Integer.parseInt(((EditText)findViewById(R.id.price)).getText().toString()),
                                            Integer.parseInt(((EditText)findViewById(R.id.businessID)).getText().toString()));


        Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/activities");
        getContentResolver().insert(uri,newActivity.getContentValue());
    }
}
