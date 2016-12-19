package com.ssyijiu.fragmentdemo.ui;

import android.os.Bundle;

import com.ssyijiu.fragmentdemo.R;
import com.ssyijiu.fragmentdemo.app.BaseFragment;
import com.ssyijiu.fragmentdemo.app.FRAG;
import com.ssyijiu.library.MLog;


/**
 * Created by ssyijiu on 2016/12/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class HomeFragment extends BaseFragment {

    // Activity意外重启恢复Fragment时候默认会执行无参构造方法
    // 没有无参构造方法则抛出异常
    public HomeFragment() {
        MLog.i("HomeFragment");
    }

    public HomeFragment(int index) {
        FRAG.INDEX = index;
        MLog.i("HomeFragment with the index");
    }


    @Override
    protected void parseArguments(Bundle arguments) {
        FRAG.INDEX = arguments.getInt("index");
    }

    @Override
    protected int getFragmentResId() {
        switch (FRAG.INDEX) {
            case FRAG.HOME:
                return R.layout.fragment_home;
            case FRAG.GIRLS:
                return R.layout.fragment_girls;
            default:
                return R.layout.fragment_home;
        }

    }

    public static HomeFragment newInstance(int index) {
        HomeFragment f = new HomeFragment(index);
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

}
