package com.maplechen.mu.Http.Interface;

import com.maplechen.mu.Domain.NewInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface News {

    @GET("{type}/?key=e706c9c4592e33a847a54f757f011e7c&num=20")
    Observable<NewInfo> getJson(@Path("type")String type);

}
