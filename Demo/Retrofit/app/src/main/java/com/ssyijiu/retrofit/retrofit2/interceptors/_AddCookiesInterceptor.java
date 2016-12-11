package com.ssyijiu.retrofit.retrofit2.interceptors;

import com.ssyijiu.library.MLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.ssyijiu.retrofit.retrofit2.interceptors._ReceivedCookiesInterceptor.cookies;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * <p>
 * 通过拦截器实现 cookie管理，会使用每次请求带上所有的cookie(访问A服务器，带上了B服务器的cookie)
 */

public class _AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (String cookie : cookies) {
            MLog.i(cookie);
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}
