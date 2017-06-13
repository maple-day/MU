package com.maplechen.mu.Http;

import android.content.Context;
import android.text.TextUtils;

import com.maplechen.mu.Http.Interface.YcuHome;

import java.util.Map;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/15.
 */
public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 5;

    private YcuHome apiService;


    public static String baseUrl = "http://www.ycu.edu.cn/";

    private static Context mContext;

    private static RetrofitClient sNewInstance;
    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);
    }

    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitClient(context, url);
        return sNewInstance;
    }

    private RetrofitClient(Context context) {
        this(context, null);
    }

    private RetrofitClient(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(YcuHome.class);
    }
    public Observable.Transformer schedulersTransformer(){
        return  new Observable.Transformer(){

            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //学院主页
    public void getYcuHtml(Subscriber<ResponseBody> subscriber, String url){
        apiService.getHtml(url)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 点击入教务系统主页
     */
    public void loginHome(Subscriber<ResponseBody> subscriber){
        apiService.loginHome().subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取验证码
     * @param subscriber
     */
    public void getValidate(Subscriber<ResponseBody> subscriber){
        apiService.getValidate(new Random().nextInt()).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 点击登陆
     */
    public void getHomeLogin(Map<String,String> map,Subscriber<ResponseBody> subscriber){
        apiService.getHomeLogin(map).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 教务处
     */
    public void jWC(Map<String,String> map ,Subscriber<ResponseBody> subscriber){
        apiService.getJWC(map).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void Score(Subscriber<ResponseBody> subscriber){
        apiService.getScore().observeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
