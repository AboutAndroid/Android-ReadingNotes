package com.stay4it.sample.base;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stay4it.sample.R;

import java.util.ArrayList;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * 只有一个 RecyclerVew 的 Activity
 */

public class RecyclerActivity<T> extends BaseActivity {

    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter<T,BaseViewHolder> mAdapter;
    protected ArrayList<T> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {

        mRecyclerView = findView(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
