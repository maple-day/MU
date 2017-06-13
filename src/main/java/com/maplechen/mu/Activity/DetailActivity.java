package com.maplechen.mu.Activity;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maplechen.mu.Common.BaseActivity;
import com.maplechen.mu.R;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.detail_fm)
    FrameLayout detailFm;
    @BindView(R.id.detail_pb)
    ProgressBar detailPb;

    private WebView webView;

    String url = "";

    @Override
    protected void getUrl(String url) {
        this.url = url;
    }

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initData(String content) {

    }

    @Override
    public void initView() {
        webView = new WebView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        detailFm.addView(webView, layoutParams);
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 表示支持js
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击缩放
        webView.setInitialScale(25);
        webView.setWebViewClient(new WebViewClient() {
            //网页开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                detailPb.setVisibility(View.VISIBLE);
            }

            //网页加载结束
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                detailPb.setVisibility(View.GONE);
            }

            //所有跳转的链接都会在此方法中回调
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request)
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            /**
             * 进度发生变化
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                System.out.println("加载进度:" + newProgress);
                super.onProgressChanged(view, newProgress);
            }

            /**
             * 获取网页标题
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("网页标题:" + title);
                super.onReceivedTitle(view, title);
            }
        });
        webView.loadUrl(url);

    }

    @Override
    public void initBar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.closeCurrent();
        }
        return super.onOptionsItemSelected(item);
    }


}
