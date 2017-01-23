package com.example.yedid.secondapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by yedid on 1/22/2017.
 */

public class BroadcastReceiver extends android.content.BroadcastReceiver
{
    public BroadcastReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // do query from the database
        // TODO Auto-generated method stub
        // Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("message");
        Toast.makeText(context, "Javacodegeeks received the Intent's message:" + intentData,Toast.LENGTH_LONG).show();
        if (intent.getAction().matches("android.intent.action.TIME_TICK"))
            Toast.makeText(context, "TIME_TICK", Toast.LENGTH_LONG).show();
        else if(intent.getAction().matches("android.intent.action.BOOT_COMPLETED"))
            Toast.makeText(context, "BOOT_COMPLETED", Toast.LENGTH_LONG).show();
        else if (intent.getAction().matches("android.intent.action.TIME_SET"))
            Toast.makeText(context, "TIME_SET", Toast.LENGTH_LONG).show();
        else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
            Toast.makeText(context, "SMS_RECEIVED", Toast.LENGTH_LONG).show();
    }
}
