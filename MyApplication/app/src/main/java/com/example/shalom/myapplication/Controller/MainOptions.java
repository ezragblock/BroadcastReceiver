package com.example.shalom.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shalom.myapplication.R;
import com.example.shalom.myapplication.model.entities.Address;
import com.example.shalom.myapplication.model.entities.Business;

public class MainOptions extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options2);
    }
    public void addActivity(View v)
    {
        //open add activity window
        startActivity(new Intent(MainOptions.this,AddActivity.class));
    }
    public void addBusiness(View v)
    {
        //open the add business activity with intent
        startActivity(new Intent(MainOptions.this,AddBusiness.class));
    }

}
