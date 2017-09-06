package com.joe.phonebook.entity;

/**
 *
 * 人员属性定义
 * Created by Think on 2017/1/18.
 */

public class TD_User {

    public String FieldName;
    public String DisLabel ;
    public int DisOrder;
    public short IsActive;
    public boolean EnabledEdit;
    public short SecretFlag;
    public short Action;

    public short usergroup_protect;
    public String protect_mode;

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getDisLabel() {
        return DisLabel;
    }

    public void setDisLabel(String disLabel) {
        DisLabel = disLabel;
    }

    public int getDisOrder() {
        return DisOrder;
    }

    public void setDisOrder(int disOrder) {
        DisOrder = disOrder;
    }

    public short getIsActive() {
        return IsActive;
    }

    public void setIsActive(short isActive) {
        IsActive = isActive;
    }

    public boolean isEnabledEdit() {
        return EnabledEdit;
    }

    public void setEnabledEdit(boolean enabledEdit) {
        EnabledEdit = enabledEdit;
    }

    public short getSecretFlag() {
        return SecretFlag;
    }

    public void setSecretFlag(short secretFlag) {
        SecretFlag = secretFlag;
    }

    public short getAction() {
        return Action;
    }

    public void setAction(short action) {
        Action = action;
    }

    public short getUsergroup_protect() {
        return usergroup_protect;
    }

    public void setUsergroup_protect(short usergroup_protect) {
        this.usergroup_protect = usergroup_protect;
    }

    public String getProtect_mode() {
        return protect_mode;
    }

    public void setProtect_mode(String protect_mode) {
        this.protect_mode = protect_mode;
    }

    //重写
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        if (o == null ||!(o instanceof TD_User)) {

            return false;
        };
        TD_User mTD_User = (TD_User)o;

        return mTD_User.FieldName.equals(FieldName);
    }
}
