package com.example.shalom.myapplication.datasource;

import com.example.shalom.myapplication.backend.IDataSource;

/**
 * Created by Shalom on 11/29/2016.
 */

public class FactoryDataSource
{
    static IDataSource myDataSource = null;
    public static IDataSource getDataBase()
    {
        if(myDataSource == null)
            myDataSource = new ListDataSource();
        return myDataSource;
    }
}
