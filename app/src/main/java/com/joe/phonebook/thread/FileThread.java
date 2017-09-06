package com.joe.phonebook.thread;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.joe.phonebook.common.Common;
import com.joe.phonebook.listener.ObserverCallBackListener;
import com.joe.phonebook.utils.CompressUtils;

import java.io.File;

/**
 * Created by Think on 2017/1/17.
 */

public class FileThread implements Runnable{

    private Context context;
    private String zipName;
    private ObserverCallBackListener observerCallBackListener;
    private final String PASSWORD = "password";
    private static final String TAG = "FileThread";

    public FileThread(Context context, String zipName, ObserverCallBackListener observerCallBackListener) {
        this.context = context;
        this.zipName = zipName;
        this.observerCallBackListener = observerCallBackListener;
    }


    @Override
    public void run() {
        Message message = new Message();

        try {
            String destination = Common.PHONE_BOOK;
            File srcFile = new File (destination+File.separator+zipName);
            try {

                File[] files = CompressUtils.unZip(srcFile,destination,PASSWORD);
                Log.d(TAG, "run: "+files[0].getPath());
                //解析TXT文件，并插入数据库

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            String o = (String) message.obj;
            switch (message.what){
                case 1:
                    observerCallBackListener.callBackMainUI(o);
                    break;
                case 2:
                    observerCallBackListener.fail(o);
                    break;
            }
        }
    };
}
