package com.ssyijiu.retrofit.helper;

import com.ssyijiu.retrofit.api.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ssyijiu on 2016/11/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum RetrofitClient {

    MOVIE_INSTANCE(API.MOVIE_API_BASEURL),
    MULTI_INSTANCE(API.MULTI_API_BASEURL);

    private final Retrofit retrofit;
    RetrofitClient(String baseUrl) {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                //baseUrl
                .baseUrl(baseUrl)
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
