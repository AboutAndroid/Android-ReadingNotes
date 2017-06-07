package com.stay4it.sample.base;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stay4it.sample.R;

import java.util.ArrayList;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * <p>
 * 只有一个 RecyclerVew 的 Activity
 */

public class RecyclerActivity<T> extends BaseActivity {

    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected ArrayList<T> mDatas = new ArrayList<>();
    public EditText sqlTable, sqlTerm;
    public Button button;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {

        mRecyclerView = findView(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        sqlTable = (EditText) findViewById(R.id.sql_table);
        sqlTable.setText("rcontact");
        sqlTerm = (EditText) findViewById(R.id.sql_term);
        sqlTerm.setText("type='33'");
        button = (Button) findViewById(R.id.button);


    }
}
