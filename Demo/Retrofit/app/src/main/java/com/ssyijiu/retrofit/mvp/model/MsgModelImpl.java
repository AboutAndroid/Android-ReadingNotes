package com.ssyijiu.retrofit.mvp.model;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.api.MovieApi;
import com.ssyijiu.retrofit.api.MultiApi;
import com.ssyijiu.retrofit.bean.MovieResp;
import com.ssyijiu.retrofit.bean.MultiResp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MsgModelImpl implements MsgModel{

    private MsgModelImpl() {
    }

    private static MsgModelImpl instance = null;

    public static MsgModelImpl getInstance() {
        if (instance == null) {
            synchronized (MsgModelImpl.class) {
                if (instance == null) {
                    instance = new MsgModelImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public void getPostMsg(final MsgModel.MsgListener listener) {
        //1. 创建一个 retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MultiApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2. 创建一个 api 接口对象
        MultiApi multiApi = retrofit.create(MultiApi.class);

        //3. 创建一个回调 resp 对象
        Call<MultiResp> call = getPostCall(multiApi);

        //4. 开始请求网络
        call.enqueue(new Callback<MultiResp>() {
            @Override
            public void onResponse(Call<MultiResp> call, Response<MultiResp> response) {

                // 获取所有的请求头
                MLog.i(response.headers());

                // 获取单个请求头
                okhttp3.Response okResponse = response.raw();
                MLog.i(okResponse.header("Date"));
                listener.onSuccess(response.body().responseParams);

            }

            @Override
            public void onFailure(Call<MultiResp> call, Throwable t) {
                MLog.e(t.getMessage());
                listener.onFailure(t);
            }
        });
    }

    private Call<MultiResp> getPostCall(MultiApi api) {

//        Map<String,String> params = new HashMap<>();
//        params.put("param","b1.unionloginurl.limit");
//        return api.getMessage(params);

        return api.getMessage("b1.unionloginurl.limit");
    }

    @Override
    public void getGetMsg(final MsgListener listener) {
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

                List<MovieResp.SubjectsBean> subjects = response.body().getSubjects();
                List<String> movieNames = new ArrayList<String>();
                for (MovieResp.SubjectsBean sub : subjects) {
                    movieNames.add(sub.getTitle());
                }

                listener.onSuccess(String.valueOf(movieNames));

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
