package com.stay4it.sample;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.stay4it.sample.adapter.TableAdapter;
import com.stay4it.sample.base.RecyclerActivity;
import com.stay4it.sample.bean.Table;
import com.stay4it.sample.config.Constant;
import com.stay4it.sample.config.IntentKey;
import com.stay4it.sample.config.SQL;
import com.stay4it.sample.utils.DbHelper;
import com.stay4it.sample.utils.IOUtil;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends RecyclerActivity<Table> {

    private DbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void initData() {
        mDbHelper = new DbHelper(this, Constant.DATABASE_NAME);

        mSQLiteDatabase = mDbHelper.open(Constant.DB_PASSWORD);

        Cursor cursor = mSQLiteDatabase.rawQuery(SQL.SELECT_ALL_TABLE, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            mDatas.add(new Table(name));

        }
        IOUtil.close(cursor);
        mSQLiteDatabase.close();

        mAdapter = new TableAdapter(R.layout.item_tablename, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(MainActivity.this,TableInfoActivity.class);
                intent.putExtra(IntentKey.TABLE_NAME,mDatas.get(position).name);
                startActivity(intent);
            }
        });
    }
}
