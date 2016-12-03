package com.ssyijiu.mvpdemo2.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.nonview.router.Router;
import com.ssyijiu.mvpdemo2.R;
import com.ssyijiu.mvpdemo2.base.BaseActivity;
import com.ssyijiu.mvpdemo2.base.MvpPresenter;
import com.ssyijiu.mvpdemo2.presenter.LoginPresenter;
import com.ssyijiu.mvpdemo2.presenter.contract.LoginContract;
import com.yatatsu.autobundle.AutoBundleField;

import static com.ssyijiu.mvpdemo2.R.id.et_username;


public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginPresenter mLoginPresenter = new LoginPresenter();

    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView login_status;


    @AutoBundleField(required = false)
    String status;

    @AutoBundleField(required = false)
    String username;

    @AutoBundleField(required = false)
    int password;

    @AutoBundleField(required = false)
    Uri uri;

    @AutoBundleField(required = false)
    Bundle extras;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected MvpPresenter[] getPresenters() {
        return new MvpPresenter[]{mLoginPresenter};
    }

    @Override
    protected void initViewAndData() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        login_status = (TextView) findViewById(R.id.login_status);

        et_username.setText(username);
        et_password.setText(password+"");


        btn_login.setOnClickListener(view -> {

            String username1 = et_username.getText().toString().trim();
            String password1 = et_password.getText().toString().trim();
            mLoginPresenter.login(username1, password1);

        });

        btn_login.setOnLongClickListener(v -> {

//            LoginManager.isLogin = true;
            Router.create(uri).open(mContext);
            finish();
            return true;
        });

    }

    @Override
    protected void parseIntDataFromIntent(Intent intent) {

    }

    @Override
    public void showLoading() {
        login_status.setText(R.string.loading);
        saveStatus();

    }

    @Override
    public void toUserInfo() {
        login_status.setText(R.string.success);
        saveStatus();
        Intent intent = new Intent(this,UserInfoActivity.class);
        intent.putExtra("name","ssyijiu");
        startActivity(intent);
    }

    @Override
    public void showFailed() {
        login_status.setText(R.string.failed);
        saveStatus();
    }

    @Override
    public void showHello() {
        if(TextUtils.isEmpty(status)) {
            status = getResources().getString(R.string.hello);
        }
        login_status.setText(status);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        saveStatus();
    }

    public void saveStatus() {
        status = login_status.getText().toString();
    }

}
