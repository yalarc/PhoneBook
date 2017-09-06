package com.joe.phonebook.myenum;

/**
 * Created by Think on 2016/6/21.
 * 设置头像方式
 */
public enum PeopleHeadEnum {
    SURNAME(1),//姓
    THENAME(2),//名字
    DEFAULT(3);//默认的
    private int value;
    private PeopleHeadEnum(int value){
        this.value = value;
    }
    public static PeopleHeadEnum getHead(int code){
        for (PeopleHeadEnum c : PeopleHeadEnum.values()) {
             if (c.value == code) {
                 return c;
             }
        }
        return null;
    }
}
