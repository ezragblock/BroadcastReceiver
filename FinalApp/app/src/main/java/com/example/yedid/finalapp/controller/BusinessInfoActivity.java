package com.example.yedid.finalapp.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yedid.finalapp.R;
import com.example.yedid.finalapp.model.backend.FactoryDataSource;
import com.example.yedid.finalapp.model.entities.Activity;
import com.example.yedid.finalapp.model.entities.Business;

import java.util.ArrayList;

public class BusinessInfoActivity extends AppCompatActivity {

    private ExpandableListView activitiesListView;
    private Business business;

    /**
     * This is called on the creation of the business info activity.
     * It's in charge of adding all the business info into the window and adding the activities into the business aat the bottom
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_info);

        setBusinessArg();
        setBusinessData();




        ArrayList<Activity> activities = new ArrayList<>();//taking only the trip for this business
        for(Activity activity: FactoryDataSource.getDataBase().getActivities())
        {
            if(activity.getBusinessId() == business.getId())
                activities.add(activity);
        }

        activitiesListView = (ExpandableListView) findViewById(R.id.activitiesExpandableView);
        activitiesListView.setAdapter(new ExpandableListAdapter(activities,this));
        activitiesListView.setGroupIndicator(null);

        final ExpandableListAdapter adapter;

        setTitle(business.getName());
    }


    /**
     * This method is called when the user presses the phone number of the business
     * It opens the dialer with the phone number
     * @param view
     */
    protected void Dial(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + business.getTelephoneNumber()));
        try {
            startActivity(intent);
        }
        catch (SecurityException e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
        }
    }

    /**
     * This method is called when the email address of the business is pressed.
     * It opens up the option to send an email to the business's email address
     * @param view
     */
    protected void SentEmail(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{business.getEmail()});
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    /**
     * This pressed when the user presses the business address.
     * It opens it in Google maps
     * @param view
     */
    protected void OpenMap(View view)
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=1600 " + business.getAddress().state + ", " + business.getAddress().city + ", " + business.getAddress().state);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    /**
     * This is pressed when the user presses the website of the business
     * It opens a webview in the activity
     * @param view
     */
    protected void OpenWebSite(View view)
    {
        Intent intent = new Intent(this, BusinessWebView.class);
        intent.putExtra("address",business.getWebsiteAddress());
        startActivity(intent);
    }

    /**
     * Sets up the business arguments from the intent
     */
    //set the business argument for thisfragment so we can take the buisness data and print them
    private void setBusinessArg()
    {
        Intent intent = getIntent();
        business = (Business)intent.getSerializableExtra("business");
    }

    /**
     * This method takes the business info and puts it in all the controls like buttons etc.
     */
    //take the data from the business paramter and print it
    private void setBusinessData()
    {
        //set the btns text
        ((TextView)findViewById(R.id.businessNameText)).setText(business.getName());
        ((Button)findViewById(R.id.phoneBtn)).setText(business.getTelephoneNumber());
        ((Button)findViewById(R.id.webBtn)).setText(business.getWebsiteAddress());
        ((Button)findViewById(R.id.mapBtn)).setText(business.getAddress().toString());
        ((Button)findViewById(R.id.emailBtn)).setText(business.getEmail());
    }
}
