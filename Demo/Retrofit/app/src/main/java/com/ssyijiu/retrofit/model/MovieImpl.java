package com.ssyijiu.retrofit.model;

import com.ssyijiu.retrofit.api.MovieApi;
import com.ssyijiu.retrofit.bean.MovieResp;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ssyijiu on 2016/10/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MovieImpl implements MovieModel {

    @Override
    public void getMsg(final ModelListener listener) {
        //1. 创建一个 retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2. 创建一个 api 接口对象
        MovieApi movieApi = retrofit.create(MovieApi.class);

        //3. 创建一个回调 resp 对象
        Call<MovieResp> call = getCall(movieApi);

        //4. 开始请求网络
        call.enqueue(new Callback<MovieResp>() {
            @Override
            public void onResponse(Call<MovieResp> call, Response<MovieResp> response) {
                listener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<MovieResp> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    private Call<MovieResp> getCall(MovieApi api) {

//        Map<String,Integer> params = new HashMap<>();
//        params.put("start",2);
//        params.put("count",4);
//        return api.getTop250(params);

//        return  api.getTop250(2,5);
//        return  api.getTop250("top250");
//        return  api.getTop250_3();
        return  api.getTop250();
    }
}
