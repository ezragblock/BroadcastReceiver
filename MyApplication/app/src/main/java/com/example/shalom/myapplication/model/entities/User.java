package com.example.shalom.myapplication.model.entities;

/**
 * Created by Shalom on 11/26/2016.
 */

public class User
{
    int userNum;
    String username;
    String password;

    public User(int userNum, String username, String password)
    {
        this.userNum = userNum;
        this.username = username;
        this.password = password;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
