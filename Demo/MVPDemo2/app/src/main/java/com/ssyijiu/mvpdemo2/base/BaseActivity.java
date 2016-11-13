package com.ssyijiu.mvpdemo2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity<T extends BasePresenter>
        extends AppCompatActivity implements IView {


    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mPresenter = createPresenter();
        mPresenter.attachView(this);

        initEventAndData();

        if (getIntent() != null) {
            parseIntDataFromIntent(getIntent());
        }

        MLog.i("Presenter = " + mPresenter);
        MLog.i("View = " + mPresenter.getView());

        checkPresenter().onStart();
    }

    protected abstract T createPresenter();


    @Override
    protected void onDestroy() {

        checkPresenter().detachView();

        MLog.i("View = " + mPresenter.getView());

        // Activity意外销毁这个方法不会被执行
        if(isFinishing()) {

            MLog.i("isFinishing");

            if(!mPresenter.isViewAttached()) {
                mPresenter = null;
            }
        }

        MLog.i("Presenter = " + mPresenter);

        super.onDestroy();
    }

    protected abstract int getLayoutResId();

    protected abstract void initEventAndData();

    protected abstract void parseIntDataFromIntent(Intent intent);

    public T checkPresenter() {
        if (mPresenter == null) {
            throw new IllegalStateException("The createPresenter must return non-null");
        }
        return mPresenter;
    }

}
