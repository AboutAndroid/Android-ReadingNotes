package com.ssyijiu.recyclerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssyijiu.picassof.imageloader.ImageClient;
import com.ssyijiu.picassof.weight.MImageView;
import com.ssyijiu.recyclerdemo.ImageUrls;
import com.ssyijiu.recyclerdemo.R;


public class RecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private SparseArray<View> viewArray = new SparseArray<>();


    public RecycleAdapter(Context context) {
        mContext = context;
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


        ImageClient.getInstance().loadImage(mContext,ImageUrls.INSTANCE.get(position),viewHolder.mImageView);

        viewHolder.mTextView.setText("Use Glide load Girls");
    }

    @Override
    public int getItemCount() {
        return ImageUrls.INSTANCE.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        MImageView mImageView;
        TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (MImageView) itemView.findViewById(R.id.item_image);
            mTextView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

}
