package com.ssyijiu.retrofit.helper;

import com.ssyijiu.retrofit.App;
import com.ssyijiu.retrofit.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ssyijiu on 2016/11/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum OKHttpFactory {
    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final int TIMEOUT_WRITE = 20;

    private static final int CACHE_SIZE = 10 * 1024 * 1024;


    OKHttpFactory() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 添加 log 信息拦截器
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }

        // 添加缓存
        builder.cache(getCacheDir());

        // 设置超时和错误重连
        builder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
               .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
               .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
               .retryOnConnectionFailure(true);

        okHttpClient = builder.build();

                //stetho,可以在chrome中查看请求
//                .addNetworkInterceptor(new StethoInterceptor())
                //添加UA
//                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                //必须是设置Cache目录
//                .cache(cache)
                //走缓存，两个都要设置
//                .addInterceptor(new OnOffLineCachedInterceptor())
//                .addNetworkInterceptor(new OnOffLineCachedInterceptor())

    }

    private Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private Cache getCacheDir() {
        return new Cache(App.getContext().getCacheDir(), CACHE_SIZE);
    }
}
