package com.ssyijiu.retrofit.mvp;

import com.ssyijiu.mvp.BasePresenter;
import com.ssyijiu.mvp.i.MvpListener;
import com.ssyijiu.mvp.i.MvpModel;
import com.ssyijiu.mvp.i.MvpView;

/**
 * Created by ssyijiu on 2016/12/11.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface RetrofitContract {

    interface View extends MvpView {

        void showLoading(String loadingMsg);

        void showSuccess(Object resp);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getFinancingList();

        public abstract void getGoldPrice();

        public abstract void getGetMsg();

        public abstract void getPostMsg();

        public abstract void loading(String loadingMsg);
    }


    interface Model extends MvpModel {

        void getPostMsg(MvpListener<String> listener);

        void getGetMsg(MvpListener<String> listener);

        void getGoldPrice(MvpListener<String> listener);

        void getFinancingList(MvpListener<String> listener);
    }
}
