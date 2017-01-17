package com.example.shalom.myapplication.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.SharedPreference.MyPreference;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

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
        final User u = new User(((EditText)findViewById(R.id.username)).getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString());
        //the user that was typed
        if((new MyPreference(this)).isUserOnPhone(u)!=0)//check if exist on phone
            startActivity(new Intent(Login.this,MainOptions.class));

        try
        {
            //now we will check in the database
            (new AsyncTask<String,String,Cursor>() {
                @Override
                protected void onPostExecute(Cursor cursor)
                {
                    ArrayList<User> users = User.getListFromCursor(cursor);
                    for (User user: users)//going for each user in the database and checking if his data match the input
                    {
                        if(user.getUsername() == u.getUsername()
                                && user.getPassword() == u.getPassword())//checking if the username and password are a match
                        {
                            //user opened the application... does anything later need to know the user who opened it or not?
                            startActivity(new Intent(Login.this,MainOptions.class));
                        }
                    }
                    //check if it exists in database
                }

                @Override
                protected Cursor doInBackground(String... params)
                {
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                    return getContentResolver().query(uri,null,null,null,null);
                }
            }).execute();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void register(View v)
    {
        //start register activity
        startActivity(new Intent(Login.this,Register.class));
    }
}
