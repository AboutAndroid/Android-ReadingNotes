package com.ssyijiu.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.api.GankApi;
import com.ssyijiu.retrofit.api.MovieApi;
import com.ssyijiu.retrofit.resp.GankResp;
import com.ssyijiu.retrofit.resp.MovieResp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMovie();
    }

    private void getMovie() {

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

                MLog.i(movieNames);
            }

            @Override
            public void onFailure(Call<MovieResp> call, Throwable t) {
                MLog.e(t.getMessage());
            }
        });
    }

    private Call<MovieResp> getCall(MovieApi api) {
        return  api.getTop250(2,5);
//        return  api.getTop250();
//        return  api.getTop250_3();
//        return  api.getTop250("top250");
    }
}
