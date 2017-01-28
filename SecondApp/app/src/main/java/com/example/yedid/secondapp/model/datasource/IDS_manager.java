package com.example.yedid.secondapp.model.datasource;

import android.database.Cursor;

import com.example.yedid.secondapp.model.entities.Activity;
import com.example.yedid.secondapp.model.entities.Business;

import java.util.List;

/**
 * Created by yedid on 1/18/2017.
 */

public interface IDS_manager
{
    List<Activity> getActivities();
    List<Business> getBusinesses();
    void updateList();
}
