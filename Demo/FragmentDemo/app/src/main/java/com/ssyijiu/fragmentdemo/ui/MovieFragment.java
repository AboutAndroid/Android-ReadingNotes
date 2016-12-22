package com.ssyijiu.fragmentdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ssyijiu.fragmentdemo.R;
import com.ssyijiu.fragmentdemo.app.BaseFragment;
import com.ssyijiu.fragmentdemo.app.FRAG;
import com.ssyijiu.fragmentdemo.event.TabReselectEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ssyijiu on 2016/12/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MovieFragment extends BaseFragment{

    private TextView tvText;

    @Override
    protected int getFragmentResId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView(View rootView) {
        tvText = (TextView) rootView.findViewById(R.id.movie_tv_text);
    }

    @Override
    protected void initLazyData() {
        Toast.makeText(mActivity, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public static MovieFragment newInstance() {
        MovieFragment f = new MovieFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Subscribe
    public void onEvent(TabReselectEvent event) {
        if(event.position == FRAG.MOVIE) {
            tvText.setText("MOVIE");
        }
    }
}
