package com.ssyijiu.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.api.MultiApi;
import com.ssyijiu.retrofit.bean.MovieResp;
import com.ssyijiu.retrofit.bean.MultiResp;
import com.ssyijiu.retrofit.model.ModelListener;
import com.ssyijiu.retrofit.model.MovieImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new MovieImpl().getMsg(new MovieListener());
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMessage();
            }
        });


    }


    /**
     * POST 请求测试
     */
    private void getMessage() {

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

            }

            @Override
            public void onFailure(Call<MultiResp> call, Throwable t) {
                MLog.e(t.getMessage());
            }
        });
    }

    private Call<MultiResp> getCall(MultiApi api) {

//        Map<String,String> params = new HashMap<>();
//        params.put("param","b1.unionloginurl.limit");
//        return api.getMessage(params);

        return api.getMessage("b1.unionloginurl.limit");
    }


    /**
     * 逗逼架构
     *
     * View 触发事件，Activity 请求数据，请求成功后更新 View。
     *
     * initData() {
     *     1. 请求数据
     *     2. 将数据展示到 view 上
     * }
     */


    /**
     * MVC
     *
     * View 持有 Controller，传递事件给 Controller，Controller 由此去触发 Model 层事件，
     * Model 更新完数据（如从网络或者数据库获得数据后）触发 View 的更新事件。
     *
     * M - model      数据中心、业务处理
     * V - xml        数据展示、人机交互
     * C - Activity   M 和 V 之间的协调者，将 M 提供的数据展示到 V 上
     *                xml 和 Activity 并没有完全分离
     */
    class MovieListener implements ModelListener {
        @Override
        public void onSuccess(Object resp) {
            List<MovieResp.SubjectsBean> subjects = ((MovieResp)resp).getSubjects();
            List<String> movieNames = new ArrayList<String>();
            for (MovieResp.SubjectsBean sub : subjects) {
                movieNames.add(sub.getTitle());
            }
            tvInfo.setText(String.valueOf(movieNames));
        }

        @Override
        public void onFailure(Throwable t) {
            tvInfo.setText(t.getMessage());
        }
    }
}


