package com.example.shalom.myapplication.Controller;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.Address;
import com.example.shalom.myapplication.model.entities.Business;

public class CreateBusiness extends AppCompatActivity {

    /**
     * constructor of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
    }

    /**
     * this method is called when the user presses the business creation button
     * is in charge of creating and adding the business to the database
     * @param v the button
     */
    public void addBusiness(View v)
    {
        Address address = new Address(
                ((EditText) findViewById(R.id.state)).getText().toString()
                ,((EditText) findViewById(R.id.city)).getText().toString()
                ,((EditText) findViewById(R.id.street)).getText().toString());

        final Business newBusiness = new Business(
                Integer.parseInt(((EditText) findViewById(R.id.ID)).getText().toString())
                ,((EditText) findViewById(R.id.name)).getText().toString()
                ,address
                ,((EditText) findViewById(R.id.telephoneNumber)).getText().toString()
                ,((EditText) findViewById(R.id.email)).getText().toString()
                ,((EditText) findViewById(R.id.website)).getText().toString());
        //add to content provider
        final Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/businesses");


        (new AsyncTask<String,Integer,String>() {
            @Override
            protected String doInBackground(String... params)
            {
                try
                {
                    getContentResolver().insert(uri, newBusiness.getContentvalue());
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
        }).execute();
        this.finish();
    }
}
