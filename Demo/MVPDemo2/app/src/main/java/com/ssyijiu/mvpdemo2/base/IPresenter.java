package com.ssyijiu.mvpdemo2.base;

import android.os.Bundle;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface IPresenter<V extends IView> {


    void attachView(V view);

    /** Presenter 初始化网络数据，并根据数据展示相关页面 */
    void onStart();

    void detachView();

}
