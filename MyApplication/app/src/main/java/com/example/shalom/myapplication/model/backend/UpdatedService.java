package com.example.shalom.myapplication.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.AsyncTask;

public class UpdatedService extends Service
{
    private static final String TAG = "service";
    private static IDataSource manager = FactoryDataSource.getDataBase();

    new AsyncTask<String, String, String>()
    {
        @Override
        protected Void doInBackground(final String... params)
        {
            checkForDBUpdates();
            return "0";
        }
    }.execute ();

    private void checkForDBUpdates()
    {
        Intent ActivityUpdate, BusinessUpdate,UserUpdate;
        //sets the appropriate intents in advance
        ActivityUpdate = new Intent("com.example.ezras.newUpdates").putExtra("table", 'a');
        BusinessUpdate = new Intent("com.example.ezras.newUpdates").putExtra("table", 'b');
        UserUpdate = new Intent("com.example.ezras.newUpdates").putExtra("table", 'u');

        while (true)
        {
            try
            {
                //checking for agencies updates
                if (manager.isActivitiesUpdated())
                {
                    sendBroadcast(ActivityUpdate);
                }
                if (manager.isBusinessesUpdated())
                {
                    sendBroadcast(BusinessUpdate);
                }
                if(manager.isUsersUpdated())
                {
                    sendBroadcast(UserUpdate);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //wait 10 seconds
            SystemClock.sleep(10000);
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
