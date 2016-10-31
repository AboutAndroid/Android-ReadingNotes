package com.ssyijiu.dagger2_1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;


/**
 * http://www.jianshu.com/p/01d3c014b0b1
 */
public class MainActivity extends AppCompatActivity {


    @Inject
    OkHttpClient mOkHttpClient;


    @Inject
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MLog.TAG = "ssyijiu_dagger2";

        ((App)getApplication()).getNetComponent().inject(this);

        MLog.i(mOkHttpClient);
        MLog.i(mSharedPreferences);
    }
}
