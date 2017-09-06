package com.joe.phonebook.api;

import com.joe.phonebook.base.BaseFragment;
import com.joe.phonebook.fragment.PhoneBookFragment;
import com.joe.phonebook.myenum.ChooseWayEnum;
import com.joe.phonebook.myenum.PeopleHeadEnum;
import com.joe.phonebook.utils.Contant;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化接口操作和全局数据
 * @author joe
 * Created by Think on 2017/1/17.
 */

public class PhoneInit {
    /**单例*/
    public static PhoneInit phoneInit;
    /**设置一个默认选择 人员*/
    private ChooseWayEnum mChooseWayEnum = ChooseWayEnum.PEOPLECHOOSE;

    /**
     * 初始化进入的方式
     */
    public String phoneType = Contant.LOGIN_CHILD;

    /**
     * 存放Fragment的Map键值对
     */
    private Map<String,BaseFragment> myMap = new HashMap<String,BaseFragment>();

    /**
     * 第一个主页面
     */
    private PhoneBookFragment phoneBookFragment;

    /**
     * 设置电话号码的隐藏位数
     */
    private String[] cellPhoneLength = new String[2];

    /**
     * 设置进入的方式，默认一个
     */
    private PeopleHeadEnum peopleHeadEnum = PeopleHeadEnum.THENAME;


    /**创建单例*/
    public static PhoneInit getInstance(){
        if(phoneInit== null){
            phoneInit = new PhoneInit();
        }
        return phoneInit;
    }

    /**
     * 获取设置的路由选择
     */

    public ChooseWayEnum getChooseWayEnum(){
        return mChooseWayEnum;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Map<String ,BaseFragment> getMyMap(){
        return myMap;
    }

    public PhoneBookFragment getPhoneBookFragment() {
        return phoneBookFragment;
    }

    public void setPhoneBookFragment(PhoneBookFragment phoneBookFragment) {
        this.phoneBookFragment = phoneBookFragment;
    }

    public String[] getCellPhoneLength() {
        return cellPhoneLength;
    }

    public void setCellPhoneLength(String[] cellPhoneLength) {
        this.cellPhoneLength = cellPhoneLength;
    }

    public PeopleHeadEnum getPeopleHeadEnum() {
        return peopleHeadEnum;
    }

    public void setPeopleHeadEnum(PeopleHeadEnum peopleHeadEnum) {
        this.peopleHeadEnum = peopleHeadEnum;
    }
}
