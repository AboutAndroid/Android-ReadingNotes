package com.ssyijiu.retrofit.retrofit2;

import android.content.Context;
import android.os.Environment;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.ssyijiu.retrofit.BuildConfig;
import com.ssyijiu.retrofit.app.App;
import com.ssyijiu.retrofit.retrofit2.interceptors.LoggingInterceptor;
import com.ssyijiu.retrofit.retrofit2.interceptors.TokenInterceptor;
import com.ssyijiu.retrofit.retrofit2.interceptors.UserAgentInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by ssyijiu on 2016/11/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum OKHttpFactory {
    INSTANCE;

    private final Context mContext = App.getContext();
    private final OkHttpClient mOkHttpClient;

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final int TIMEOUT_WRITE = 20;

    private static final int CACHE_SIZE = 10 * 1024 * 1024;


    OKHttpFactory() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()

                // 设置超时和重连
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

                // 设置请求头(UA)
                .addInterceptor(new UserAgentInterceptor("ssyijiu-retrofit"))

                // 添加 log 信息拦截器
                .addInterceptor(getLoggingInterceptor())

                // 添加公共参数 token
                .addInterceptor(new TokenInterceptor())

                // 设置 Cookie
                .cookieJar(new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(App.getContext())));


        mOkHttpClient = builder.build();

        //stetho,可以在chrome中查看请求
//                .addNetworkInterceptor(new StethoInterceptor())


    }

    private Interceptor getLoggingInterceptor() {

        return new LoggingInterceptor(BuildConfig.DEBUG);
//        return new HttpLoggingInterceptor()
//                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private Cache getCacheDir() {
        return new Cache(getDiskCache(mContext, "retrofit"), CACHE_SIZE);
    }

    /**
     * 获取磁盘缓存文件位置
     *
     * @param context
     * @param fileName
     * @return sd卡可用路径为 /sdcard/Android/data/<application package>/cache/fileName
     * sd卡不可用路径为 /data/data/<application package>/cache/fileName
     */
    public File getDiskCache(Context context, String fileName) {

        String cachePath;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + fileName);
    }
}
