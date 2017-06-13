package com.maplechen.mu.Common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/4/13.
 */
public class ActivityManager {

    public static ActivityManager appManger = null;
    private Stack<Activity> stack=new Stack<Activity>();// 栈


    public static ActivityManager getInstance() {
        if (appManger == null) {
            synchronized (ActivityManager.class) {
                if (appManger == null) {
                    appManger = new ActivityManager();
                }
            }
        }
        return appManger;
    }

    public void addActivity(Activity activity) {
        stack.add(activity);
    }

    public void removeActivity(Activity activity) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity activity2 = stack.get(i);
            if (activity2.getClass().equals(activity.getClass())) {
                activity.finish();
                stack.remove(activity);
                break;
            }
        }
    }

    public void removeCurrent() {
        Activity element = stack.lastElement();
        element.finish();
        stack.remove(element);
    }

    public void removeAll() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity activity = stack.get(i);
            activity.finish();
            stack.remove(activity);
        }
    }

    public int getSize() {
        return stack.size();
    }
}
