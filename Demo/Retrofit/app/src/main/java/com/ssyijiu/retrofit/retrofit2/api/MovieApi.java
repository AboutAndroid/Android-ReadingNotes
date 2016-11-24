package com.ssyijiu.retrofit.retrofit2.api;

import com.ssyijiu.retrofit.bean.MovieBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by ssyijiu on 2016/10/13.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


public interface MovieApi {
    /**
     * https://api.douban.com/v2/movie/top250?start=0&count=11
     */

    /* GET */
    @GET("top250")
    Call<MovieBean> getTop250();

    /* GET */
    @GET("top250")  // 跨过 baseUrl，直接访问 url
    Call<MovieBean> getTop250(@Url String url, int time);

    /* GET with param*/
    @GET("top250?start=0&count=3")
    Call<MovieBean> getTop250_3();

    /* Path */
    @GET("{xxx}?start=0&count=4")
    Call<MovieBean> getTop250(@Path("xxx") String xxx);

    /* Query */
    @GET("top250")
    Call<MovieBean> getTop250(@Query("start") int start, @Query("count") int count);

    /* QueryMap */
    @GET("top250")
    Call<MovieBean> getTop250(@QueryMap Map<String,Integer> count);


}
