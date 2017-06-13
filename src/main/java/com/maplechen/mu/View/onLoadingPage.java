package com.maplechen.mu.View;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.maplechen.mu.Domain.ImgInfo;
import com.maplechen.mu.Domain.NewInfo;
import com.maplechen.mu.Http.MySubscriber;
import com.maplechen.mu.Http.MySubscriberImg;
import com.maplechen.mu.Http.MySubscriberNew;
import com.maplechen.mu.Http.RetrofitClient;
import com.maplechen.mu.Http.RetrofitImg;
import com.maplechen.mu.Http.RetrofitNew;
import com.maplechen.mu.R;
import com.maplechen.mu.Utils.UIUtils;

/**
 * Created by Administrator on 2017/4/14.
 */
public abstract class  onLoadingPage extends FrameLayout {

    private final Context context;
    private LayoutParams params;
    private View emptyView;
    private View errorView;
    private View loadingView;
    private View successView;

    private static final int PAGE_LOADING_STATE = 1;// 正在加载布局
    private static final int PAGE_ERROR_STATE = 2;// 加载失败
    private static final int PAGE_EMPTY_STATE = 3;// 加载数据为空
    private static final int PAGE_SUCCESS_STATE = 4;// 加载成功
    private static int PAGE_CURRENT_STATE = 1;

    private ReasultState reasultState = null;

    private String NEWS ;


    public onLoadingPage(Context context ) {
        this(context, null);
    }

    public onLoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public onLoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (emptyView == null) {
            emptyView = UIUtils.inflate(R.layout.page_empty);
            addView(emptyView,params);
        }
        if (errorView == null) {
            errorView = UIUtils.inflate(R.layout.page_error);
            addView(errorView,params);
        }
        if (loadingView == null) {
            loadingView = UIUtils.inflate(R.layout.page_loading);
            addView(loadingView,params);
        }
        showSafeView();
    }

    private void showSafeView() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? View.VISIBLE : View.GONE);
        if(successView == null){
            successView= View.inflate(context, getLayoutId(), null);
            addView(successView);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE?View.VISIBLE:View.GONE);
    }
    public void show() {
        NEWS =  getType();
        if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }
        String url = getUrl();
        if (TextUtils.isEmpty(url)) {
            reasultState = ReasultState.SUCCESS;
            reasultState.setContent("");
            loadPage();
        }else {
            if(NEWS != null){
                if(NEWS.equals("qiwen")||NEWS.equals("military")||NEWS.equals("meinv"
                )||NEWS.equals("huabian")||NEWS.equals("guonei")
                        ||NEWS.equals("world")||NEWS.equals("it")||NEWS.equals("social")
                        ||NEWS.equals("tiyu")||NEWS.equals("keji")||NEWS.equals("travel")||NEWS.equals("startup")){
                    RetrofitNew.getInstance(context).getJson(NEWS, new MySubscriberNew() {
                        @Override
                        public void onFailure(Throwable e) {
                            Log.e("tag", e.toString());
                            reasultState = ReasultState.ERROR;
                            reasultState.setContent("");
                            loadPage();
                        }

                        @Override
                        public void OnSuccess(NewInfo newInfo) {
                            if(newInfo.code.equals("200")) {
                                Gson gson = new Gson();
                                String html = gson.toJson(newInfo);
                                if (TextUtils.isEmpty(html)) {
                                    reasultState = ReasultState.EMPITY;
                                    reasultState.setContent("");
                                } else {
                                    reasultState = ReasultState.SUCCESS;
                                    reasultState.setContent(html);
                                }
                                loadPage();
                            }else {
                                reasultState = ReasultState.ERROR;
                                reasultState.setContent("");
                                loadPage();
                            }
                        }
                    });
                }else {
                    RetrofitImg.getInstance(context).getJson(url, new MySubscriberImg() {
                        @Override
                        public void onFailure(Throwable e) {
                            Log.e("tag", e.toString());
                            reasultState = ReasultState.ERROR;
                            reasultState.setContent("");
                            loadPage();
                        }

                        @Override
                        public void OnSuccess(ImgInfo imgInfo) {
                            if(imgInfo.status.equals("true")) {
                                Gson gson = new Gson();
                                String html = gson.toJson(imgInfo);
                                if (TextUtils.isEmpty(html)) {
                                    reasultState = ReasultState.EMPITY;
                                    reasultState.setContent("");
                                } else {
                                    reasultState = ReasultState.SUCCESS;
                                    reasultState.setContent(html);
                                }
                                loadPage();
                            }else {
                                reasultState = ReasultState.ERROR;
                                reasultState.setContent("");
                                loadPage();
                            }
                        }
                    });
                }

            }else {
                RetrofitClient.getInstance(context).getYcuHtml(new MySubscriber() {
                    @Override
                    public void onErrorMsg(Throwable e) {
                        Log.e("tag", e.toString());
                        reasultState = ReasultState.ERROR;
                        reasultState.setContent("");
                        loadPage();
                    }

                    @Override
                    public void onSuccessMsg(String html) {
                        if (TextUtils.isEmpty(html)) {
                            reasultState = ReasultState.EMPITY;
                            reasultState.setContent("");
                        } else {
                            reasultState = ReasultState.SUCCESS;
                            reasultState.setContent(html);
                        }
                        loadPage();
                    }
                }, url);
            }
        }


    }

    protected abstract String getType();

    protected void loadPage() {
        switch (reasultState) {
            case ERROR:
                PAGE_CURRENT_STATE = PAGE_ERROR_STATE;
                break;
            case EMPITY:
                PAGE_CURRENT_STATE = PAGE_EMPTY_STATE;
                break;
            case SUCCESS:
                PAGE_CURRENT_STATE = PAGE_SUCCESS_STATE;
                break;
        }
        showSafeView();
        if (PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE) {
            onSuccess(reasultState, successView);
        }
    }

    protected abstract void onSuccess(ReasultState reasultState, View successView);

    protected abstract String getUrl();
    protected abstract int getLayoutId();

    public enum ReasultState {

        ERROR(PAGE_ERROR_STATE), EMPITY(PAGE_EMPTY_STATE), SUCCESS(
                PAGE_SUCCESS_STATE);

        private int state;
        private String content;

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private ReasultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
