package com.ssyijiu.retrofit.mvp;

import com.ssyijiu.library.MLog;
import com.ssyijiu.mvp.BaseModel;
import com.ssyijiu.mvp.i.MvpListener;
import com.ssyijiu.retrofit.bean.MovieBean;
import com.ssyijiu.retrofit.bean.resp.FinancingListResp;
import com.ssyijiu.retrofit.bean.resp.GoldPriceResp;
import com.ssyijiu.retrofit.bean.resp.StringResp;
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

public class RetrofitModel extends BaseModel implements RetrofitContract.Model {

    private RetrofitModel() {
    }


    @Override
    public void getFinancingList(final MvpListener<String> listener) {


        Map<String, String> map = ParamsMap.INSTANCE.getTokenMap();
//        Map<String,String> map = new HashMap<>();
        map.put("prdType", "1");

        ApiFactory.INSTANCE.API_MULTI.getFinancingList(map).enqueue(new Callback<FinancingListResp>() {
            @Override
            public void onResponse(Call<FinancingListResp> call, Response<FinancingListResp> response) {
                listener.onSuccess(response.body().transform().toString());
            }

            @Override
            public void onFailure(Call<FinancingListResp> call, Throwable t) {
                listener.onException(t);
            }
        });
    }

    @Override
    public void getGoldPrice(final MvpListener<String> listener) {
        ApiFactory.INSTANCE.API_MULTI.getGoldPrice().enqueue(new Callback<GoldPriceResp>() {
            @Override
            public void onResponse(Call<GoldPriceResp> call, Response<GoldPriceResp> response) {
                listener.onSuccess(response.body().responseParams.goldRate);
            }

            @Override
            public void onFailure(Call<GoldPriceResp> call, Throwable t) {
                listener.onException(t);
            }
        });
    }

    @Override
    public void getPostMsg(final MvpListener<String> listener) {

        ApiFactory.INSTANCE.API_POST.getMessage("b1.unionloginurl.limit").enqueue(new Callback<StringResp>() {
            @Override
            public void onResponse(Call<StringResp> call, Response<StringResp> response) {
                // 获取所有的请求头
//                MLog.i(response.headers());

                // 获取单个请求头
                okhttp3.Response okResponse = response.raw();
//                MLog.i(okResponse.header("Date"));

                listener.onSuccess(response.body().responseParams);
            }

            @Override
            public void onFailure(Call<StringResp> call, Throwable t) {
                MLog.e(t.getMessage());
                listener.onException(t);
            }
        });

        // 取消调用
        // call.cancel();
    }

    private Call<StringResp> getPostCall(PostApi api) {

//        Map<String,String> params = new HashMap<>();
//        params.put("param","b1.unionloginurl.limit");
//        return api.getMessage(params);

        return api.getMessage("b1.unionloginurl.limit");
    }


    @Override
    public void getGetMsg(final MvpListener<String> listener) {


        ApiFactory.INSTANCE.API_MOVIE.getTop250().enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                List<MovieBean.SubjectsBean> subjects = response.body().getSubjects();
                List<String> movieNames = new ArrayList<String>();
                for (MovieBean.SubjectsBean sub : subjects) {
                    movieNames.add(sub.getTitle());
                }

                listener.onSuccess(String.valueOf(movieNames));
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {
                listener.onException(t);
            }
        });
    }


    private Call<MovieBean> getCall(MovieApi api) {

//        Map<String,Integer> params = new HashMap<>();
//        params.put("start",2);
//        params.put("count",4);
//        return api.getTop250(params);

//        return  api.getTop250(2,5);
//        return  api.getTop250("top250");
//        return  api.getTop250_3();
        return api.getTop250();
    }
}
