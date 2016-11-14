package com.ssyijiu.retrofit.mvp.presenter;

import com.ssyijiu.retrofit.mvp.view.IView;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void onStart();

    void detachView();
}
