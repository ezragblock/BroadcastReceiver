package com.example.shalom.myapplication.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.SharedPreference.MyPreference;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity
{
    /**
     * Constructor for the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /**
     * This method is called when the user presses the register button after entering a username and password
     * and it creates the new user and adds it to the database, and saves it on the phone if the option was selected
     * @param v
     */
    public void register(View v)
    {
        //hear we check if this user is already taken
        (new AsyncTask<String,String,ArrayList<User>>() {
            @Override
            protected void onPostExecute(ArrayList<User> users)
            {
                EditText username = ((EditText)findViewById(R.id.username));
                for(User user:users)
                {
                    if(user.getUsername().equals(username.getText().toString()))
                    {
                        username.setText("That username is already used...");
                        return;
                    }
                }
            }

            @Override
            protected ArrayList<User> doInBackground(String... params)
            {
                try
                {
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                    return User.getListFromCursor(getContentResolver().query(uri, null, null, null, null));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    return new ArrayList<User>();
                }
            }
        }).execute();

        //add the user
        EditText username = ((EditText)findViewById(R.id.username));
        final User u = new User(username.getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString());


        (new AsyncTask<String,Integer,String>() {

            @Override
            protected String doInBackground(String... params)
            {
                try
                {
                    //android.os.Debug.waitForDebugger();
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                    getContentResolver().insert(uri,u.getContentValue());
                }
                catch (Exception e)
                {
                    return e.getMessage();
                }

                return "Your account was registered successfully";
            }

            @Override
            protected void onPostExecute(String s)
            {
                Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
            }
        }).execute();

        //add the user to the phone (if it doesn't already exist)
        if(((CheckBox)findViewById(R.id.SaveOnPhone)).isChecked())
        {
            (new MyPreference(this)).addUser(u);
            Toast.makeText(this,"saved on your phone",Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(Register.this,MainOptions.class));
        this.finish();
    }
}
