package com.ssyijiu.retrofit.retrofit2.interceptors;

import com.ssyijiu.library.MLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.ssyijiu.retrofit.retrofit2.interceptors.ReceivedCookiesInterceptor.cookies;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        MLog.e(cookies);
        MLog.e(cookies.size());
        for (String cookie : cookies) {
            MLog.i(cookie);
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}
