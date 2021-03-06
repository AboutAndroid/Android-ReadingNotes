package com.ssyijiu.retrofit.retrofit2.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


// Interceptor 一般用来设置 请求头(UA)、设置缓存策略 、打印 Log 等
// 这里来设置 请求头(UA)
public class UserAgentInterceptor implements Interceptor {  // 实现接口

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private final String userAgentHeaderValue;

    public UserAgentInterceptor(String userAgentHeaderValue) {
        this.userAgentHeaderValue = userAgentHeaderValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                //移除先前默认的 UA
                .removeHeader(USER_AGENT_HEADER_NAME)
                //设置 UA
                .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                // 设置其他的请求头 - 不会覆盖之前的请求头
                .addHeader("ssyijiu", "android")
                // 设置其他的请求头 - 会覆盖之前的请求头
                .header("ssyijiu", "java")
                .build();


        return chain.proceed(requestWithUserAgent);
    }
}
