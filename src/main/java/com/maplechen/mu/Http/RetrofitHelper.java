package com.maplechen.mu.Http;

import com.maplechen.mu.Utils.UIUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: LMJ
 * date: 2016/9/1
 */
public class RetrofitHelper {

    public static final String BASE_USER_URL = "http://api.douban.com/v2/movie/";
    private static final String BASE_EXPLORE_URL = "http://c.m.163.com";
    private static final String BASE_LIVE_URL = "http://api.maxjia.com";
    private static final String BASE_PANDA_URL = "http://www.panda.tv";

    private static Retrofit explore = null;
    private static Retrofit live = null;
    private static Retrofit user = null;
    private static Retrofit panda = null;

    public static Retrofit getExploreHelper() {
        if (explore == null) {
            try {
                synchronized (RetrofitHelper.class) {
                    if (explore == null) {
                        File httpCacheDirectory = new File(UIUtils.getContext().getCacheDir(), "exploreCache");
                        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);//缓存10MB
                        OkHttpClient.Builder httpBuidler = new OkHttpClient().newBuilder();
                        httpBuidler.cache(cache)
                                .connectTimeout(10, TimeUnit.SECONDS)//连接超时限制5秒
                                .writeTimeout(10, TimeUnit.SECONDS)
                                .readTimeout(10, TimeUnit.SECONDS);
                        //添加拦截器
//                                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//离线缓存

                        explore = new Retrofit.Builder()
                                .client(httpBuidler.build())
                                .baseUrl(BASE_EXPLORE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .build();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return explore;
    }

    public static Retrofit getLiveHelper() {
        if (live == null) {
            synchronized (RetrofitHelper.class) {
                if (live == null) {
                    live = new Retrofit.Builder()
                            .baseUrl(BASE_LIVE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return live;
    }

    public static Retrofit getPandaHelper() {
        if (panda == null) {
            synchronized (RetrofitHelper.class) {
                panda = new Retrofit.Builder()
                        .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                        .baseUrl(BASE_PANDA_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return panda;
    }

}
