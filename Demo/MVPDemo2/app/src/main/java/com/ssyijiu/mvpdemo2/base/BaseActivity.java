package com.ssyijiu.mvpdemo2.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;
import com.ssyijiu.mvpdemo2.utils.ToastUtil;
import com.yatatsu.autobundle.AutoBundle;


/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    protected PresenterManager mPresenterManager = PresenterManager.INSTANCE;

    protected Context mContext;

    private MvpPresenter[] mMvpPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mContext = this;

        // 保存和恢复数据
        bindBundle(savedInstanceState);

        // 加载 Presenters 并绑定 View
        loadPresenters();
        attachViews();

        // 初始化 View
        initView();

        // 初始化传递数据
        if (getIntent() != null) {
            parseIntent(getIntent());
        }

        // 初始化数据
        initData();


    }






    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AutoBundle.pack(this,outState);
    }

    @Override
    protected void onDestroy() {
        detachViews();
        removePresenters();
        super.onDestroy();
    }

    protected abstract int getLayoutResId();

    /**
     * 获取当前页面所有的 presenter
     * @return
     */
    protected abstract MvpPresenter[] getPresenters();


    protected abstract void initView();

    protected abstract void parseIntent(Intent intent);

    protected void initData() {
    }


    protected void loadPresenters() {
        MLog.i("before load:" + mPresenterManager.getPresenters());
        mMvpPresenters = getPresenters();
        mPresenterManager.addPresenter(mMvpPresenters);
        MLog.i("after load:" + mPresenterManager.getPresenters());
    }

    protected void attachViews() {
        mPresenterManager.attachViews(this,mMvpPresenters);
    }

    private void detachViews() {
        mPresenterManager.detachViews(mMvpPresenters);
    }

    private void removePresenters() {
        MLog.i("before remove:" + mPresenterManager.getPresenters());
        mPresenterManager.removePresenter(mMvpPresenters);
        MLog.i("after remove:" + mPresenterManager.getPresenters());
    }

    private void bindBundle(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            AutoBundle.bind(this,savedInstanceState);
        } else if(getIntent() != null){
            AutoBundle.bind(this, getIntent());
        }
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.show(errorMsg);
    }

    @Override
    public void showException(Throwable tr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(tr.getMessage());
        builder.create().show();
    }
}
