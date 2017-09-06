package com.joe.phonebook.entity;

/**
 * 人员部门关系
 * Created by Think on 2017/1/18.
 */

public class SYS_OrgUser {
    public Integer ID;
    public String UserId;
    public String DepartmentCode;
    public String CreatedBy;
    public String CreatedDate;
    public Integer DisOrder;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public Integer getDisOrder() {
        return DisOrder;
    }

    public void setDisOrder(Integer disOrder) {
        DisOrder = disOrder;
    }
}
