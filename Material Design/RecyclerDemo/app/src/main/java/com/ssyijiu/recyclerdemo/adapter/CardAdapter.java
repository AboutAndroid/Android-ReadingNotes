package com.ssyijiu.recyclerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssyijiu.recyclerdemo.ImageUrls;
import com.ssyijiu.recyclerdemo.R;
import com.ssyijiu.recyclerdemo.utils.DensityUtil;
import com.ssyijiu.vinci.imageloader.Vinci;
import com.ssyijiu.vinci.weight.ImageVinci;

import java.util.Random;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.VH> {
    private Context mContext;
    private Random mRandom = new Random();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CardAdapter(Context context) {
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                mOnItemClickListener.onItemClick(position);
            });
        }

        if(mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(position);
                    return false;
                }
            });
        }

        setImageHeight(holder);
        Vinci.getInstance().loadImage(mContext, ImageUrls.INSTANCE.get(position), holder.mImageView);
        holder.mTextView.setText(R.string.desc);
    }

    @Override
    public int getItemCount() {
        return ImageUrls.INSTANCE.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        ImageVinci mImageView;
        TextView mTextView;

        VH(View itemView) {
            super(itemView);
            mImageView = (ImageVinci) itemView.findViewById(R.id.item_image);
            mTextView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    public void addData(int position) {
        ImageUrls.INSTANCE.mUrls.add(position, ImageUrls.INSTANCE.ONLY);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        ImageUrls.INSTANCE.mUrls.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 设置 ImageView 的高度
     */
    private void setImageHeight(VH viewHolder) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewHolder.mImageView.getLayoutParams();
        layoutParams.height = mRandom.nextInt(DensityUtil.dp2px(101)) + DensityUtil.dp2px(200); // 200 ~ 300
        viewHolder.mImageView.setLayoutParams(layoutParams);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
}
