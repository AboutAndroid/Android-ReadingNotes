package com.ssyijiu.mvpdemo.base;

/**
 * Created by lixiaoming on 2016/8/8.
 */

//IPresenter提供了一些基础方法，其实这些方法是对应Activity或Fragment的生命周期方法

public interface IPresenter<V extends IView> {
    void onCrate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void attach(V view);
}
