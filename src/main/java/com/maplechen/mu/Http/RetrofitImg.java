package com.maplechen.mu.Http;

import android.content.Context;
import android.text.TextUtils;

import com.maplechen.mu.Domain.ImgInfo;
import com.maplechen.mu.Http.Interface.Img;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/9.
 */
public class RetrofitImg {
    private static final int DEFAULT_TIMEOUT = 5;

    private Img apiService;


    public static String baseUrl = "http://www.tngou.net/";

    private static Context mContext;

    private static RetrofitImg sNewInstance;
    private static class SingletonHolder {
        private static RetrofitImg INSTANCE = new RetrofitImg(mContext);
    }

    public static RetrofitImg getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitImg getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitImg(context, url);
        return sNewInstance;
    }

    private RetrofitImg(Context context) {
        this(context, null);
    }

    private RetrofitImg(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(Img.class);
    }

    public void getJson(String type, Subscriber<ImgInfo> subscriber){
        apiService.getImg(type)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
