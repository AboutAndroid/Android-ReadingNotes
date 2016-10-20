package com.ssyijiu.mvpdemo2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.mvpdemo2.Presenter.BasePresenter;
import com.ssyijiu.mvpdemo2.Presenter.IPresenter;
import com.ssyijiu.mvpdemo2.view.IView;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {


    protected P mPresenter;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initEventAndData();

        if(getPresenter() != null) {
            getPresenter().start();
        }
    }

    protected abstract int getLayoutResId();

    protected abstract void initEventAndData();

    public abstract P onLoadPresenter();

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getPresenter().isViewAttach()) {
            getPresenter().detachView();
        }
        
    }
}
