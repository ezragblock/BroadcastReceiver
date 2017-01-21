package com.example.shalom.myapplication.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.backend.ActivateBackEndTask;
import com.example.shalom.myapplication.model.backend.ICallableTask;
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

        final Activity newActivity = new Activity(Activity.fromStringTOType(text),
                                                  ((EditText)findViewById(R.id.description)).getText().toString(),
                                                  ((EditText)findViewById(R.id.state)).getText().toString(),
                                                  new GregorianCalendar(((DatePicker)findViewById(R.id.beginningDate)).getDayOfMonth(),
                                                                      ((DatePicker)findViewById(R.id.beginningDate)).getMonth(),
                                                                      ((DatePicker)findViewById(R.id.beginningDate)).getYear()),
                                                  new GregorianCalendar(((DatePicker)findViewById(R.id.finishingDate)).getDayOfMonth(),
                                                                      ((DatePicker)findViewById(R.id.finishingDate)).getMonth(),
                                                                      ((DatePicker)findViewById(R.id.finishingDate)).getYear()),
                                                  Integer.parseInt(((EditText)findViewById(R.id.price)).getText().toString()),
                                                  Integer.parseInt(((EditText)findViewById(R.id.businessID)).getText().toString()));


        final Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/activities");

        (new AsyncTask<String,Integer,String>() {
            @Override
            protected String doInBackground(String... params)
            {
                try {
                    getContentResolver().insert(uri, newActivity.getContentValue());
                }
                catch (Exception e)
                {
                    return e.getMessage();
                }
                return "Added succesfully";
            }

            @Override
            protected void onPostExecute(String s) {
                Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
                toast.show();
            }
        }).execute();

    }
}
////////////////////////////////////////////////////////////