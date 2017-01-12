package com.example.shalom.myapplication.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.SharedPreference.MyPreference;
import com.example.shalom.myapplication.model.backend.ActivateBackEndTask;
import com.example.shalom.myapplication.model.backend.ICallableTask;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

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
        final Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
        ArrayList<User> users = User.getListFromCursor(getContentResolver().query(uri,null,null,null,null));
        EditText username = ((EditText)findViewById(R.id.username));
        for (User user: users)
        {
            if(user.getUsername() == username.getText().toString())
            {
                username.setText("That username is already used...");
                return;
            }
        }
        final User u = new User(username.getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString());

        new ActivateBackEndTask().execute(new ICallableTask()
        {
            public Cursor Activate()
            {
                getContentResolver().insert(uri,u.getContentValue());
                return null;
            }
        });

        if(((CheckBox)findViewById(R.id.SaveOnPhone)).isActivated())
        {
            MyPreference.addUser(u);
        }
    }
}
