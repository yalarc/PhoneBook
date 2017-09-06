package com.joe.phonebook.application;

import android.content.Context;

import com.joe.phonebook.db.DBManager;
import com.joe.phonebook.entity.SYS_Department;
import com.joe.phonebook.entity.SYS_User;
import com.joe.phonebook.entity.T_UserRelationship;

import java.util.ArrayList;
import java.util.List;

import com.joe.phonebook.myenum.ChooseTreeHierarchy;
import com.joe.phonebook.myenum.ChooseWay;

/**
 * Created by Think on 2017/2/6.
 */

public class BaseApplication {
    public Context context;
    public static BaseApplication app;
    public ChooseTreeHierarchy mChooseTreeHierarchy;
    public ChooseWay mChooseWay;
    public ArrayList<SYS_User> checkUserCommon;
    public ArrayList<SYS_User> checkUserHierarchy;
    public ArrayList<SYS_User> checkUserTree;

    public SYS_Department SYS_Department_;
    private List<T_UserRelationship> contactList;
    private ArrayList<SYS_User> userList;

    public BaseApplication(Context context) {
        this.context = context;
    }
    public static BaseApplication getApplication(Context context){
        if(app == null){
            app = new BaseApplication(context);
        }
        return app;

    }
    public void onCreate(){
        DBManager.getInstance(context);

    }

    public ArrayList<SYS_User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<SYS_User> userList) {
        this.userList = userList;
    }

    public List<T_UserRelationship> getContactList() {
        return contactList;
    }

    public void setContactList(List<T_UserRelationship> contactList) {
        this.contactList = contactList;
    }

    public SYS_Department getSYS_Department_() {
        return SYS_Department_;
    }

    public void setSYS_Department_(SYS_Department SYS_Department_) {
        this.SYS_Department_ = SYS_Department_;
    }
    public void clear(){
        SYS_Department_ = new SYS_Department();
        userList = new ArrayList<SYS_User>();
    }

}
