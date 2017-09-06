package com.joe.phonebook.thread;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.joe.phonebook.listener.ObserverCallBackListener;

/**
 * Created by Think on 2017/1/17.
 */

public class RequestThread implements Runnable {

    private Context context;
    private Object entity;
    private String url;
    private int requestType;
    private ObserverCallBackListener observerCallBackListener;

    public RequestThread(Context context, Object entity, String url, int requestType, ObserverCallBackListener observerCallBackListener) {
        this.context = context;
        this.entity = entity;
        this.url = url;
        this.requestType = requestType;
        this.observerCallBackListener = observerCallBackListener;
    }

    @Override
    public void run() {
        switch (requestType) {
            case HttpCollocate.POST:
                Message message = new Message();
                try {
                    String request = HttpCollocate.requestByHttpPost(entity, url);
                    message.what = 1;
                    message.obj = request;
                    handler.sendMessage(message);


                } catch (Exception e) {
                    message.what = 2;
                    message.obj = e.getMessage();
                    handler.sendMessage(message);
                    e.printStackTrace();

                }
                break;
            case HttpCollocate.GET:
                Message message1 = new Message();
                try {
                    String request = HttpCollocate.requestGet(url);
                    message1.what = 1;
                    message1.obj = request;
                    handler.sendMessage(message1);
                } catch (Exception e) {
                    message1.what = 2;
                    message1.obj = e.getMessage();
                    handler.sendMessage(message1);
                    e.printStackTrace();
                }
                break;
        }


    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            String o = (String) message.obj;
            switch (message.what) {
                case 1:
                    observerCallBackListener.success(o);
                case 2:
                    observerCallBackListener.fail(o);
                case 3:
                    break;
            }
        }

        {
        }
    };
}
