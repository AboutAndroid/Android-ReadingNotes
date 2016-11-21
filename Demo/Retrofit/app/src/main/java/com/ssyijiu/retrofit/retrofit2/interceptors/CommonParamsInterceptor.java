package com.ssyijiu.retrofit.retrofit2.interceptors;

import com.ssyijiu.retrofit.retrofit2.api.CommonParams;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        HttpUrl.Builder originalHttpUrl = originalRequest.url()
                .newBuilder()
//                .scheme(originalRequest.url().scheme())
//                .host(originalRequest.url().host())
                .addQueryParameter(CommonParams.CHANNEL_ID, "23");

        Request newRequest = originalRequest.newBuilder()
//                .method(originalRequest.method(), originalRequest.body())
                .url(originalHttpUrl.build())
                .build();

        return chain.proceed(newRequest);
    }
}
