package com.joe.phonebook.listener;

/**
 * Created by Think on 2017/1/17.
 */

public interface ObserverCallBackListener {
    void success(String requestMessage);
    void fail(String exceptionMessage);
    void notNetwork();
    void callBackMainUI(String successMessage);
}
