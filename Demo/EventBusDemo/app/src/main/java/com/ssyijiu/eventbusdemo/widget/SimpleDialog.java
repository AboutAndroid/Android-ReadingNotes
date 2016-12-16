package com.ssyijiu.eventbusdemo.widget;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ssyijiu.eventbusdemo.R;


/**
 * Created by ssyijiu on 2016/8/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class SimpleDialog extends Dialog implements View.OnClickListener {

    private TextView btnConfirm;
    private TextView btnCancel;

    private TextView tvMessage;
    private TextView tvTitle;

    private String title;
    private String message;

    private View lineHorizontal;
    private View lineVertical;




    public SimpleDialog(Context context, String title, String message) {
        super(context);
        this.title = title;
        this.message = message;
        initView(context);
    }
    public SimpleDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(R.layout.simpledialog_layout);

        btnConfirm = (TextView) findViewById(R.id.btn_confirm);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);

        lineVertical = findViewById(R.id.line_vertical);
        lineHorizontal = findViewById(R.id.line_horizontal);

        setTitle(title);
        setMessage(message);

        isHasNegativeView(false);

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    /**
     * 设置标题，不设置默认不显示。
     * @param title
     * @return
     */
    public SimpleDialog setTitle(String title) {
        this.title = title;
        if(!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        return this;
    }

    public SimpleDialog setMessage(String message) {
        this.message = message;
        if(!TextUtils.isEmpty(message)) {
            tvMessage.setText(this.message);
        }
        return this;
    }

    /**
     * 设置确认按钮。
     * listener为null,点击事件为dismiss
     * @param confirm_text
     * @param listener
     * @return
     */
    public SimpleDialog setPositiveView(String confirm_text, View.OnClickListener listener) {

        if(!TextUtils.isEmpty(confirm_text)) {
            btnConfirm.setText(confirm_text);
        }

        if(listener != null) {
            btnConfirm.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置取消按钮，不设置默认不显示。
     * listener为null,点击事件为dismiss
     * @param cancel_text
     * @param listener
     * @return
     */
    public SimpleDialog setNegativeView(String cancel_text, View.OnClickListener listener) {
        isHasNegativeView(true);
        if(!TextUtils.isEmpty(cancel_text)) {
            btnCancel.setText(cancel_text);
        }

        if(listener != null) {
            btnCancel.setOnClickListener(listener);
        }
        return this;
    }

    private void isHasNegativeView(boolean flag) {
        btnCancel.setVisibility(flag ? View.VISIBLE:View.GONE);
        lineVertical.setVisibility(flag ? View.VISIBLE:View.GONE);

    }

    // 默认点击事件
    @Override
    public void onClick(View v) {
        dismiss();
    }

    /**
     * 设置分割线的颜色
     * @param color
     */
    public SimpleDialog setLineColor(int color) {
        lineVertical.setBackgroundColor(color);
        lineHorizontal.setBackgroundColor(color);
        return this;
    }


    /**
     * 设置Dialog的宽度, 单位px
     * @param width
     * @return
     */
    public SimpleDialog setWidth(int width) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width;
        getWindow().setAttributes(lp);
        return this;
    }

    public TextView getNegativeView() {
        return btnConfirm;
    }

    public TextView getPositiveView() {
        return btnCancel;
    }

    public TextView getTvMessage() {
        return tvMessage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

}