package com.ssyijiu.retrofit.helper;

import com.ssyijiu.retrofit.api.MovieApi;
import com.ssyijiu.retrofit.api.MultiApi;
import com.ssyijiu.retrofit.api.PostApi;

/**
 * Created by ssyijiu on 2016/11/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum ApiFactory {
    INSTANCE;
    public final MovieApi API_MOVIE;
    public final PostApi API_POST;
    public final MultiApi API_MULTI;
    ApiFactory() {

        API_MOVIE = RetrofitClient.MOVIE_INSTANCE.getRetrofit().create(MovieApi.class);
        API_POST = RetrofitClient.MULTI_INSTANCE.getRetrofit().create(PostApi.class);
        API_MULTI = RetrofitClient.MULTI_INSTANCE.getRetrofit().create(MultiApi.class);
    }
}
