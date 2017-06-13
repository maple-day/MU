package com.maplechen.mu.Http;

import android.content.Context;
import android.text.TextUtils;

import com.maplechen.mu.Http.Interface.YcuHome;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/5/8.
 */
public class RetrofitJWC {
    private static final int DEFAULT_TIMEOUT = 5;

    private YcuHome apiService;


    public static String baseUrl = "http://202.207.232.9/";

    private static Context mContext;

    private static RetrofitJWC sNewInstance;
    private static class SingletonHolder {
        private static RetrofitJWC INSTANCE = new RetrofitJWC(mContext);
    }

    public static RetrofitJWC getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitJWC getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitJWC(context, url);
        return sNewInstance;
    }

    private RetrofitJWC(Context context) {
        this(context, null);
    }

    private RetrofitJWC(Context context, String url) {

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
}
