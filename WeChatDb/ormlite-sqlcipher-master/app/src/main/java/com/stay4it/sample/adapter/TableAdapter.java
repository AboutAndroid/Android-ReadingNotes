package com.stay4it.sample.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stay4it.sample.R;
import com.stay4it.sample.bean.Table;

import java.util.List;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class TableAdapter extends BaseQuickAdapter<Table,BaseViewHolder> {

    public TableAdapter(int layoutResId, List<Table> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Table item) {
        helper.setText(R.id.item_list_table_name,item.name);
    }
}
