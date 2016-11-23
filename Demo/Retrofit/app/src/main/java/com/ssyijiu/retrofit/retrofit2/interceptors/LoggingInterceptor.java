package com.ssyijiu.retrofit.retrofit2.interceptors;


import android.text.TextUtils;

import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoggingInterceptor implements Interceptor {

    private boolean isDebug = false;

    public LoggingInterceptor(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request originalRequest = chain.request();

        if (!isDebug) {
            return chain.proceed(originalRequest);
        }

        long t1 = System.nanoTime();
        XLog.i(String.format(Locale.getDefault(), "Sending request %s on %s%n%s",
                originalRequest.url(), chain.connection(), originalRequest.headers()));


        Response response = chain.proceed(chain.request());


        long t2 = System.nanoTime();
        XLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType mediaType = response.body().contentType();
        String content = response.body().string();  // 从流中获取信息

        if (!TextUtils.isEmpty(content)) {
            XLog.json(content);
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
