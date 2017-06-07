package com.stay4it.sample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.scrollablepanel.library.PanelAdapter;
import com.stay4it.sample.R;

import java.util.List;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class TableInfoAdapter extends PanelAdapter {

    /**
     * id
     */
    private static final int ID_TYPE = 0;
    /**
     * 列名
     */
    private static final int COLNAME_TYPE = 1;
    /**
     * 信息
     */
    private static final int INFO_TYPE = 2;
    /**
     * 标题
     */
    private static final int TITLE_TYPE = 4;


    private List<String> mIdList;
    private List<String> mColNameList;
    private List<List<String>> mInfoList;

    @Override
    public int getRowCount() {
        return mIdList == null ? 0 : mIdList.size();
    }

    @Override
    public int getColumnCount() {
        return mColNameList == null ? 0 : mColNameList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {

        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case ID_TYPE:
                setId(row, (VH) holder);
                break;
            case COLNAME_TYPE:
                setName(column, (VH) holder);
                break;
            case INFO_TYPE:
                setInfo(row, column, (VH) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setInfo(row, column, (VH) holder);
        }

    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return ID_TYPE;
        }
        if (row == 0) {
            return COLNAME_TYPE;
        }
        return INFO_TYPE;
    }

    private void setId(int row, VH holder) {
        if (mIdList != null && mIdList.size() > 0) {
            String id = mIdList.get(row - 1);
            if (!TextUtils.isEmpty(id)) {
                ((TextView) holder.itemView).setText(id);
            }
        }
    }

    private void setName(int column, VH holder) {

        if (mColNameList != null && mColNameList.size() > 0) {
            final String name = mColNameList.get(column - 1);
            if (!TextUtils.isEmpty(name)) {
                ((TextView) holder.itemView).setText(name);
                ((TextView) holder.itemView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!name.equals("null")){
                            showDialog(v.getContext(),name);
                        }
                    }
                });
            }

        }

    }

    private void setInfo(int row, int column, VH holder) {

        if (mInfoList != null && mInfoList.size() > 0 && mColNameList != null && mColNameList.size() > 0) {
            final String info = mInfoList.get(row - 1).get(column - 1);
            if (!TextUtils.isEmpty(info)) {
                ((TextView) holder.itemView).setText(info);
                ((TextView) holder.itemView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      if (!info.equals("null")){
                          showDialog(v.getContext(),info);
                      }
                    }
                });
            }
        }
    }

    //显示基本的AlertDialog
    private void showDialog(Context context, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("详细信息");
        builder.setMessage(msg);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ID_TYPE:
                return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_id, parent, false));
            case TITLE_TYPE:
                return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_title, parent, false));
            case COLNAME_TYPE:
                return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_colname, parent, false));
        }
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tableinfo, parent, false));
    }

    private static class VH extends RecyclerView.ViewHolder {

        VH(View itemView) {
            super(itemView);
        }
    }

    public void setIdList(List<String> idList) {
        mIdList = idList;
    }


    public void setColNameList(List<String> colNameList) {
        mColNameList = colNameList;
    }

    public void setInfoList(List<List<String>> infoList) {
        mInfoList = infoList;
    }

}
