package com.maplechen.mu.Http.Interface;

import com.maplechen.mu.Domain.LiveBaseBean;
import com.maplechen.mu.Domain.LiveListItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/29.
 */
public interface Live {
    int LIMIT = 20;

    //请求获取不同游戏的直播列表
    @GET("/api/live/list/")
    Observable<LiveBaseBean<List<LiveListItemBean>>> getLiveList(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("live_type") String live_type,
            @Query("game_type") String game_type
    );

}
