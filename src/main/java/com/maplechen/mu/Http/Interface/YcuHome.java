package com.maplechen.mu.Http.Interface;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface YcuHome {
//    @Headers("Content-Typei:text/x-www-form-urlencoded;charset=gb2313;")
    @GET("{url}")
    Observable<ResponseBody> getHtml(@Path("url") String url);

    @GET("B20110603182545.html")
    Observable<String> HomeYcuTongzhi();


    @GET(".")//登陆教务系统
    Observable<ResponseBody> loginHome();
    @GET("sys/ValidateCode.aspx")//获取验证码
    Observable<ResponseBody> getValidate(@Query("num") int num);


    @FormUrlEncoded
    @POST("_data/home_login.aspx")//点击登陆 教务系统
    Observable<ResponseBody> getHomeLogin(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("xscj/Stu_MyScore_rpt.aspx")
    Observable<ResponseBody> getJWC(@FieldMap Map<String,String> map);

    @GET("xscj/Stu_MyScore.aspx")
    Observable<ResponseBody> getScore();

}
