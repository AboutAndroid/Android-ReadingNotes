package com.ssyijiu.mvpdemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzh.nonview.router.Router;
import com.ssyijiu.mvpdemo2.router.RouteUrl;
import com.ssyijiu.mvpdemo2.router.Routes;
import com.ssyijiu.mvpdemo2.ui.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        findViewById(R.id.button).setOnClickListener(v->{
            Router.create(RouteUrl.newUrl(Routes.INFO,
                    new RouteUrl.ParamMap().add("name","ssyijiu")))
                    .open(MainActivity.this);

//            Router.create(RouteUrl.newUrl(Routes.LOGIN,
//                    new RouteUrl.ParamMap()
//                            .add(Routes.LOGIN_USERNAME,"ssyijiu")
//                            .add(Routes.LOGIN_PASSWORD,"123456")))
//                    .open(MainActivity.this);

        });
    }
}
