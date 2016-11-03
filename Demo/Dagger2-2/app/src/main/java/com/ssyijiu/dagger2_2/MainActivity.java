package com.ssyijiu.dagger2_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * https://github.com/luxiaoming/dagger2Demo
 */
public class MainActivity extends AppCompatActivity {

    @Named("boy")
    @Inject
    Test mTest;

    @Named("girl")
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

        DaggerMainComponent.builder()
                .cModule(new CModule(5))
                .build().inject(this);



        MLog.e(mTest+":"+mTest.mAge);
        MLog.e(mTest2+":"+mTest2.mAge);
    }
}
