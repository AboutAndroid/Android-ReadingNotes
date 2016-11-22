package com.ssyijiu.retrofit.mvp.presenter;

import com.ssyijiu.retrofit.mvp.model.MsgModel;
import com.ssyijiu.retrofit.mvp.model.MsgModelImpl;
import com.ssyijiu.retrofit.mvp.view.MsgView;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MsgPresenter extends BasePresenter<MsgView>{

    private MsgPresenter(){
    }

    private static MsgPresenter instance = null;

    public static MsgPresenter getInstance() {
        if (instance == null) {
            synchronized (MsgModelImpl.class) {
                if (instance == null) {
                    instance = new MsgPresenter();
                }
            }
        }

        return instance;
    }

    public void getFinancingList() {
        loading("FinancingList:loading...");

        MsgModelImpl.getInstance().getFinancingList(new MsgListener());
    }

    public void getGoldPrice() {
        loading("GoldPrice:loading...");

        MsgModelImpl.getInstance().getGoldPrice(new MsgListener());
    }

    public void getPostMsg() {
        loading("POST:loading...");

        MsgModelImpl.getInstance().getPostMsg(new MsgListener());
    }


    public void getGetMsg() {
        loading("GET:loading...");

        MsgModelImpl.getInstance().getGetMsg(new MsgListener());
    }

    private void loading(String loadingMsg) {
        if (isViewAttached()) {
            getView().showLoading(loadingMsg);
        }
    }

    private class MsgListener implements MsgModel.MsgListener {

        @Override
        public void onSuccess(String resp) {
            if(isViewAttached()) {
                getView().showSuccess(resp);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            if(isViewAttached()) {
                getView().showFailed(t);
            }
        }
    }

    @Override
    public void onStart() {
        getView().showLoading("onStart");
    }
}
