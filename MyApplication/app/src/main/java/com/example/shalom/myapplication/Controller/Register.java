package com.example.shalom.myapplication.Controller;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View v)
    {
        Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
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
        ContentValues values = new ContentValues();
        values.put("userNum",users.get(users.size() - 1).getUserNum() + 1);
        values.put("username",username.getText().toString());
        values.put("password",((EditText)findViewById(R.id.Password)).getText().toString());
        getContentResolver().insert(uri,values);
    }
}
