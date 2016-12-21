package com.ssyijiu.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.ssyijiu.fragmentdemo.app.BaseActivity;
import com.ssyijiu.fragmentdemo.app.FRAG;
import com.ssyijiu.fragmentdemo.event.TabReselectEvent;
import com.ssyijiu.fragmentdemo.ui.FinancingFragment;
import com.ssyijiu.fragmentdemo.ui.GirlsFragment;
import com.ssyijiu.fragmentdemo.ui.HomeFragment;
import com.ssyijiu.fragmentdemo.ui.MovieFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * Fragment 踩坑之旅
 * https://developer.android.com/guide/components/fragments.html
 * http://yifeng.studio/2016/12/15/android-fragment-attentions/
 */
public class MainActivity extends BaseActivity {

    private BottomBar mBottombar;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private GirlsFragment mGirlsFragment;
    private FinancingFragment mFinancingFragment;
    private MovieFragment mMovieFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

        mBottombar = getView(R.id.bottombar);
        mFragmentManager = getFragmentManager();

        // Activity意外重启时会自动恢复Fragment,不需要重新添加
        if (savedInstanceState == null) {
            addFragment();
        } else {
            recoverFragment();
        }

        initBottomBar();
    }

    private void addFragment() {
        mHomeFragment = HomeFragment.newInstance();
        mMovieFragment = MovieFragment.newInstance();
        mFinancingFragment = FinancingFragment.newInstance();
        mGirlsFragment = GirlsFragment.newInstance();

        addFragmentToActivity(mFragmentManager, mHomeFragment, R.id.container,FRAG.TAG_HOME);
        addFragmentToActivity(mFragmentManager, mFinancingFragment, R.id.container,FRAG.TAG_FINANCING);
        addFragmentToActivity(mFragmentManager, mMovieFragment, R.id.container,FRAG.TAG_MOVIE);
        addFragmentToActivity(mFragmentManager, mGirlsFragment, R.id.container,FRAG.TAG_GIRLS);

        showFragment(mHomeFragment);
    }

    private void recoverFragment() {
        mHomeFragment = (HomeFragment) mFragmentManager.findFragmentByTag(FRAG.TAG_HOME);
        mGirlsFragment = (GirlsFragment) mFragmentManager.findFragmentByTag(FRAG.TAG_GIRLS);
        mMovieFragment = (MovieFragment) mFragmentManager.findFragmentByTag(FRAG.TAG_MOVIE);
        mFinancingFragment = (FinancingFragment) mFragmentManager.findFragmentByTag(FRAG.TAG_FINANCING);

    }


    private void initBottomBar() {
        mBottombar.setOnTabSelectListener((tabId) -> {
            switch (tabId) {
                case R.id.bottombar_home:  // mBottombar 会默认执行选中第一个tab一次
                    showFragment(mHomeFragment);
                    break;
                case R.id.bottombar_financing:
                    showFragment(mFinancingFragment);
                    break;
                case R.id.bottombar_movie:
                    showFragment(mMovieFragment);
                    break;
                case R.id.bottombar_girls:
                    showFragment(mGirlsFragment);
                    break;
            }
        });

        mBottombar.setOnTabReselectListener((tabId) -> {
            EventBus.getDefault().post(new TabReselectEvent(tabId));
        });

    }

    private void showFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .hide(mHomeFragment)
                .hide(mGirlsFragment)
                .hide(mMovieFragment)
                .hide(mFinancingFragment)
                .show(fragment)
                .commit();
    }
}
