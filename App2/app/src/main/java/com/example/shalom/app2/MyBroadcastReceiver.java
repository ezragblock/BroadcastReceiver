package com.example.shalom.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Shalom on 1/17/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // do query from the database
        // TODO Auto-generated method stub
        // Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("message");
        Toast.makeText(context,"The broadcast receiver received the Intent's message: " + intentData, Toast.LENGTH_LONG).show();
    }
}
