package com.example.yedid.secondapp.model.datasource;

import android.database.Cursor;

/**
 * Created by yedid on 1/18/2017.
 */

public interface IDS_manager
{
    Cursor getActivities() throws Exception;
    Cursor getUsers() throws Exception;
    Cursor getBusinesses() throws Exception;
}
