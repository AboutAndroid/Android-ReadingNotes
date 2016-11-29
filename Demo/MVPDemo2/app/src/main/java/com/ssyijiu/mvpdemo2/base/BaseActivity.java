package com.ssyijiu.mvpdemo2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;

import java.util.HashSet;
import java.util.Set;

import icepick.Icepick;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    protected static Set<MvpPresenter> sPresenterManager = new HashSet<>(1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(getLayoutResId());

        loadPresenters();
        initViewAndData();

        if (getIntent() != null) {
            parseIntDataFromIntent(getIntent());
        }

        initPresenters();
        MLog.i(sPresenterManager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {

        detachViews();
        // Activity意外销毁这个方法不会被执行
        if (isFinishing()) {
            MLog.i("isFinishing");
            removePresenters();
        }
        MLog.i(sPresenterManager);
        super.onDestroy();
    }

    protected abstract int getLayoutResId();

    protected abstract MvpPresenter[] getPresenters();

    protected abstract void initViewAndData();

    protected abstract void parseIntDataFromIntent(Intent intent);


    protected void loadPresenters() {

        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                presenter.attachView(this);
                sPresenterManager.add(presenter);
            }
        }
    }

    protected void initPresenters() {
        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                presenter.init();
            }
        }
    }

    private void detachViews() {

        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                if (presenter != null) {
                    presenter.detachView();
                }
            }
        }

    }

    private void removePresenters() {
        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                sPresenterManager.remove(presenter);
            }
        }
    }
}
