package com.ssyijiu.recyclerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Toast;

import com.ssyijiu.recyclerdemo.adapter.RecycleAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swiperefreshlayout)
    SwipeRefreshLayout refreshLayout;

    ArrayList<String> mDatas = new ArrayList<>();

    private RecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        initData();
        initRecyclerView();

    }

    private void initData() {
        String datas[] = {"Cloud", "Movie", "Laptop", "Loop", "Menu", "Mood", "Palette", "Search", "Time", "Work"};
        mDatas.addAll(Arrays.asList(datas));
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(this, mDatas);
        recyclerView.setAdapter(adapter);

        // 上拉加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    refreshLayout.setRefreshing(true);
                    mDatas.addAll(mDatas);
                    adapter.notifyDataSetChanged();
                    new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 2000);
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 2000);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//        }
//    }
}
