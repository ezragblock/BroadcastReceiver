package com.example.yedid.finalapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.yedid.finalapp.model.backend.FactoryDataSource;
import com.example.yedid.finalapp.model.datasource.DataBase;
import com.example.yedid.finalapp.model.datasource.IDS_manager;

/**
 * Created by yedid on 1/22/2017.
 */

public class BroadcastReceiver extends android.content.BroadcastReceiver
{
    IDS_manager manager;
    public BroadcastReceiver()
    {
    }

    /**
     * In charge of receiving a broadcast in this case to update the database
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // do query from the database
        // TODO Auto-generated method stub
        // Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("message");
        if(intent.getAction().matches("com.example.shalom.secondapp.UPDATE_LIST"))
        {
            FactoryDataSource.setContex(context);
            manager =FactoryDataSource.getDataBase();
            manager.updateList();
            Toast.makeText(context, "Data Updated", Toast.LENGTH_LONG).show();
        }
        /*else if (intent.getAction().matches("android.intent.action.TIME_TICK"))
            Toast.makeText(context, "TIME_TICK", Toast.LENGTH_LONG).show();
        else if(intent.getAction().matches("android.intent.action.BOOT_COMPLETED"))
            Toast.makeText(context, "BOOT_COMPLETED", Toast.LENGTH_LONG).show();
        else if (intent.getAction().matches("android.intent.action.TIME_SET"))
            Toast.makeText(context, "TIME_SET", Toast.LENGTH_LONG).show();
        else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
            Toast.makeText(context, "SMS_RECEIVED", Toast.LENGTH_LONG).show();*/
    }
}
