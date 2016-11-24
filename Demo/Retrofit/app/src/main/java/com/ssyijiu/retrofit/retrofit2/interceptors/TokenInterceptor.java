package com.ssyijiu.retrofit.retrofit2.interceptors;

import android.text.TextUtils;

import com.ssyijiu.retrofit.retrofit2.api.CommonParams;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * 给网络请求添加公共参数 如：token
 */

public class TokenInterceptor implements Interceptor {

    // 使用 volatile 来保证 token 的可见性
    private volatile String mToken;

    public void setToken(String token) {
        mToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request originalRequest = chain.request();
        Headers originHeaders = originalRequest.headers();
        Headers.Builder newHeaders = new Headers.Builder();


        String authType = "";

        for (int i = 0, size = originHeaders.size(); i < size; i++) {
            if (!TextUtils.equals(originHeaders.name(i), "Auth-Type")) {
                newHeaders.add(originHeaders.name(i), originHeaders.value(i));
            } else {
                authType = originHeaders.value(i);
            }
        }
        Request.Builder newRequest = originalRequest.newBuilder()
                .headers(newHeaders.build());

        if(authType.equals("TOKEN")) {
            tokenAuth(newRequest, originalRequest.url(), System.currentTimeMillis());
        }

        return chain.proceed(newRequest.build());
    }

    private void tokenAuth(Request.Builder newRequest, HttpUrl url, long timestamp) {
        if(TextUtils.isEmpty(mToken) ) {
            //throw new YLApiError(/**...*/);
        }

        HttpUrl.Builder newUrl = url.newBuilder()
                .addQueryParameter(CommonParams.CHANNEL_ID, CommonParams.CHANNEL_ID_VALUE);

        newRequest.url(newUrl.build());
    }
}
