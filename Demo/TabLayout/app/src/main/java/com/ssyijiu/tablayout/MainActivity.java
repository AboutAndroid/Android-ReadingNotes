package com.ssyijiu.tablayout;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;

    ArrayList<String> mTitles = new ArrayList<>();
    ArrayList<String> mUrls = new ArrayList<>();
    ArrayList<WebView> mWebViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tab);



        initData();
    }

    private void initData() {

        mTitles.add("Baidu");
        mTitles.add("Github");
        mTitles.add("Xitu");

        mUrls.add("https://www.baidu.com/");
        mUrls.add("https://github.com/");
        mUrls.add("http://gold.xitu.io/");

        for (int i=0; i<mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
            WebView webView = new WebView(this);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(mUrls.get(i));
            mWebViews.add(webView);
        }


        // 设置Tab的模式 MODE_FIXED 固定，MODE_SCROLLABLE滚动
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        // 标签的显示方式：GRAVITY_CENTER居中 GRAVITY_FILL平铺
        // 只有在 MODE_FIXED 模式下有效
        // mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        // 设置指示器的颜色
        mTabLayout.setSelectedTabIndicatorColor(Color.RED);

        // 设置指示器的高度
        // mTabLayout.setSelectedTabIndicatorHeight(0);

        // 设置未选中和选中的颜色
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);

        mViewPager.setAdapter(new ViewPagerAdapter());


        // TabLayout和ViewPager联动 有Bug:TabLayout的变标签不显示了
        // mTabLayout.setupWithViewPager(mViewPager);

        // TabLayout跟随ViewPager联动
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // ViewPager跟随TabLayout联动
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // 默认选中索引为 1 的标签
        // mTabLayout.getTabAt(1).select();

    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            WebView webView = mWebViews.get(position);
            container.addView(webView);
            return webView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }
}
