package com.ssyijiu.retrofit.retrofit2;

import com.ssyijiu.retrofit.retrofit2.api.MovieApi;
import com.ssyijiu.retrofit.retrofit2.api.MultiApi;
import com.ssyijiu.retrofit.retrofit2.api.PostApi;

/**
 * Created by ssyijiu on 2016/11/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ApiFactory {

    private ApiFactory() {
    }

    public static final class INSTANCE {
        public static final MovieApi API_MOVIE = RetrofitFactory.INSTANCE.MOVIE_INSTANCE.create(MovieApi.class);
        public static final PostApi API_POST = RetrofitFactory.INSTANCE.MULTI_INSTANCE.create(PostApi.class);
        public static final MultiApi API_MULTI = RetrofitFactory.INSTANCE.MULTI_INSTANCE.create(MultiApi.class);
    }

}
