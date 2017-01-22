package com.stay4it.sample;

import android.content.Intent;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.ssyijiu.library.MLog;
import com.stay4it.sample.adapter.TableInfoAdapter;
import com.stay4it.sample.base.BaseActivity;
import com.stay4it.sample.config.Constant;
import com.stay4it.sample.config.IntentKey;
import com.stay4it.sample.config.SQL;
import com.stay4it.sample.utils.DbHelper;
import com.stay4it.sample.utils.IOUtil;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class TableInfoActivity extends BaseActivity {

    private DbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private String mTableName;

    private ScrollablePanel mScrollablePanel;
    private TableInfoAdapter mAdapter;


    /** 所有的列名 */
    private List<String> mColNameList;
    /** 存放二维表中的所有数据 */
    private List<List<String>> mInfoList = new ArrayList<>();

    int mMaxId = 0;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tableinfo;
    }

    @Override
    protected void initView() {
        mScrollablePanel = findView(R.id.scrollable_panel);
        mAdapter = new TableInfoAdapter();
    }

    @Override
    protected void parseIntent(Intent intent) {
        mTableName = intent.getStringExtra(IntentKey.TABLE_NAME);
    }

    @Override
    protected void initData() {
        mDbHelper = new DbHelper(this, Constant.DATABASE_NAME);
        mSQLiteDatabase = mDbHelper.open(Constant.DB_PASSWORD);


        mColNameList = getAllColName();

        getAllValues();

        // bug -> 要多加一个数据、否则这个控件显示不全
        mColNameList.add("");

        mAdapter.setIdList(getAllIds());
        mAdapter.setColNameList(mColNameList);
        mAdapter.setInfoList(mInfoList);

        mScrollablePanel.setPanelAdapter(mAdapter);

    }


    /**
     * 获取一个表所有的数据
     */
    private void getAllValues() {

        String sql = String.format("select * from %s;", mTableName);
        openDataBae();
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, null);

        // cursor 有数据
        if(cursor != null && cursor.getCount() > 0) {
            // 最大 id
            mMaxId = cursor.getCount();
            MLog.i(mColNameList);
            while (cursor.moveToNext()) {

                List<String> rowList = new ArrayList<>();

                // 获取一条记录
                for (String colName : mColNameList) {
                    String value = "";
                    try {
                        // 有些数据取出来不是 String. 避免崩溃
                        value = cursor.getString(cursor.getColumnIndex(colName));
                    } catch (Exception ignored) {
                    } finally {
                        rowList.add(value);
                    }
                }

                mInfoList.add(rowList);
            }
        }
        MLog.i(mInfoList);
        IOUtil.close(cursor);

    }

    /**
     * 获取一个表所有的列名
     */
    private List<String> getAllColName() {

        List<String> list = new ArrayList<>();
        openDataBae();
        Cursor cursor = mSQLiteDatabase.rawQuery(SQL.getSqlSelectAllCol(mTableName), null);
        while (cursor.moveToNext()) {
            String colName = cursor.getString(cursor.getColumnIndex("name"));
            list.add(colName);

        }
        IOUtil.close(cursor);
        return list;
    }

    /**
     * 获取所有的 id
     */
    private List<String> getAllIds() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= mMaxId; i++) {
            list.add(String.valueOf(i + 1));
        }

        return list;
    }

    /**
     * 打开数据库
     */
    private void openDataBae() {
        if (!mSQLiteDatabase.isOpen()) {
            mSQLiteDatabase = mDbHelper.open(Constant.DB_PASSWORD);
        }
    }
}
