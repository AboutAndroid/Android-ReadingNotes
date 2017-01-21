package com.ssyijiu.recyclerdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ssyijiu.library.MLog;
import com.ssyijiu.recyclerdemo.adapter.CardAdapter;
import com.ssyijiu.recyclerdemo.adapter.QuickAdapter;
import com.ssyijiu.recyclerdemo.utils.ToastUtil;
import com.ssyijiu.vinci.imageloader.Vinci;
import com.ssyijiu.vinci.weight.ImageVinci;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.ssyijiu.library.MLog.LogLev.I;

/**
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    // CardAdapter mAdapter;

    QuickAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }


    private void initView() {

        // 设置布局管理器
        // final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 设置 Adapter
        // mAdapter = new CardAdapter(this);


        // 使用万能适配器
        mAdapter = new QuickAdapter<String>(this, ImageUrls.INSTANCE.mUrls, R.layout.item_list) {

            @Override
            public void convert(QuickViewHolder holder, String item) {
                ImageVinci vinci = holder.getView(R.id.item_image);

                Vinci.getInstance().loadImage(holder.mContext, item, vinci);

                holder.setText(R.id.item_text, R.string.desc);
            }
        };


        // 添加分割线
        // DividerItemDecoration mDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        // 自定义分割线
        // mDecor.setDrawable(getResources().getDrawable(R.drawable.shape_item_divider));
        // mRecyclerView.addItemDecoration(mDecor);

        // 设置 Item 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置点击事件
        mAdapter.setOnItemClickListener((itemView, position) -> {
            ToastUtil.show("点击: " + position);
            mAdapter.addData(position, ImageUrls.INSTANCE.ONLY);
        });

        mAdapter.setOnItemLongClickListener((itemView, position) -> {
            ToastUtil.show("长按: " + position);
            mAdapter.removeData(position);
        });
        mRecyclerView.setAdapter(mAdapter);

    }


    // 实现沉浸式状态栏
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
