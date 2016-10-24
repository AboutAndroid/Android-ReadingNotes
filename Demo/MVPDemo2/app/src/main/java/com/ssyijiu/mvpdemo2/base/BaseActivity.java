package com.ssyijiu.mvpdemo2.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements IView {

    private P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = onLoadPresenter();

        MLog.setLogLev(MLog.LogLev.NO_LOG);

        MLog.i("onCreate:" + mPresenter);

        getPresenter().attachView(this);

        initEventAndData();

        if (getIntent() != null) {
            parseIntDataFromIntent(getIntent());
        }

        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }


    public abstract P onLoadPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initEventAndData();

    protected abstract void parseIntDataFromIntent(Intent intent);


    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (getPresenter().isViewAttach()) {
            mPresenter.detachView();
        }

        if(isFinishing()) {
            mPresenter = null;
        }


        MLog.i("onDestroy:" + mPresenter);
    }
}
