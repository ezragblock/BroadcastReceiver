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
import com.example.shalom.myapplication.model.entities.ActivityType;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = (Spinner) findViewById(R.id.actType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    public void createAct(View v)
    {
        //add activity to database
        ContentValues values = new ContentValues();
        ActivityType type;
        Spinner spinner = (Spinner)findViewById(R.id.actType);
        String text = spinner.getSelectedItem().toString();
        switch(text)
        {
            case "Hotel vacation package":
                values.put("type","HOTEL_VACATION_PACKAGE");
                break;
            case "Travel agency trip":
                values.put("type","TRAVEL_AGENCY_TRIP");
                break;
            case "Entertainment":
                values.put("type","ENTERTAINMENT");
                break;
            case "Airline":
                values.put("type","AIRLINE");
                break;
            default:
                // nothing was chosen => send an appropriate message
        }
        values.put("description",((EditText)findViewById(R.id.description)).getText().toString());
        values.put("state",((EditText)findViewById(R.id.state)).getText().toString());
        values.put("beginningday",((Integer)((DatePicker)findViewById(R.id.beginningDate)).getDayOfMonth()).toString());
        values.put("beginningmonth",((Integer)((DatePicker)findViewById(R.id.beginningDate)).getMonth()).toString());
        values.put("beginningyear",((Integer)((DatePicker)findViewById(R.id.beginningDate)).getYear()).toString());
        values.put("finishingday",((Integer)((DatePicker)findViewById(R.id.finishingDate)).getDayOfMonth()).toString());
        values.put("finishingmonth",((Integer)((DatePicker)findViewById(R.id.finishingDate)).getMonth()).toString());
        values.put("finishingyear",((Integer)((DatePicker)findViewById(R.id.finishingDate)).getYear()).toString());
        values.put("price",((EditText)findViewById(R.id.price)).getText().toString());
        values.put("businessId",((EditText)findViewById(R.id.businessID)).getText().toString());
        Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/activities");
        getContentResolver().insert(uri,values);
    }
}
