package com.example.shalom.myapplication.model.backend;

import android.os.AsyncTask;

/**
 * Created by yedid on 11/30/2016.
 */

public class ActivateBackEndTask extends AsyncTask<ICallableTask,Integer,String>
{
    @Override
    protected String doInBackground(ICallableTask... params)
    {
        for(int i = 0;i<params.length;i++)
        {
            try
            {
                params[i].Activate();
            }
            catch (Exception e)
            {

            }
        }
        return "Done";
    }
    @Override
    public void onPostExecute(String result)
    {

    }
    @Override
    public void onPreExecute()
    {

    }
    @Override
    protected void onProgressUpdate(Integer... values)
    {

    }
}
