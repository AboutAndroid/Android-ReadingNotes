package com.ssyijiu.retrofit.retrofit2.interceptors;

import android.text.TextUtils;

import com.ssyijiu.retrofit.retrofit2.api.ApiConfig;
import com.ssyijiu.retrofit.retrofit2.api.Your;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * <p>
 * 给网络请求添加 Token
 */

public class TokenInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        // token 为 null 或者请求中已经带了 token
        if (Your.sToken == null || alreadyHasTokenOnParam(originalRequest)) {
            return chain.proceed(originalRequest);
        }

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

        if (authType.equals(ApiConfig.HEADER_TOKEN)) {
            addTokenOnParam(newRequest, originalRequest.url());
        }

        return chain.proceed(newRequest.build());
    }

    private boolean alreadyHasTokenOnParam(Request originalRequest) {

        FormBody body = getFormBodyFromRequest(originalRequest);
        if(body != null) {
            for (int i = 0; i < body.size(); i++) {
                if (TextUtils.equals(body.encodedName(i), ApiConfig.TOKEN)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从 Request 提取 FormBody
     * @param request
     * @return if is GET Request or happen exception return null
     */
    private FormBody getFormBodyFromRequest(Request request) {

        // GET 请求没有 body
        if(TextUtils.equals(request.method(),"POST")) {
            // POST 请求没有参数的话 ，originalRequest.body() 不能转成 FormBody
            try {
                return (FormBody) request.body();
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }


    private void addTokenOnParam(Request.Builder newRequest, HttpUrl url) {
        if (TextUtils.isEmpty(Your.sToken)) {
            //throw new YLApiError(/**...*/);
        }

        HttpUrl.Builder newUrl = url.newBuilder()
                .addQueryParameter(ApiConfig.TOKEN, Your.sToken);

        newRequest.url(newUrl.build());
    }
}
