package com.ssyijiu.mvpdemo2.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;
import com.yatatsu.autobundle.AutoBundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    protected static List<MvpPresenter> sPresenterManager = new ArrayList<>();

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mContext = this;

        bindBundle(savedInstanceState);

        loadPresenters();
        initViewAndData();

        if (getIntent() != null) {
            parseIntDataFromIntent(getIntent());
        }

        initPresenters();
        MLog.i("onCreate:" + sPresenterManager.hashCode() + sPresenterManager);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AutoBundle.pack(this,outState);
    }

    @Override
    protected void onDestroy() {

        detachViews();
        // Activity意外销毁这个方法不会被执行
        if (isFinishing()) {
            MLog.i("isFinishing");
            removePresenters();
        }
        MLog.i("onDestroy:" + sPresenterManager.hashCode() + sPresenterManager);
        super.onDestroy();
    }

    protected abstract int getLayoutResId();

    /**
     * 获取当前页面所有的 presenter
     * @return
     */
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

//        if(sPresenterManager.size() > 0) {
//            for (MvpPresenter presenter : sPresenterManager) {
//                presenter.init();
//            }
//        }

        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                presenter.init();
            }
        }
    }

    private void detachViews() {


//        if(sPresenterManager.size() > 0) {
//            MLog.i("detachViews:" + sPresenterManager.hashCode() + sPresenterManager);
//            for (MvpPresenter presenter : sPresenterManager) {
//                presenter.detachView();
//            }
//        }

        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {

            MLog.i("detachViews:"+ Arrays.asList(presenters));
            for (MvpPresenter presenter : presenters) {
                    presenter.detachView();
            }
        }

    }

    private void removePresenters() {

//        if(sPresenterManager.size() > 0) {
//            for (MvpPresenter presenter : sPresenterManager) {
//                sPresenterManager.remove(presenter);
//            }
//        }

        MvpPresenter[] presenters = getPresenters();
        if (presenters != null) {
            for (MvpPresenter presenter : presenters) {
                sPresenterManager.remove(presenter);
            }
        }
    }

    private void bindBundle(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            AutoBundle.bind(this,savedInstanceState);
        } else if(getIntent() != null){
            AutoBundle.bind(this, getIntent());
        }
    }
}
