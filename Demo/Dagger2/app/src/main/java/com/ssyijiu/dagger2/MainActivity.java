package com.ssyijiu.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ssyijiu.library.MLog;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    Test mTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder().build().inject(this);

        MLog.i(mTest);
    }
}
