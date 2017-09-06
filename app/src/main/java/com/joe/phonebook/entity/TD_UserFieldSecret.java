package com.joe.phonebook.entity;

import java.io.Serializable;

/**
 * 定义某个保密字段哪些人能看
 * Created by Think on 2017/1/18.
 */

public class TD_UserFieldSecret implements Serializable {

    public String UserId;
    public String FieldName;
    private TD_User mTD_User;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public TD_User getmTD_User() {
        return mTD_User;
    }

    public void setmTD_User(TD_User mTD_User) {
        this.mTD_User = mTD_User;
    }

    // 重写
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        if (o == null || !(o instanceof TD_UserFieldSecret)) {

            return false;
        }
        ;
        TD_UserFieldSecret mTD_UserFieldSecret = (TD_UserFieldSecret) o;

        return mTD_UserFieldSecret.UserId.equals(UserId);
    }
}
