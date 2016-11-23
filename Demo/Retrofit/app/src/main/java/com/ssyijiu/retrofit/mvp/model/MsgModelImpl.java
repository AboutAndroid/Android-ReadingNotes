package com.ssyijiu.retrofit.mvp.model;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.bean.FinancingListResp;
import com.ssyijiu.retrofit.bean.GoldPriceResp;
import com.ssyijiu.retrofit.bean.MovieResp;
import com.ssyijiu.retrofit.bean.PostResp;
import com.ssyijiu.retrofit.retrofit2.ApiFactory;
import com.ssyijiu.retrofit.retrofit2.api.MovieApi;
import com.ssyijiu.retrofit.retrofit2.api.ParamsMap;
import com.ssyijiu.retrofit.retrofit2.api.PostApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    public void getFinancingList(final MsgListener listener) {


        Map<String,String> map = ParamsMap.INSTANCE.getTokenMap();
        map.put("prdType","1");

        ApiFactory.INSTANCE.API_MULTI.getFinancingList(map).enqueue(new Callback<FinancingListResp>() {
            @Override
            public void onResponse(Call<FinancingListResp> call, Response<FinancingListResp> response) {
                listener.onSuccess(response.body().getResponseParams().get(0).getTBaseLCProcuct().getAdMemo());
            }

            @Override
            public void onFailure(Call<FinancingListResp> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void getGoldPrice(final MsgListener listener) {
        ApiFactory.INSTANCE.API_MULTI.getGoldPrice().enqueue(new Callback<GoldPriceResp>() {
            @Override
            public void onResponse(Call<GoldPriceResp> call, Response<GoldPriceResp> response) {
                listener.onSuccess(response.body().getResponseParams().getGoldRate());
            }

            @Override
            public void onFailure(Call<GoldPriceResp> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    @Override
    public void getPostMsg(final MsgModel.MsgListener listener) {

        ApiFactory.INSTANCE.API_POST.getMessage("b1.unionloginurl.limit").enqueue(new Callback<PostResp>() {
            @Override
            public void onResponse(Call<PostResp> call, Response<PostResp> response) {
                // 获取所有的请求头
//                MLog.i(response.headers());

                // 获取单个请求头
                okhttp3.Response okResponse = response.raw();
//                MLog.i(okResponse.header("Date"));

                listener.onSuccess(response.body().responseParams);
            }

            @Override
            public void onFailure(Call<PostResp> call, Throwable t) {
                MLog.e(t.getMessage());
                listener.onFailure(t);
            }
        });

        // 取消调用
        // call.cancel();
    }

    private Call<PostResp> getPostCall(PostApi api) {

//        Map<String,String> params = new HashMap<>();
//        params.put("param","b1.unionloginurl.limit");
//        return api.getMessage(params);

        return api.getMessage("b1.unionloginurl.limit");
    }


    @Override
    public void getGetMsg(final MsgListener listener) {


        ApiFactory.INSTANCE.API_MOVIE.getTop250().enqueue(new Callback<MovieResp>() {
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
