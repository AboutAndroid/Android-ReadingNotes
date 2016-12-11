package com.ssyijiu.retrofit.retrofit2.interceptors;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * <p>
 * 通过拦截器实现 cookie管理，会使用每次请求带上所有的cookie(访问A服务器，带上了B服务器的cookie)
 */

public class _ReceivedCookiesInterceptor implements Interceptor {

    public static HashSet<String> cookies = new HashSet<>();

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
        }
        return originalResponse;
    }
}
