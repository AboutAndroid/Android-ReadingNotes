package com.ssyijiu.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
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

    private AHBottomNavigation mBottombar;

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

        // Activity内存重启时会自动恢复Fragment,不需要重新添加
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

        // 内存重启后，会恢复所有的 Fragment,但是 Fragment 的 mHidden 属性默认 false,造成 Fragment 重叠
    }


    private void initBottomBar() {

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottombar_home, R.drawable.ic_bottombar_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottombar_girls, R.drawable.ic_bottombar_girls, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottombar_movie, R.drawable.ic_bottombar_movie, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottombar_financing, R.drawable.ic_bottombar_financing, R.color.colorPrimary);

        // Add items
        mBottombar.addItem(item1);
        mBottombar.addItem(item2);
        mBottombar.addItem(item3);
        mBottombar.addItem(item4);

        // Change colors
        mBottombar.setAccentColor(getColorFromId(R.color.colorPrimary));
        mBottombar.setInactiveColor(getColorFromId(R.color.GERY));

        // Manage titles
        mBottombar.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        // Set current item programmatically
        mBottombar.setCurrentItem(0);

        // Customize notification (title, background, typeface)
        mBottombar.setNotificationBackgroundColor(getColorFromId(R.color.colorPrimary));

        // Add or remove notification for each item
        mBottombar.setNotification("1", 3);

        // Set listeners
        mBottombar.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case FRAG.HOME:
                    showFragment(mHomeFragment);
                    break;
                case FRAG.GIRLS:
                    showFragment(mGirlsFragment);
                    break;
                case FRAG.MOVIE:
                    showFragment(mMovieFragment);
                    break;
                case FRAG.FINANCING:
                    showFragment(mFinancingFragment);
                    break;
            }

            // 再次选中
            if(wasSelected) {
                EventBus.getDefault().post(new TabReselectEvent(position));
            }
            return true;
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
