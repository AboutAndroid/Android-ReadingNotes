package com.ssyijiu.retrofit.mvp.model;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.api.MultiApi;
import com.ssyijiu.retrofit.bean.MultiResp;

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

public class MsgManager {
    private static MsgManager instance = null;
    public static MsgManager getInstance() {
        if(instance == null) {
            synchronized (MsgManager.class) {
                if(instance == null) {
                    instance = new MsgManager();
                }
            }
        }

        return instance;
    }

    public interface MsgListener {
        void getMsgSuccess(MultiResp resp);
        void getMsgFailed(Throwable t);
    }

    public void getMsg(final MsgListener listener) {
        //1. 创建一个 retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MultiApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2. 创建一个 api 接口对象
        MultiApi multiApi = retrofit.create(MultiApi.class);

        //3. 创建一个回调 resp 对象
        Call<MultiResp> call = getCall(multiApi);

        //4. 开始请求网络
        call.enqueue(new Callback<MultiResp>() {
            @Override
            public void onResponse(Call<MultiResp> call, Response<MultiResp> response) {

                MLog.i(response.headers()); // 获取所有的请求头
                MLog.i(response.body().responseParams);

                listener.getMsgSuccess(response.body());

            }

            @Override
            public void onFailure(Call<MultiResp> call, Throwable t) {
                MLog.e(t.getMessage());

                listener.getMsgFailed(t);
            }
        });
    }

    private Call<MultiResp> getCall(MultiApi api) {

//        Map<String,String> params = new HashMap<>();
//        params.put("param","b1.unionloginurl.limit");
//        return api.getMessage(params);

        return api.getMessage("b1.unionloginurl.limit");
    }

}
