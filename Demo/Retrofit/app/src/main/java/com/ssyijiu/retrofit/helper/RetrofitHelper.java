package com.ssyijiu.retrofit.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxm on 2016/10/13.
 */
public class RetrofitHelper {

    public void build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
