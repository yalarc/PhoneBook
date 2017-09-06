package com.joe.phonebook.entity;

/**
 * 用户的常用联系人
 * Created by Think on 2017/1/18.
 */

public class T_UserRelationship {

    private String UserId;
    private String CUserId;
    private String header;
    public Short statusFlag;
    private SYS_User mSYS_User;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCUserId() {
        return CUserId;
    }

    public void setCUserId(String CUserId) {
        this.CUserId = CUserId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Short getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Short statusFlag) {
        this.statusFlag = statusFlag;
    }

    public SYS_User getmSYS_User() {
        return mSYS_User;
    }

    public void setmSYS_User(SYS_User mSYS_User) {
        this.mSYS_User = mSYS_User;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof T_UserRelationship)) {

            return false;
        }
        T_UserRelationship mT_UserRelationship = (T_UserRelationship) o;

        return mT_UserRelationship.CUserId.equals(CUserId);
    }

}
