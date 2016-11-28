package com.ssyijiu.mvpdemo2.presenter.contract;

import com.ssyijiu.mvpdemo2.base.BaseActivity;
import com.ssyijiu.mvpdemo2.base.BasePresenter;
import com.ssyijiu.mvpdemo2.base.MvpView;


/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface UserInfoContract {
    interface View extends MvpView {

        void showLoading();

        void showUserInfo();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void show();
    }
}
