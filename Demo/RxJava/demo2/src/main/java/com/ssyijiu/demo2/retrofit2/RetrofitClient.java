package com.ssyijiu.demo2.retrofit2;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ssyijiu.demo2.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ssyijiu on 2017/1/24.
 * Github : ssyijiu
 * Email  : lxmyiiu@163.com
 */

public class RetrofitClient {

    private static Retrofit INSTANCE;

    private static void initRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new StethoInterceptor());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        INSTANCE = new Retrofit.Builder().baseUrl(Api.baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private static Retrofit getInstance() {
        if (INSTANCE == null) {
            initRetrofit();
        }
        return INSTANCE;
    }

    public static Api getApi() {
        return getInstance().create(Api.class);
    }
}
