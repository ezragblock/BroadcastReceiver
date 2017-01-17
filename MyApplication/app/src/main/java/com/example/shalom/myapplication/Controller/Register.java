package com.example.shalom.myapplication.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View v)
    {
        try
        {
            (new AsyncTask<String,String,List<User>>() {
                @Override
                protected void onPostExecute(List<User> users)
                {
                    EditText username = ((EditText)findViewById(R.id.username));
                    for(User user:users)
                    {
                        if(user.getUsername() == username.getText().toString())
                        {
                            username.setText("That username is already used...");
                            return;
                        }
                    }
                }

                @Override
                protected List<User> doInBackground(String... params)
                {
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                    return User.getListFromCursor(getContentResolver().query(uri,null,null,null,null));
                }
            }).execute();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
            toast.show();
        }


        EditText username = ((EditText)findViewById(R.id.username));
        final User u = new User(username.getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString());
        try
        {
            (new AsyncTask<String,Integer,Integer>() {
                @Override
                protected Integer doInBackground(String... params)
                {
                    Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                    getContentResolver().insert(uri,u.getContentValue());
                    return 0;
                }
            }).execute();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
            toast.show();
        }

        if(((CheckBox)findViewById(R.id.SaveOnPhone)).isActivated())
        {
            (new MyPreference(this)).addUser(u);
        }
    }
}
