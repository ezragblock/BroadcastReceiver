package com.example.yedid.finalapp.model.datasource;

import com.example.yedid.finalapp.model.entities.Activity;
import com.example.yedid.finalapp.model.entities.Business;

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
