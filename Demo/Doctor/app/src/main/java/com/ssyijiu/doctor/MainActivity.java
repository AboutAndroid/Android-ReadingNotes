package com.ssyijiu.doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ssyijiu.library.MLog;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int a;
    private int b;
    private int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.hello).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int a = 0;
        for (int i = 0; i < 15; i++) {
            a ++ ;
            if (a  + 6== 3) {
            }

        }
        MLog.i(a);
    }
}
