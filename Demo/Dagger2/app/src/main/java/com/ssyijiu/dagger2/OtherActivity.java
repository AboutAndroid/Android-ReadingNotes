package com.ssyijiu.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.ssyijiu.dagger2.bean.Poetry;
import com.ssyijiu.dagger2.component.MainComponent;
import com.ssyijiu.library.MLog;

import javax.inject.Inject;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class OtherActivity extends AppCompatActivity {

    @Inject
    Gson mGson;

    @Inject
    Poetry mPoetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        MainComponent.getInstance().inject(this);

        MLog.i(mPoetry+":"+mGson.toJson(mPoetry));
    }
}
