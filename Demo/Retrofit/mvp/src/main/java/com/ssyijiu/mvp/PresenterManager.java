package com.ssyijiu.mvp;

import com.ssyijiu.mvp.i.MvpPresenter;
import com.ssyijiu.mvp.i.MvpView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ssyijiu on 2016/12/5.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum PresenterManager {
    INSTANCE;
    private List<MvpPresenter> mList = new LinkedList<>();


    /**
     * attachView and init presenter
     * @param presenters
     */
    public void attachViews(MvpView mvpView, MvpPresenter... presenters) {
        if(isNotEmpty(presenters)) {
            for (MvpPresenter presenter : presenters) {
                presenter.attachView(mvpView);
            }
        }
    }

    public void detachViews(MvpPresenter... presenters) {
        if(isNotEmpty(presenters)) {
            for (MvpPresenter presenter : presenters) {
                presenter.detachView();
            }
        }
    }

    public void addPresenter(MvpPresenter... presenters) {
        if(isNotEmpty(presenters)) {
            Collections.addAll(mList, presenters);
        }
    }

    public void removePresenter(MvpPresenter... presenters) {
        if(isNotEmpty(presenters)) {
            for (int i=0; i<presenters.length; i++) {
                mList.remove(presenters[i]);
                presenters[i] = null;
            }
        }
    }

    private boolean isNotEmpty(MvpPresenter... presenters) {
        return presenters != null && presenters.length > 0;
    }

    public List<MvpPresenter> getPresenters() {
        return mList;
    }

}
