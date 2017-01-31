package com.example.yedid.secondapp.model.backend;

import android.content.Context;

import com.example.yedid.secondapp.model.datasource.DataBase;
import com.example.yedid.secondapp.model.datasource.IDS_manager;

/**
 * Created by yedid on 1/30/2017.
 */

public class FactoryDataSource
{
    static IDS_manager myDataSource = null;
    public static IDS_manager getDataBase(Context context)
    {
        if(myDataSource == null)
            myDataSource = new DataBase(context);
        return myDataSource;
    }
}
