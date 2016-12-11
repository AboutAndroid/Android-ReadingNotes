package com.ssyijiu.retrofit.retrofit2;

import com.google.gson.Gson;
import com.ssyijiu.retrofit.retrofit2.api.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RetrofitFactory {

    private Retrofit RETROFIT;
    private Retrofit.Builder BUILDER;

    /**
     * 服务器有几个 HOST, 创建几个 retrofit 单例。
     * 当一个单例被加载时，其他单例也会被加载到内存。
     */
    public static final class INSTANCE {
        public static final Retrofit MULTI_INSTANCE = new RetrofitFactory(API.MULTI_API_BASEURL).RETROFIT;
        public static final Retrofit MOVIE_INSTANCE = new RetrofitFactory(API.MOVIE_API_BASEURL).RETROFIT;
    }

    private RetrofitFactory(String baseUrl) {

        if(BUILDER == null) {
            BUILDER = new Retrofit.Builder()
                    //设置OKHttpClient
                    .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                    //baseUrl
                    .baseUrl(baseUrl)
                    //gson转化器
                    .addConverterFactory(GsonConverterFactory.create(mGson));
        }

        RETROFIT = BUILDER.baseUrl(baseUrl).build();
    }

    private static final Gson mGson = new Gson();

}
