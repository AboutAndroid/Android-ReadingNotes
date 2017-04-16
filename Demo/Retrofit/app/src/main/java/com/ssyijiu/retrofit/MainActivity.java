package com.ssyijiu.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import com.ssyijiu.library.MLog;
import com.ssyijiu.mvp.MvpActivity;
import com.ssyijiu.mvp.i.MvpPresenter;
import com.ssyijiu.retrofit.bean.vo.User;
import com.ssyijiu.retrofit.mvp.RetrofitContract;
import com.ssyijiu.retrofit.mvp.RetrofitPresenter;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * http://www.codexiu.cn/android/blog/18502/
 * http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237358&idx=1&sn=f71478d5c450f588ed1678752ec36f6b&chksm=886398c1bf1411d7d8ae4369114e6737291d278c8a9225364d2a7f8b29d6c40e0db291f74217&mpshare=1&scene=1&srcid=1012ccqYkXxK2jVQpdHdT9bO#wechat_redirect
 */
public class MainActivity extends MvpActivity implements RetrofitContract.View {

    private TextView tvInfo;
    RetrofitPresenter presenter = new RetrofitPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        findViewById(R.id.btn_get).setOnClickListener((view) -> presenter.getGetMsg());

        findViewById(R.id.btn_post).setOnClickListener((view) -> presenter.getPostMsg());

        findViewById(R.id.btn_gold_price).setOnClickListener((view) -> presenter.getGoldPrice());

        findViewById(R.id.btn_financing_list).setOnClickListener((view) -> presenter.getFinancingList());

    }

    @Override
    protected MvpPresenter[] getPresenters() {
        return new MvpPresenter[]{presenter};
    }

    @Override
    public void showLoading(String loadingMsg) {
        tvInfo.setText(loadingMsg);
    }

    @Override
    public void showSuccess(Object resp) {
        tvInfo.setText((String) resp);
    }

    @Override
    public void showError(String errorMsg) {
        tvInfo.setText(errorMsg);
    }

    @Override
    public void showException(Throwable tr) {
        tvInfo.setText(tr.getMessage());
    }

}


