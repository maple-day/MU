package com.maplechen.mu.Utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.maplechen.mu.Common.application;

/**
 * Created by Administrator on 2017/4/13.
 */
public class UIUtils {
    public static Context getContext() {
        return application.getContext();
    }

    public static Handler getHandler() {
        return application.getHandler();
    }

    public static int getMainThreadId() {
        return application.getMyTid();
    }

    public static int getColor(int home_back_selected) {
       return getContext().getResources().getColor(home_back_selected);
    }
    public static View inflate(int id) {
        return View.inflate(getContext(),id,null);
    }

    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    // 运行在主线程
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            r.run();
        } else {
            getHandler().post(r);
        }
    }
    // 获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
