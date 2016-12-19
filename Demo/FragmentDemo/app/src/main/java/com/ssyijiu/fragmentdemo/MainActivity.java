package com.ssyijiu.fragmentdemo;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.ssyijiu.fragmentdemo.app.BaseActivity;
import com.ssyijiu.fragmentdemo.app.FRAG;
import com.ssyijiu.fragmentdemo.ui.HomeFragment;
import com.ssyijiu.library.MLog;

import static com.ssyijiu.fragmentdemo.R.layout.activity_main;

/**
 * Fragment 踩坑之旅
 * https://developer.android.com/guide/components/fragments.html
 * http://yifeng.studio/2016/12/15/android-fragment-attentions/
 */
public class MainActivity extends BaseActivity {

    private FrameLayout mContainer;
    private BottomBar mBottombar;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private HomeFragment mGirlsFragment;

    @Override
    protected int getLayoutResId() {
        return activity_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mContainer = getView(R.id.container);
        mBottombar = getView(R.id.bottombar);

        mFragmentManager = getFragmentManager();

        // Activity意外重启时会自动恢复Fragment,不需要重新添加
        if (savedInstanceState == null) {
            addFragment();
        }

        initBottomBar();
    }

    private void addFragment() {


        mHomeFragment = HomeFragment.newInstance(FRAG.HOME);
        mGirlsFragment = HomeFragment.newInstance(FRAG.GIRLS);

        addFragmentToActivity(mFragmentManager, mHomeFragment, R.id.container);
        addFragmentToActivity(mFragmentManager, mGirlsFragment, R.id.container);

        mFragmentManager.beginTransaction().show(mHomeFragment)
                .hide(mGirlsFragment)
                .commit();

    }


    private void initBottomBar() {
        mBottombar.setOnTabSelectListener((tabId) -> {

            switch (tabId) {
                case R.id.bottombar_home:
                    mFragmentManager.beginTransaction().show(mHomeFragment)
                            .hide(mGirlsFragment)
                            .commit();
                    break;
                case R.id.bottombar_financing:
                    break;
                case R.id.bottombar_movie:
                    break;
                case R.id.bottombar_girls:
                    mFragmentManager.beginTransaction().show(mGirlsFragment)
                            .hide(mHomeFragment)
                            .commit();
                    break;
            }
        });

        mBottombar.setOnTabReselectListener((tabId) -> {
        });

    }
}
