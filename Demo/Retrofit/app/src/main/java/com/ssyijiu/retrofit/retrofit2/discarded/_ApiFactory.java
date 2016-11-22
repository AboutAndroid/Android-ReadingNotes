package com.ssyijiu.retrofit.retrofit2.discarded;

import com.ssyijiu.retrofit.retrofit2.api.MovieApi;
import com.ssyijiu.retrofit.retrofit2.api.MultiApi;
import com.ssyijiu.retrofit.retrofit2.api.PostApi;

/**
 * Created by ssyijiu on 2016/11/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum _ApiFactory {
    INSTANCE;
    public final MovieApi API_MOVIE;
    public final PostApi API_POST;
    public final MultiApi API_MULTI;
    _ApiFactory() {

        /**
         * 会把所有的 Api.class 加载进内存
         */

        API_MOVIE = _RetrofitFactory.MOVIE_INSTANCE.getRetrofit().create(MovieApi.class);
        API_POST = _RetrofitFactory.MULTI_INSTANCE.getRetrofit().create(PostApi.class);
        API_MULTI = _RetrofitFactory.MULTI_INSTANCE.getRetrofit().create(MultiApi.class);

    }
}
