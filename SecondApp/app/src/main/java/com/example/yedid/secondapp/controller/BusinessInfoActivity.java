package com.example.yedid.secondapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.yedid.secondapp.R;
import com.example.yedid.secondapp.model.entities.Business;

public class BusinessInfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_info);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Business business = (Business)b.get("b");

        //set the btns text
        ((TextView)findViewById(R.id.business_name)).setText(business.getName());
        ((Button)findViewById(R.id.phone_btn)).setText(business.getTelephoneNumber());
        ((Button)findViewById(R.id.web_btn)).setText(business.getWebsiteAddress());
        ((Button)findViewById(R.id.adress_btn)).setText(business.getAddress().toString());
        ((Button)findViewById(R.id.email_btn)).setText(business.getEmail());
    }
}
