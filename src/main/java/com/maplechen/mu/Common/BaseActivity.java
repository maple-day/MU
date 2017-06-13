package com.maplechen.mu.Common;

import android.os.Bundle;
import android.view.View;

import com.maplechen.mu.View.onLoadingPage;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/13.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String  url = getIntent().getStringExtra("url");
        String  history = getIntent().getStringExtra("history");
        if(url!=null &&!(url.equals(""))){
            getUrl(url);
        }if(history!=null && !(history.equals(""))){
            getHistory(history);
        }
        onLoadingPage onLoadingPage = new onLoadingPage(this) {
            @Override
            protected String getType() {
                return null;
            }

            @Override
            protected void onSuccess(ReasultState reasultState, View successView) {
                    ButterKnife.bind(BaseActivity.this,successView);
                    initData(reasultState.getContent());
                    initView();
            }

            @Override
            protected String getUrl() {
                return setUrl();
            }

            @Override
            protected int getLayoutId() {
                return BaseActivity.this.getLayoutId();
            }
        };
        onLoadingPage.show();
        initBar();
        setContentView(onLoadingPage);
        ActivityManager.getInstance().addActivity(this);

    }
    //判断是不是历史沿革
    protected void getHistory(String history) {

    }

    //url :B20110603180530  链接前面字符串     完整:B20110603180530.html
    protected  void getUrl(String url){

    };

    protected abstract String setUrl();
    public abstract int getLayoutId();
    protected abstract void initData(String content);
    public abstract void initView();
    public abstract void initBar() ;
    /**
     * 关闭当前的Activity
     */
    public void closeCurrent() {
        ActivityManager.getInstance().removeCurrent();
    }

}
