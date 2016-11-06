package com.ssyijiu.dagger2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.dagger2_2.annotation.ForBoy;
import com.ssyijiu.dagger2_2.annotation.ForGirl;
import com.ssyijiu.dagger2_2.component.DaggerMainComponent;
import com.ssyijiu.dagger2_2.modules.CModule;
import com.ssyijiu.library.MLog;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

/**
 * https://github.com/luxiaoming/dagger2Demo
 */
public class MainActivity extends AppCompatActivity {

//    @ForBoy

    @Inject
    Lazy<Test> mTest;

//    @ForGirl
    @Inject
    Test mTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerAppComponent.builder()
//                .androidModule(new AndroidModule((App) getApplication()))
//                .build().inject(this);


//        DaggerMainComponent.builder()
//                .appComponent((((App) getApplication()).getComponent()))
//                .cModule(new CModule())
//                .build().inject(this);

//        DaggerMainComponent.builder()
//                .cModule(new CModule(5))
//                .build().inject(this);

        DaggerMainComponent.builder().build().inject(this);


        MLog.e(mTest+":"+mTest.get());
        MLog.e(mTest2+":"+mTest2);
    }
}
