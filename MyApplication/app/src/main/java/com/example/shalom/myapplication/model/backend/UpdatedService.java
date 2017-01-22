package com.example.shalom.myapplication.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.Nullable;

public class UpdatedService extends Service
{
    private static final String TAG = "service";
    private static IDataSource manager = FactoryDataSource.getDataBase();

    @Override
    public void onCreate()
    {
        super.onCreate();

        new AsyncTask<String, String, String>()
        {
            @Override
            protected String doInBackground(final String... params)
            {
                if(checkForDBUpdates())
                {
                    return "DataBase Has been Updated";
                }
                else
                {
                    return "";
                }
            }
        }.execute ();
    }


    private boolean checkForDBUpdates()
    {
        Intent ActivityUpdate, BusinessUpdate,UserUpdate;
        //sets the appropriate intents in advance
        ActivityUpdate = new Intent("com.example.shalom.myapplication").putExtra("table", 'a');
        BusinessUpdate = new Intent("com.example.shalom.myapplication").putExtra("table", 'b');
        UserUpdate = new Intent("com.example.shalom.myapplication").putExtra("table", 'u');

        while (true)
        {
            try
            {
                //checking for agencies updates
                if (manager.isActivitiesUpdated())
                {
                    sendBroadcast(ActivityUpdate);
                    return true;
                }
                if (manager.isBusinessesUpdated())
                {
                    sendBroadcast(BusinessUpdate);
                    return true;
                }
                if(manager.isUsersUpdated())
                {
                    sendBroadcast(UserUpdate);
                    return true;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //wait 10 seconds
            SystemClock.sleep(10000);
            return false;
        }
    }


    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
