package com.ssyijiu.recyclerdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ssyijiu on 2017/1/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.QuickViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private LayoutInflater mInflater;
    private int mLayoutId;


    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;


    public QuickAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public QuickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mLayoutId, parent, false);

        QuickViewHolder holder = new QuickViewHolder(itemView, mContext);

        // 设置点击事件
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(v -> {
                mOnItemClickListener.onItemClick(itemView, holder.getLayoutPosition());
            });
        }
        // 设置长按事件
        if (mOnItemLongClickListener != null) {
            itemView.setOnLongClickListener(v -> {
                mOnItemLongClickListener.onItemLongClick(itemView, holder.getLayoutPosition());
                return false;
            });
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(QuickViewHolder holder, int position) {

        convert(holder, getItem(position));

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public T getItem(int position) {
        if (position < 0 || position > getItemCount() - 1) {
            throw new RuntimeException("the position must in [0,data.size())");
        }
        return mDatas.get(position);
    }


    public void addData(int position, T item) {
        mDatas.add(position, item);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


    public abstract void convert(QuickViewHolder holder, T item);

    public static class QuickViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;
        public Context mContext;

        QuickViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            mViews = new SparseArray<>();
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public View getItemView() {
            return itemView;
        }

        public QuickViewHolder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        public QuickViewHolder setText(int viewId, int resId) {
            TextView tv = getView(viewId);
            tv.setText(resId);
            return this;
        }

        public QuickViewHolder setImageResource(int viewId, int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        public QuickViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        public QuickViewHolder setImageDrawable(int viewId, Drawable drawable) {
            ImageView view = getView(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        public QuickViewHolder setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public QuickViewHolder setBackgroundRes(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        public QuickViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        public QuickViewHolder setTextColorRes(int viewId, int textColorRes) {
            TextView view = getView(viewId);
            view.setTextColor(mContext.getResources().getColor(textColorRes));
            return this;
        }

        public QuickViewHolder setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getView(viewId).setAlpha(value);
            } else {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                getView(viewId).startAnimation(alpha);
            }
            return this;
        }

        public QuickViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        public QuickViewHolder linkify(int viewId) {
            TextView view = getView(viewId);
            Linkify.addLinks(view, Linkify.ALL);
            return this;
        }

        public QuickViewHolder setTypeface(Typeface typeface, int... viewIds) {
            for (int viewId : viewIds) {
                TextView view = getView(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
            return this;
        }

        public QuickViewHolder setProgress(int viewId, int progress) {
            ProgressBar view = getView(viewId);
            view.setProgress(progress);
            return this;
        }

        public QuickViewHolder setProgress(int viewId, int progress, int max) {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        public QuickViewHolder setMax(int viewId, int max) {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            return this;
        }

        public QuickViewHolder setRating(int viewId, float rating) {
            RatingBar view = getView(viewId);
            view.setRating(rating);
            return this;
        }

        public QuickViewHolder setRating(int viewId, float rating, int max) {
            RatingBar view = getView(viewId);
            view.setMax(max);
            view.setRating(rating);
            return this;
        }


        public QuickViewHolder setChecked(int viewId, boolean checked) {
            Checkable view = (Checkable) getView(viewId);
            view.setChecked(checked);
            return this;
        }


        // 关于事件的
        public QuickViewHolder setOnClickListener(int viewId,
                                                  View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public QuickViewHolder setOnTouchListener(int viewId,
                                                  View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        public QuickViewHolder setOnLongClickListener(int viewId,
                                                      View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int position);
    }
}
