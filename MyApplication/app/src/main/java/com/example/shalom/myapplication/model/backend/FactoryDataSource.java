package com.example.shalom.myapplication.model.backend;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.datasource.ListDataSource;

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
