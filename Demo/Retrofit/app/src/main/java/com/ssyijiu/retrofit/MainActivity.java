package com.ssyijiu.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ssyijiu.retrofit.mvp.presenter.MsgPresenter;
import com.ssyijiu.retrofit.mvp.view.MsgView;


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


