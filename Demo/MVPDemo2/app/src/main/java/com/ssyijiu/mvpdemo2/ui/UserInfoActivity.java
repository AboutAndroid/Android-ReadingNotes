package com.ssyijiu.mvpdemo2.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ssyijiu.mvpdemo2.R;
import com.ssyijiu.mvpdemo2.base.BaseActivity;
import com.ssyijiu.mvpdemo2.base.MvpPresenter;
import com.ssyijiu.mvpdemo2.presenter.UserInfoPresenter;
import com.yatatsu.autobundle.AutoBundleField;


/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */



public class UserInfoActivity extends BaseActivity implements UserInfoContract.View{

    UserInfoPresenter mUserInfoPresenter = new UserInfoPresenter();

    TextView tvUserInfo;

    @AutoBundleField(key = "name",required = false)
    String userInfo;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected MvpPresenter[] getPresenters() {
        return new MvpPresenter[]{mUserInfoPresenter};
    }

    @Override
    protected void initView() {
        tvUserInfo = (TextView) findViewById(R.id.tv_userinfo);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInfoPresenter.show();
            }
        });
    }

    @Override
    protected void parseIntent(Intent intent) {
    }

    @Override
    public void showLoading() {
        if(TextUtils.isEmpty(userInfo)) {
            tvUserInfo.setText(R.string.press_to_show);
        } else {
            tvUserInfo.setText(userInfo);
        }

    }

    @Override
    public void showUserInfo() {
        tvUserInfo.setText(userInfo);
    }
}
