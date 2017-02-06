package com.example.shalom.myapplication.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.entities.Address;
import com.example.shalom.myapplication.model.entities.Business;

public class MainOptions extends AppCompatActivity
{
    /**
     * Constructor of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
    }

    /**
     * Method for calling up the activity adding window when the add activity button is pressed
     * @param v
     */
    public void addActivity(View v)
    {
        //open add activity window
        startActivity(new Intent(MainOptions.this,AddActivity.class));
    }

    /**
     * This method is for calling up the business creation window when the business creation button is pressed
     * @param v
     */
    public void addBusiness(View v)
    {
        //open the add business activity with intent
        startActivity(new Intent(MainOptions.this,CreateBusiness.class));
    }
}
