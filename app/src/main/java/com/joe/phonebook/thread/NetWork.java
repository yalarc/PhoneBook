package com.joe.phonebook.thread;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测网络
 * @author joe
 * Created by Think on 2017/1/17.
 */

public class NetWork {

    /**网络不可用*/
    public static final int NONE_WORK = 0;
    /**WIFI连接*/
    public static final int WIFI = 1;
    /**移动信号3G/4G连接*/
    public static final int MOBILE = 2;

    /**
     * 检测网络连接类型
     * @param context
     * @return NONE_WORK 没有网络,  WIFI 无线WIFI.  MOBILE 移动信号网络
     */
    public static int checkNetWorkType(Context context){
        if(!isNetWork(context)){
            return NetWork.NONE_WORK;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI).isConnectedOrConnecting()){
            return NetWork.WIFI;
        }else {
            return NetWork.MOBILE;
        }


    }

    /**
     * 检测是否有网络连接
     * @param context
     * @return true 有网络，false 无网络
     */
    public static boolean isNetWork(Context context){
        if(context != null) {
            // 1.获取连接设备管理器
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo == null || !networkInfo.isAvailable()){
                return false;
            }
            return true;
        }
        return false;
    }



}
