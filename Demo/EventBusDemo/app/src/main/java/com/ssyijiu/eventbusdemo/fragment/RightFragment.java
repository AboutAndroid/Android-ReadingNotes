package com.ssyijiu.eventbusdemo.fragment;

import android.text.TextUtils;
import android.widget.TextView;


import com.ssyijiu.eventbusdemo.R;
import com.ssyijiu.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RightFragment extends BaseFragment {


    @BindView(R.id.frag_right_tv)
    TextView content;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_right;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, mRootView);
    }

    @Subscribe
    public void onEventMainThread(MessageEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.message;
        setContent(msg);
    }

    public void setContent(String text) {
        if (!TextUtils.isEmpty(text)) {
            content.setText(text);
        }
    }

}
