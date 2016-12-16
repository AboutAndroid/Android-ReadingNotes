package com.ssyijiu.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.ssyijiu.fragmentdemo.app.BaseActivity;
import com.ssyijiu.fragmentdemo.ui.HomeFragment;

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
        initFragment();

    }

    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();
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
                case R.id.bottombar_girls:
                    break;
            }
        });

        mBottombar.setOnTabReselectListener((tabId) -> {
        });

    }
}
