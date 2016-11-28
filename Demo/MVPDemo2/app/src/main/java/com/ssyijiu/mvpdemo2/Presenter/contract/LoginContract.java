package com.ssyijiu.mvpdemo2.presenter.contract;

import com.ssyijiu.mvpdemo2.base.BasePresenter;
import com.ssyijiu.mvpdemo2.base.MvpModel;
import com.ssyijiu.mvpdemo2.base.MvpView;

/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginContract {

    interface View extends MvpView {

        void showLoading();

        void toUserInfo();

        void showFailed();

        void showHello();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void login(final String username, final String password);
    }

    interface Mode extends MvpModel {

        interface LoginListener {

            void onSuccess();

            void onFailed();
        }

        void login(String username, String password, LoginListener listener);
    }
}
