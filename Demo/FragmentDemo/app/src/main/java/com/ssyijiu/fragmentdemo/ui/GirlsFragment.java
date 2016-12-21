package com.ssyijiu.fragmentdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ssyijiu.fragmentdemo.R;
import com.ssyijiu.fragmentdemo.app.BaseFragment;
import com.ssyijiu.fragmentdemo.event.TabReselectEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ssyijiu on 2016/12/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class GirlsFragment extends BaseFragment {

    private TextView tvText;

    @Override
    protected int getFragmentResId() {
        return R.layout.fragment_girls;
    }

    @Override
    protected void initView(View rootView) {
        tvText = (TextView) rootView.findViewById(R.id.girls_tv_text);
    }

    @Override
    protected void initLazyData() {
        Toast.makeText(mActivity, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public static GirlsFragment newInstance() {
        GirlsFragment f = new GirlsFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Subscribe
    public void onEvent(TabReselectEvent event) {
        if(event.tabId == R.id.bottombar_girls) {
            tvText.setText("GIRLS");
        }
    }
}
