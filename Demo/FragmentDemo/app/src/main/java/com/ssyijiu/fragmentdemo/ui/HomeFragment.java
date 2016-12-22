package com.ssyijiu.fragmentdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ssyijiu.fragmentdemo.R;
import com.ssyijiu.fragmentdemo.app.BaseFragment;
import com.ssyijiu.fragmentdemo.app.FRAG;
import com.ssyijiu.fragmentdemo.event.TabReselectEvent;
import com.ssyijiu.library.MLog;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by ssyijiu on 2016/12/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class HomeFragment extends BaseFragment {

    private TextView tvText;

    // Activity意外重启恢复Fragment时候默认会执行无参构造方法
    // 没有无参构造方法则抛出异常
    public HomeFragment() {
        MLog.i("HomeFragment");
    }

    @Override
    protected int getFragmentResId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initView(View rootView) {
        tvText = (TextView) rootView.findViewById(R.id.home_tv_text);
    }

    @Override
    protected void initLazyData() {
        Toast.makeText(mActivity, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Subscribe
    public void onEvent(TabReselectEvent event) {
        if(event.position == FRAG.HOME) {
            tvText.setText("HOME");
        }
    }

}
