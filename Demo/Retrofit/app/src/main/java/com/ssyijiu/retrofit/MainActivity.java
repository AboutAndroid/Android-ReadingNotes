package com.ssyijiu.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ssyijiu.library.MLog;
import com.ssyijiu.retrofit.api.MultiApi;
import com.ssyijiu.retrofit.bean.MovieResp;
import com.ssyijiu.retrofit.bean.MultiResp;
import com.ssyijiu.retrofit.model_mvc.ModelListener;
import com.ssyijiu.retrofit.model_mvc.MovieImpl;
import com.ssyijiu.retrofit.mvp.model.MsgManager;
import com.ssyijiu.retrofit.mvp.presenter.MsgContract;
import com.ssyijiu.retrofit.mvp.presenter.MsgPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.M;


public class MainActivity extends AppCompatActivity implements MsgContract.MsgView{

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
                MsgPresenter msgPresenter = new MsgPresenter();
                msgPresenter.attach(MainActivity.this);
                msgPresenter.getMsg();
            }
        });


    }

    @Override
    public void showLoading(String loadingMsg) {
        tvInfo.setText(loadingMsg);
    }

    @Override
    public void showSuccess(String msg) {
        tvInfo.setText(msg);
    }

    @Override
    public void showFailed(String failedMsg) {
        tvInfo.setText(failedMsg);
    }

    @Override
    public void initView() {

    }


    /**
     * 逗逼架构
     *
     * View 触发事件，Activity 请求数据，请求成功后更新 View。
     *
     * onCreate {
     *     getMessage()
     * }
     *
     * getMessage {
     *
     *     1. 请求数据
     *     2. 将数据展示到 view 上
     * }
     *
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


