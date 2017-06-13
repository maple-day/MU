package com.maplechen.mu.Http;

import android.content.Context;
import android.text.TextUtils;

import com.maplechen.mu.Domain.NewInfo;
import com.maplechen.mu.Http.Interface.News;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/9.
 */
public class RetrofitNew {
    private static final int DEFAULT_TIMEOUT = 5;

    private News apiService;


    public static String baseUrl = "https://api.tianapi.com/";

    private static Context mContext;

    private static RetrofitNew sNewInstance;
    private static class SingletonHolder {
        private static RetrofitNew INSTANCE = new RetrofitNew(mContext);
    }

    public static RetrofitNew getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitNew getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitNew(context, url);
        return sNewInstance;
    }

    private RetrofitNew(Context context) {
        this(context, null);
    }

    private RetrofitNew(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(News.class);
    }

    public void getJson(String type, Subscriber<NewInfo> subscriber){
        apiService.getJson(type)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
