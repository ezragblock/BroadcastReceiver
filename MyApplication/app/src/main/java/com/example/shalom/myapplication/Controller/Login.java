package com.example.shalom.myapplication.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.SharedPreference.MyPreference;
import com.example.shalom.myapplication.model.backend.UpdatedService;
import com.example.shalom.myapplication.model.datasource.CustomContentProvider;
import com.example.shalom.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity
{

    /**
     * the constructor of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /*EditText usernameText =(EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.Password);
        MyPreference pref = new MyPreference(this);
        User favoriteUser = pref.getPreferUser();
        if(favoriteUser != null)
        {
            usernameText.setText(favoriteUser.getUsername());
            passwordText.setText(favoriteUser.getPassword());
        }*/

        //we set the users we save on the phone in the drop down list
        ArrayList<User> users = (new MyPreference(this)).getUsers();
        ArrayList<String> usernames = new ArrayList<String>();
        for (User user : users)
        {
            usernames.add(user.getUsername());
        }
        final AutoCompleteTextView username = (AutoCompleteTextView) findViewById(R.id.username);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usernames);
        username.setAdapter(adapter);
        username.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = ((AutoCompleteTextView)((AutoCompleteTextView) findViewById(R.id.username))).getText().toString();
                ArrayList<User> users = (new MyPreference(getApplicationContext())).getUsers();
                for(User u : users)
                {
                    if(u.getUsername().startsWith(username))
                    {
                        ((EditText)findViewById(R.id.Password)).setText(u.getPassword());
                    }
                }
            }
        });
    }

    /**
     * This method is called when the user enters the username and password
     * and this checks if its in the database (or on the phone if it was saved there)
     * and if it is, it opens the main options window
     * @param v
     */
    public void login(View v)
    {
        final User u = new User(((AutoCompleteTextView)findViewById(R.id.username)).getText().toString(),((EditText)findViewById(R.id.Password)).getText().toString());
        //the user that was typed
        if((new MyPreference(this)).isUserOnPhone(u) != -1)//check if exist on phone
        {
            startActivity(new Intent(Login.this, MainOptions.class));
        }
        else
        {
            //now we will check in the database
            (new AsyncTask<String,String,ArrayList<User>>() {
                @Override
                protected ArrayList<User> doInBackground(String... params)
                {
                    try
                    {
                        Uri uri = Uri.parse("content://" + CustomContentProvider.PROVIDER_NAME + "/users");
                        return User.getListFromCursor(getContentResolver().query(uri,null,null,null,null));
                    }
                    catch (Exception e)
                    {
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(ArrayList<User> users) {
                    for (User user : users)//going for each user in the database and checking if his data match the input
                    {
                        if (user.getUsername().equals(u.getUsername())
                                && user.getPassword().equals(u.getPassword()))//checking if the username and password are a match
                        {
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            if(((CheckBox)findViewById(R.id.save_checkbox)).isChecked())
                                (new MyPreference(getApplicationContext())).addUser(u);
                            //user opened the application... does anything later need to know the user who opened it or not?
                            startActivity(new Intent(Login.this, MainOptions.class));
                            return;
                        }
                    }
                    //check if it exists in database
                    //if we went over all the users and there was no match we will let the user know
                    Toast toast = Toast.makeText(getApplicationContext(), "Username or Password are incorrect", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);//for multithreading
        }
        this.finish();
    }

    /*public void register(View v)
    {
        //start register activity
        startActivity(new Intent(Login.this,Register.class));
    }*/
}
