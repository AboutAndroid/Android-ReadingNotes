package com.ssyijiu.retrofit.retrofit2;

import com.ssyijiu.retrofit.retrofit2.api.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RetrofitFactory {

    private Retrofit mRetrofit;

    /**
     * 服务器有几个 HOST, 创建几个 retrofit 单例。
     */
    public static final class INSTANCE {
        public static Retrofit MULTI_INSTANCE = new RetrofitFactory(API.MULTI_API_BASEURL).mRetrofit;
        public static Retrofit MOVIE_INSTANCE = new RetrofitFactory(API.MOVIE_API_BASEURL).mRetrofit;
    }


    private RetrofitFactory(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                //baseUrl
                .baseUrl(baseUrl)
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
