package com.joe.phonebook.entity;

/**
 * Created by Think on 2017/1/18.
 */

public class PeopleMessage {
    public String name;
    public String value="";
    public TD_User mID_User;
    public String picUrl;
    public int DisOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TD_User getmID_User() {
        return mID_User;
    }

    public void setmID_User(TD_User mID_User) {
        this.mID_User = mID_User;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getDisOrder() {
        return DisOrder;
    }

    public void setDisOrder(int disOrder) {
        DisOrder = disOrder;
    }
}
