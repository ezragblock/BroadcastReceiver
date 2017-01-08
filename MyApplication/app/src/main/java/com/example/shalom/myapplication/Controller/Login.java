package com.example.shalom.myapplication.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.SharedPreference.MyPreference;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

public class Login extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View v)
    {
        //first we will check if the user exist in the phone
        if(MyPreference.isUserOnPhone(new User(((EditText)findViewById(R.id.username)).getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString())) != 0)
        {//check if this user exist in the preference
            //user opened the application... does anything later need to know the user who opened it or not?
            startActivity(new Intent(Login.this,MainOptions.class));
        }

        Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        ArrayList<User> users = User.getListFromCursor(cursor);
        for (User user: users)//going for each user in the database and checking if his data match the input
        {
            if(user.getUsername() == ((EditText)findViewById(R.id.username)).getText().toString()
                && user.getPassword() == ((EditText)findViewById(R.id.Password)).getText().toString())//checking if the username and password are a match
            {
                //user opened the application... does anything later need to know the user who opened it or not?
                startActivity(new Intent(Login.this,MainOptions.class));
            }
        }
        //check if it exists in database
    }
    public void register(View v)
    {
        //start register activity
        startActivity(new Intent(Login.this,Register.class));
    }
}
