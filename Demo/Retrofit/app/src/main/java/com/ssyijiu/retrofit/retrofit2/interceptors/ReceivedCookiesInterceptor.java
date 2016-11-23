package com.ssyijiu.retrofit.retrofit2.interceptors;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    public static HashSet<String> cookies   = new HashSet<>();

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
