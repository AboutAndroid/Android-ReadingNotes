package com.ssyijiu.eventbusdemo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


import com.ssyijiu.eventbusdemo.fragment.LeftFragment;
import com.ssyijiu.eventbusdemo.fragment.RightFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.fragment_left)
    FrameLayout fragmentLeft;
    @BindView(R.id.fragment_right)
    FrameLayout fragmentRight;
    private FragmentTransaction beginTransaction;
    private LeftFragment leftFragment;
    private RightFragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        beginTransaction = getFragmentManager().beginTransaction();
        leftFragment = new LeftFragment();
        rightFragment = new RightFragment();
        beginTransaction.add(R.id.fragment_left, leftFragment, "LEFT");
        beginTransaction.add(R.id.fragment_right, rightFragment, "RIGHT");

        beginTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beginTransaction.remove(rightFragment);
        beginTransaction.remove(leftFragment);
    }
}
