package com.ssyijiu.demo2.retrofit2;

import com.ssyijiu.demo2.resp.MovieResp;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ssyijiu on 2017/1/24.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public interface Api {

    String baseUrl = "https://api.douban.com/v2/movie/";


    /* GET */
    @GET("top250")
    Observable<MovieResp> getTop250();

    /* Query */
    @GET("top250")
    Observable<MovieResp> getTop250(@QueryMap Map<String, Integer> count);

    /* QueryMap */
    @GET("top250")
    Observable<MovieResp> getTop250(@Query("start") String start, @Query("count") String count);
}
