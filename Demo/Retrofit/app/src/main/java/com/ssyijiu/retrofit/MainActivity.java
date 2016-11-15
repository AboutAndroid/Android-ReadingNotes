package com.ssyijiu.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ssyijiu.retrofit.mvp.presenter.MsgPresenter;
import com.ssyijiu.retrofit.mvp.view.MsgView;


/**
 * http://www.codexiu.cn/android/blog/18502/
 * http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237358&idx=1&sn=f71478d5c450f588ed1678752ec36f6b&chksm=886398c1bf1411d7d8ae4369114e6737291d278c8a9225364d2a7f8b29d6c40e0db291f74217&mpshare=1&scene=1&srcid=1012ccqYkXxK2jVQpdHdT9bO#wechat_redirect
 */
public class MainActivity extends AppCompatActivity implements MsgView {

    private TextView tvInfo;
    MsgPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        presenter = MsgPresenter.getInstance();
        presenter.attachView(this);

        presenter.onStart();

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            presenter.getGetMsg();
            }
        });
        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            presenter.getPostMsg();
            }
        });


    }

    @Override
    public void showLoading(String loadingMsg) {
        tvInfo.setText(loadingMsg);
    }

    @Override
    public void showSuccess(Object resp) {
        tvInfo.setText((String)resp);
    }

    @Override
    public void showFailed(Throwable t) {
        tvInfo.setText(t.getMessage());
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        if(isFinishing()) {
            presenter = null;
        }
        super.onDestroy();
    }
}


