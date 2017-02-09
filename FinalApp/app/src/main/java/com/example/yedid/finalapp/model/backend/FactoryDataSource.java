package com.example.yedid.finalapp.model.backend;

import android.content.Context;

import com.example.yedid.finalapp.model.datasource.DataBase;
import com.example.yedid.finalapp.model.datasource.IDS_manager;

/**
 * Created by yedid on 1/30/2017.
 */

public class FactoryDataSource
{
    static Context context;
    static IDS_manager myDataSource = null;

    /**
     *
     * @return a database according to what the factory method "decides"
     */
    public static IDS_manager getDataBase()
    {
        if(myDataSource == null)
            myDataSource = new DataBase(context);
        return myDataSource;
    }

    public static void setContex(Context c)
    {
        context = c;
    }

}
