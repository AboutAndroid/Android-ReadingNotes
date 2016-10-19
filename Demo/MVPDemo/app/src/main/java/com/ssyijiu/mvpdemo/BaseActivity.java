package com.ssyijiu.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ssyijiu.mvpdemo.base.IPresenter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lixiaoming on 2016/8/8.
 */
public abstract class BaseActivity extends FragmentActivity {

    private Set<IPresenter> mAllPresenters = new HashSet<IPresenter>(1);

    /**
     * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     *需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract IPresenter[] getPresenters();

    //初始化presenters，
    protected abstract void onInitPresenters();

    /**
     * 从intent中解析数据，具体子类来实现
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent){
    }

    private void addPresenters(){

        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            for(int i = 0; i < presenters.length; i++){
                mAllPresenters.add(presenters[i]);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if(getIntent() != null){
            parseArgumentsFromIntent(getIntent());
        }
        addPresenters();

        onInitPresenters();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //依次调用IPresenter的onResume方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onResume();
            }
        }
    }
}
