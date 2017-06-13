package com.maplechen.mu.Common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by Administrator on 2017/4/13.
 */
public class application extends Application {

    private static  Context context;
    private static int myTid;
    private static Handler handler;



    @Override
    public void onCreate() {
        context = getApplicationContext();
        myTid = Process.myTid();
        handler = new Handler();
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }

    public static int getMyTid() {
        return myTid;
    }
    public static Handler getHandler() {
        return handler;
    }

}
