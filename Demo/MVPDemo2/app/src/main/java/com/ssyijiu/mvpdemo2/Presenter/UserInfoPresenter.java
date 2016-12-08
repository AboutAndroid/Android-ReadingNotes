package com.ssyijiu.mvpdemo2.presenter;

import com.ssyijiu.mvpdemo2.ui.UserInfoContract;

/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class UserInfoPresenter extends UserInfoContract.Presenter {


    @Override
    public void init() {
        if(isViewAttached()) {
            getView().showLoading();
        }
    }

    @Override
    public void show() {
        if(isViewAttached()) {
            getView().showUserInfo();
        }
    }
}
