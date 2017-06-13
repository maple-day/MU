package com.maplechen.mu.Http.Interface;

import com.maplechen.mu.Domain.ImgInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface Img {

    @GET("tnfs/api/news")
    Observable<ImgInfo> getImg(@Query("id") String id);
}
