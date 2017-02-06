package com.example.shalom.myapplication.model.backend;

import com.example.shalom.myapplication.model.backend.IDataSource;
import com.example.shalom.myapplication.model.datasource.ListDataSource;
import com.example.shalom.myapplication.model.datasource.SQLDataBase;

/**
 * Created by Shalom on 11/29/2016.
 */

public class FactoryDataSource
{
    static IDataSource myDataSource = null;

    /**
     * This method is the factory method called when creating a database.
     * It decides whether to return the SQL based data base or the list based database
     * (It is called immediately from the class without am instance).
     * @return the database (or creates a new one if one doesn't exist yet)
     */
    public static IDataSource getDataBase()
    {
        if(myDataSource == null)
            myDataSource = new SQLDataBase();
        return myDataSource;
    }
}
