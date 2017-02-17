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
    private static Boolean IsRunning = false;
    private static IDataSource manager = FactoryDataSource.getDataBase();

    public static Boolean IsServiceRunning()
    {
        return IsRunning;
    }


    /**
     * constructor for the service (also checks if the database has been updated or not
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        IsRunning = true;

        new AsyncTask<String, String, String>()
        {
            @Override
            protected String doInBackground(final String... params)
            {
                if(checkForDBUpdates())//this function send a broadcast if she detect a change on the server
                {
                    return "DataBase Has been Updated";
                }
                else
                {
                    return "";
                }
            }
        }.execute();
    }

    /**
     * This method is in charge of checking whether or not the database has been updated
     * @return true if it has been updated and false otherwise
     */
    private boolean checkForDBUpdates()
    {
        Intent ActivityUpdate, BusinessUpdate,UserUpdate;
        //sets the appropriate intents in advance
        ActivityUpdate = new Intent("com.example.shalom.secondapp.UPDATE_LIST").putExtra("table", 'a');
        BusinessUpdate = new Intent("com.example.shalom.secondapp.UPDATE_LIST").putExtra("table", 'b');
        UserUpdate = new Intent("com.example.shalom.secondapp.UPDATE_USER").putExtra("table", 'u');

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

    /**
     * Not yet implemented
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * start command listener method
     * @param intent the intent
     * @param flags the flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * destructor of the the service
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
