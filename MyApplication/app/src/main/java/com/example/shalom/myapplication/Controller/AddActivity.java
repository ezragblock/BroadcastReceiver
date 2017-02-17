package com.example.shalom.myapplication.Controller;

import android.content.Intent;
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
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.Activity;
import com.example.shalom.myapplication.model.entities.ActivityType;
import com.example.shalom.myapplication.model.entities.Business;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity
{
    /**
     * This method is in charge of creating the add activity activity
     * and sets up all the things in the window like spinners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        /*
        final Spinner spinner2 = (Spinner)findViewById(R.id.businessSpinner);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerAdapter);


        final ArrayList<String> names = new ArrayList<String>();
        (new AsyncTask<String,String,Cursor>() {
            @Override
            protected void onPostExecute(Cursor cursor)
            {
                /*ArrayList<Business> businesses = Business.getListFromCursor(cursor);
                for (Business business: businesses)//going for each user in the database and checking if his data match the input
                {
                    names.add(business.getName());
                }
                return;
            }

            @Override
            protected Cursor doInBackground(String... params)
            {
                try
                {
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/businesses");
                    Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                    return cursor;
                }
                catch (Exception e)
                {
                    return null;
                }
            }
        }).execute();

        spinnerAdapter.notifyDataSetChanged();
        */
        Spinner spinner = (Spinner) findViewById(R.id.actType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);/////////////תסביר אחר כך
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    /**
     * method which is called when the user presses the create activity button
     * Is in charge of creating the activity and adding it to the database
     *
     * @param v the button
     */
    public void createAct(View v)
    {
        //add activity to database
        Spinner spinner = (Spinner)findViewById(R.id.actType);
        String text = spinner.getSelectedItem().toString();
        GregorianCalendar start = new GregorianCalendar();
        start.set(
                ((DatePicker)findViewById(R.id.beginningDate)).getYear(),
                ((DatePicker)findViewById(R.id.beginningDate)).getMonth(),
                ((DatePicker)findViewById(R.id.beginningDate)).getDayOfMonth());
        GregorianCalendar end = new GregorianCalendar();
        end.set(
                ((DatePicker)findViewById(R.id.finishingDate)).getYear(),
                ((DatePicker)findViewById(R.id.finishingDate)).getMonth(),
                ((DatePicker)findViewById(R.id.finishingDate)).getDayOfMonth());
        final Activity newActivity = new Activity(Activity.fromStringTOType(text),
                                                  ((EditText)findViewById(R.id.description)).getText().toString(),
                                                  ((EditText)findViewById(R.id.state)).getText().toString(),
                                                  start,end,Integer.parseInt(((EditText)findViewById(R.id.price)).getText().toString()),
                                                  Integer.parseInt(((EditText)findViewById(R.id.businessID)).getText().toString()));

        final Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/activities");

        //this task is to add the activity
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
                return "Added successfully";
            }

            @Override
            protected void onPostExecute(String s) {
                Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
                toast.show();
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);//for multithreading
        this.finish();
    }
}
