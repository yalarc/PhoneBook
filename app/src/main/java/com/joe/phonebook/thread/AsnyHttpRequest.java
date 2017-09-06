package com.joe.phonebook.thread;

import android.content.Context;

import com.joe.phonebook.listener.ObserverCallBackListener;
import com.joe.phonebook.utils.ThreadPoolUtils;

/**
 * 网络请求封装类
 * @author joe
 * Created by Think on 2017/1/17.
 *
 */
public class AsnyHttpRequest {

    public static void requestPost(Context context, Object entity, String url, int requestType, ObserverCallBackListener observerCallBackListener){
        if(!NetWork.isNetWork(context)){
            observerCallBackListener.notNetwork();
        }else {
            ThreadPoolUtils.execute(new RequestThread(context,entity,url,requestType,observerCallBackListener));

        }
    }
    public static void requestGet(Context context,Object entity,String url,int requestType,ObserverCallBackListener observerCallBackListener){
        if(!NetWork.isNetWork(context)){
            observerCallBackListener.notNetwork();
        }else {
            ThreadPoolUtils.execute(new RequestThread(context,entity,url,requestType,observerCallBackListener));

        }
    }
    public static void fileUnzip(Context context,String zipName,ObserverCallBackListener observerCallBackListener){
        ThreadPoolUtils.execute(new FileThread(context,zipName,observerCallBackListener));

    }
}
