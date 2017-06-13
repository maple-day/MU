package com.maplechen.mu.Activity;


import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/4/15.
 */
public class StartHomeActivity {

    private static Intent intent;

    public static void stratActivity(Context context, String text){

        if (text.equals("学院新闻")){
            intent = new Intent(context, YcuNewBaseActivity.class);
            intent.putExtra("title","学院新闻");
            intent.putExtra("url","B20110603182356");
        }else if (text.equals("通知公告")){
            intent = new Intent(context, YcuNewBaseActivity.class);//B20110603182545.html
            intent.putExtra("title","通知公告");
            intent.putExtra("url","B20110603182545");
        }else if(text.equals("学术动态")){
             intent = new Intent(context, YcuNewBaseActivity.class);//B20110603182734.html  B20110603182734_1.html
             intent.putExtra("title","学术动态");
             intent.putExtra("url","B20110603182734");
        }else if (text.equals("系部信息")){ //B20110603182721.html
            intent = new Intent(context, YcuNewBaseActivity.class);
            intent.putExtra("title","系部信息");
            intent.putExtra("url","B20110603182721");
        }else if (text.equals("学院简介")){//http://www.ycu.edu.cn/B20110603180530.html
            intent = new Intent(context, YcuBsicFactsActivity.class);
            intent.putExtra("title","学院简介");
            intent.putExtra("url","B20110603180530");
            intent.putExtra("history","学院简介");
        }else if (text.equals("历史沿革")){//http://www.ycu.edu.cn/B20110603180615.html
            intent = new Intent(context, YcuBsicFactsActivity.class);
            intent.putExtra("title","历史沿革");
            intent.putExtra("url","B20110603180615");
            intent.putExtra("history","历史沿革");
        }else if (text.equals("学生社团")){
            intent = new Intent(context, YcuClubActivity.class);
            intent.putExtra("title","学生社团");
            intent.putExtra("url","B20110603180615");
        }else if (text.equals("专业介绍")){
            intent = new Intent(context, YcuNewBaseActivity.class);
            intent.putExtra("title","专业介绍");
            intent.putExtra("url","B20110603181304");
        }else if (text.equals("给院长写信")){

        }else if(text.equals("教务通知通告")){//http://www.ycu.edu.cn/jwc/B20110614212300.html

        }else if(text.equals("教务常用下载")){

        }
        context.startActivity(intent);
    }
}
