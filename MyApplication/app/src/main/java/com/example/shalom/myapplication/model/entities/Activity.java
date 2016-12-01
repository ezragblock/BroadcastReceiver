package com.example.shalom.myapplication.model.entities;

import java.util.Date;

/**
 * Created by Shalom on 11/26/2016.
 */

public class Activity {
    ActivityType activityType;
    String description;
    String state;
    Date beginningDate;
    Date finishDate;
    int price;
    int businessId;

    public Activity(ActivityType activityType, String description, String state, Date beginningDate, Date finishDate, int price, int businessId)
    {
        this.activityType = activityType;
        this.description = description;
        this.state = state;
        this.beginningDate = beginningDate;
        this.finishDate = finishDate;
        this.price = price;
        this.businessId = businessId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
}
