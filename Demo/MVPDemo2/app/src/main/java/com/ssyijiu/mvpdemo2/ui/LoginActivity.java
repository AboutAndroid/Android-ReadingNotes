package com.ssyijiu.mvpdemo2.ui;


import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ssyijiu.mvpdemo2.base.BaseActivity;
import com.ssyijiu.mvpdemo2.R;
import com.ssyijiu.mvpdemo2.presenter.LoginContract;
import com.ssyijiu.mvpdemo2.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView login_status;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        login_status = (TextView) findViewById(R.id.login_status);


        btn_login.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                getPresenter().login(username, password);

            }
        });


    }

    @Override
    protected void parseIntDataFromIntent(Intent intent) {

    }

    @Override
    public LoginPresenter onLoadPresenter() {
        return LoginPresenter.getInstance();
    }


    @Override
    public void showLoading() {
        login_status.setText("loading...");
    }

    @Override
    public void showSuccess() {
        login_status.setText("success");
    }

    @Override
    public void showFailed() {
        login_status.setText("failed");
    }

    @Override
    public void showHello() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}
