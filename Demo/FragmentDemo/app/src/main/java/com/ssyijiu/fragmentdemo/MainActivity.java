package com.ssyijiu.fragmentdemo;

import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.ssyijiu.fragmentdemo.app.BaseActivity;

import static com.ssyijiu.fragmentdemo.R.layout.activity_main;

/**
 * Fragment 踩坑之旅
 * https://developer.android.com/guide/components/fragments.html
 */
public class MainActivity extends BaseActivity {

    private FrameLayout mContainer;
    private BottomBar mBottombar;

    @Override
    protected int getLayoutResId() {
        return activity_main;
    }

    @Override
    protected void initView() {

        mContainer = getView(R.id.container);
        mBottombar = getView(R.id.bottombar);

        initBottomBar();

    }


    private void initBottomBar() {
        mBottombar.setOnTabSelectListener((tabId) -> {

            switch (tabId) {
                case R.id.bottombar_home:
                    break;
                case R.id.bottombar_financing:
                    break;
                case R.id.bottombar_movie:
                    break;
                case R.id.bottombar_meizhi:
                    break;
            }
        });

        mBottombar.setOnTabReselectListener((tabId) -> {
        });

    }
}
