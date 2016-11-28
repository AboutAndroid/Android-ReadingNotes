package com.ssyijiu.mvpdemo2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;
import com.ssyijiu.mvpdemo2.presenter.LoginPresenter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import icepick.Icepick;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity<T extends BasePresenter>
        extends AppCompatActivity implements IView {


    private T mPresenter;

    private Set<IPresenter> mAllPresenters = new HashSet<>(1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(getLayoutResId());

        mPresenter = createPresenter();
        mPresenter.attachView(this);

        addPresenters();

        initEventAndData();

        if (getIntent() != null) {
            parseIntDataFromIntent(getIntent());
        }

        getPresenter().initView();
    }


    protected void addPresenters() {

        mAllPresenters.add(mPresenter);

        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            for (IPresenter presenter : presenters) {
                presenter.attachView(this);
                mAllPresenters.add(presenter);
            }
        }

        MLog.i(mAllPresenters);
    }

    protected abstract T createPresenter();

    protected abstract IPresenter[] getPresenters();


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {

        // 解绑视图
        detachViews();

        // Activity意外销毁这个方法不会被执行
        if(isFinishing()) {

            MLog.i("isFinishing");
            clearPresenters();
        }
        MLog.i(mAllPresenters);
        super.onDestroy();
    }

    protected abstract int getLayoutResId();

    protected abstract void initEventAndData();

    protected abstract void parseIntDataFromIntent(Intent intent);

    public T getPresenter() {
        if (mPresenter == null) {
            throw new IllegalStateException("The createPresenter must return non-null");
        }
        return mPresenter;
    }

    private void detachViews() {
        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            for (IPresenter presenter : presenters) {
                if(presenter != null){
                    presenter.detachView();
                }
            }
        }

    }

    private void clearPresenters() {
        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            for (IPresenter presenter : presenters) {
                mAllPresenters.remove(presenter);
            }
        }

        mPresenter = null;
    }
}
