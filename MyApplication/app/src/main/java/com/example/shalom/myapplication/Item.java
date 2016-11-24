package com.example.shalom.myapplication;

import java.util.Date;

/**
 * Created by Shalom on 11/17/2016.
 */

public class Item {
    int id;
    String name;
    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Item() {
    }

    public Item(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
