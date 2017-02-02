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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
    }
    public void addActivity(View v)
    {
        //open add activity window
        startActivity(new Intent(MainOptions.this,AddActivity.class));
    }
    public void addBusiness(View v)
    {

        /*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context); alertDialogBuilder.setTitle("login");

        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        alertDialogBuilder.setView(inflater.inflate(R.layout.dialog_add_business, null));

        AlertDialog.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override public void onClick(DialogInterface dialog, int which) {
                switch(which)
                {
                    case Dialog.BUTTON_NEGATIVE:
                        AlertDialog.
                        break;
                    case Dialog.BUTTON_POSITIVE:
                        break;
                }
            }
        };


        // Add action buttons
        alertDialogBuilder.setPositiveButton("Create", onClickListener);
        alertDialogBuilder.setNegativeButton("Cancel",onClickListener);

        alertDialogBuilder.show();*/



        //open the add business activity with intent
        startActivity(new Intent(MainOptions.this,CreateBusiness.class));
    }
}
