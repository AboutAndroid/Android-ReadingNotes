package com.ssyijiu.retrofit.api;

import com.ssyijiu.retrofit.resp.GankResp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lxm on 2016/10/13.
 */
public interface GankApi {
    String baseUrl = "http://gank.io/";
    /* GET */
//    @GET("api/data/Android/10/1")
//    @GET("api/data/Android/10/1?param=value") // 带参数
//    Call<GankResp> getGankInfo();

    /* Path */
//    @GET("api/data/Android/10/{day}")
//    Call<GankResp> getGankInfo(@Path("day") String day);


    /* Query、QueryMap*/
//    @GET("api/data/Android/10/{day}")
//    Call<GankResp> getGankInfo(@Path("day") String day, @Query("param") String value);
//    Call<GankResp> getGankInfo(@Path("day") String day, @QueryMap Map<String,String> param);

}
