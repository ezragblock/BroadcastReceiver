package com.example.yedid.secondapp.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yedid.secondapp.R;
import com.example.yedid.secondapp.model.entities.Business;

public class BusinessInfoActivity extends AppCompatActivity {

    Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_info);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        business = (Business)b.get("b");

        //set the btns text
        ((TextView)findViewById(R.id.business_name)).setText(business.getName());
        ((Button)findViewById(R.id.phone_btn)).setText(business.getTelephoneNumber());
        ((Button)findViewById(R.id.web_btn)).setText(business.getWebsiteAddress());
        ((Button)findViewById(R.id.adress_btn)).setText(business.getAddress().toString());
        ((Button)findViewById(R.id.email_btn)).setText(business.getEmail());
    }

    protected void Dial(View view)
    {
        String uri = "tel:" + business.getTelephoneNumber();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    protected void SentEmail(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, business.getEmail());
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    protected void OpenMap(View view)
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=1600 " + business.getAddress().state + ", " + business.getAddress().city + ", " + business.getAddress().state);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    protected void OpenWebSite(View view)
    {

    }
}
