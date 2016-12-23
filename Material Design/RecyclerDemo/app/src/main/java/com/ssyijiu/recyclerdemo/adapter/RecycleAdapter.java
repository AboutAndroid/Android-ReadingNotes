package com.ssyijiu.recyclerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssyijiu.recyclerdemo.R;

import java.util.List;

/**
 * Created by lxm on 2016/5/3.
 */
public class RecycleAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<String> mDatas;
    SparseArray<View> viewArray = new SparseArray<>();


    public RecycleAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
        viewArray.put(itemView.hashCode(),itemView);
        System.out.println(viewArray.size());
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(position + ":" + mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
