package com.ssyijiu.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.ssyijiu.dagger2.bean.Poetry;
import com.ssyijiu.dagger2.component.MainComponent;
import com.ssyijiu.library.MLog;

import javax.inject.Inject;

/**
 * http://zpayh.xyz/2016/07/07/Dagger2%E4%BD%BF%E7%94%A8%E8%AF%A6%E8%A7%A3/
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    Poetry mPoetry;


    @Inject
    Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLog.TAG = "dagger2_ssyijiu";


        MainComponent.getInstance().inject(this);

        MLog.i(mGson);
        MLog.i(mPoetry+":"+mGson.toJson(mPoetry));


    }

    public void onclick(View view) {
        startActivity(new Intent(this,OtherActivity.class));
    }
}

