package com.ssyijiu.retrofit.mvp;

import com.ssyijiu.mvp.DefaultMvpListener;
import com.ssyijiu.mvp.ModelManager;
import com.ssyijiu.mvp.i.MvpView;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RetrofitPresenter extends RetrofitContract.Presenter {


    RetrofitModel mModel = ModelManager.getModel(RetrofitModel.class);

    @Override
    public void getFinancingList() {
        loading("FinancingList:loading...");
        mModel.getFinancingList(new MsgListener(getView()));
    }

    @Override
    public void getGoldPrice() {
        loading("GoldPrice:loading...");
        mModel.getGoldPrice(new MsgListener(getView()));
    }

    @Override
    public void getPostMsg() {
        loading("POST:loading...");
        mModel.getPostMsg(new MsgListener(getView()));
    }

    @Override
    public void loading(String loadingMsg) {
        if (isViewAttached()) {
            getView().showLoading(loadingMsg);
        }
    }

    @Override
    public void getGetMsg() {
        loading("GET:loading...");
        mModel.getGetMsg(new MsgListener(getView()));
    }


    private class MsgListener extends DefaultMvpListener<String> {

        private MsgListener(MvpView mvpView) {
            super(mvpView);
        }

        @Override
        public void onSuccess(String resp) {
            if (isViewAttached()) {
                getView().showSuccess(resp);
            }
        }
    }

    @Override
    public void init() {
    }
}
