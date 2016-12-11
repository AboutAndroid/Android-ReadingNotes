package com.ssyijiu.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.mvp.i.MvpPresenter;
import com.ssyijiu.mvp.i.MvpView;


/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class MvpActivity extends AppCompatActivity implements MvpView {

    protected PresenterManager mPresenterManager = PresenterManager.INSTANCE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPresenters();
        attachViews();
    }

    /**
     * get All the presenters in Activity/Fragment
     * @return
     */
    protected abstract MvpPresenter[] getPresenters();

    @Override
    protected void onDestroy() {
        detachViews();
        removePresenters();
        super.onDestroy();
    }

    protected void loadPresenters() {
        mPresenterManager.addPresenter(getPresenters());
    }

    protected void attachViews() {
        mPresenterManager.attachViews(this,getPresenters());
    }

    private void detachViews() {
        mPresenterManager.detachViews(getPresenters());
    }

    private void removePresenters() {
        mPresenterManager.removePresenter(getPresenters());
    }
}
