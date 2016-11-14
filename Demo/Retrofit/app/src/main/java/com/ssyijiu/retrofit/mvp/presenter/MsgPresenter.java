package com.ssyijiu.retrofit.mvp.presenter;

import com.ssyijiu.retrofit.mvp.model.MsgModel;
import com.ssyijiu.retrofit.mvp.model.MsgModelImpl;
import com.ssyijiu.retrofit.mvp.view.MsgView;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MsgPresenter extends BasePresenter<MsgView> {

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


    public void getPostMsg() {
        if(isViewAttached()) {
            getView().showLoading("POST:loading...");
        }

        MsgModelImpl.getInstance().getPostMsg(new MsgModel.MsgListener() {
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
        });
    }


    public void getGetMsg() {
        if(isViewAttached()) {
            getView().showLoading("GET:loading...");
        }

        MsgModelImpl.getInstance().getGetMsg(new MsgModel.MsgListener() {
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
        });
    }

    @Override
    public void onStart() {
        getView().showLoading("onStart--Mvp--Retrofit");
    }
}
