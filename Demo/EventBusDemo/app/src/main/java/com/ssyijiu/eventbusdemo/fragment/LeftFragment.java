package com.ssyijiu.eventbusdemo.fragment;



import android.view.View;
import android.widget.Button;


import com.ssyijiu.eventbusdemo.widget.SimpleDialog;
import com.ssyijiu.eventbusdemo.R;
import com.ssyijiu.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeftFragment extends BaseFragment {

    String contentText;
    @BindView(R.id.frag_left_first)
    Button fragLeftFirst;
    @BindView(R.id.frag_left_second)
    Button fragLeftSecond;
    @BindView(R.id.frag_left_third)
    Button fragLeftThird;
    @BindView(R.id.frag_left_four)
    Button fragLeftFour;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_left;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, mRootView);
    }

    @OnClick({R.id.frag_left_first, R.id.frag_left_second, R.id.frag_left_third, R.id.frag_left_four})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_left_first:
                contentText = "第一章";
                EventBus.getDefault().post(new MessageEvent(contentText));
                break;
            case R.id.frag_left_second:
                contentText = "第二章";
                EventBus.getDefault().post(new MessageEvent(contentText));
                break;
            case R.id.frag_left_third:
                contentText = "第三章";
                EventBus.getDefault().post(new MessageEvent(contentText));
                break;
            case R.id.frag_left_four:
                SimpleDialog dialog = new SimpleDialog(getActivity(),"标题","这里是消息");
                dialog.show();
                break;
        }
    }
}
