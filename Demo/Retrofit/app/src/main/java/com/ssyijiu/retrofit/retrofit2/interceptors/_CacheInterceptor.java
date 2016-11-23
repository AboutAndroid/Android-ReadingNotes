package com.ssyijiu.retrofit.retrofit2.interceptors;

import com.ssyijiu.retrofit.App;
import com.ssyijiu.retrofit.Utils.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ssyijiu on 2016/11/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * http://blog.csdn.net/qq_17766199/article/details/53186874
 * 注意：这个缓存不支持 POST 见 http://www.w3school.com.cn/tags/html_ref_httpmethods.asp
 */

public class _CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        // 无网络强制创建一个强制使用缓存的 Request
        // 去除 POST(否则会因为读不到缓存而空指针)
        if (!NetUtil.isAvailable(App.getContext()) && originalRequest.method().equals("GET")) {
            originalRequest = originalRequest.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        // 构建响应
        Response originalResponse = chain.proceed(originalRequest);

        if(NetUtil.isAvailable(App.getContext())) {

            // String cacheControl = originalRequest.cacheControl().toString();
            return originalResponse.newBuilder()
                    // .header("Cache-Control", cacheControl)
                    .header("Cache-Control", "max-age=30")
                    .removeHeader("Pragma")
                    .build();
        } else {

            int maxAge= 60 * 60;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        }

    }

}
