package com.stay4it.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import com.stay4it.sample.utils.ToastUtil;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

import static android.R.attr.name;

public class MainActivity extends RecyclerActivity<Table> {

    private DbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private String fileName, password;

    @Override
    protected void initData() {
        fileName = getIntent().getStringExtra("fileName");
        mDbHelper = new DbHelper(this, fileName);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout,
                (ViewGroup) findViewById(R.id.dialog));
        final EditText pwd = (EditText) layout.findViewById(R.id.etname);
        new AlertDialog.Builder(this).setTitle("请输入数据库密码").setView(layout).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            password = pwd.getText().toString();
                            checkPwd(password);
                        } catch (Exception e) {
                            finish();
                            ToastUtil.show("密码错误！");
                        }
                    }
                })
                .show();

    }

    private void checkPwd(String pwd) throws Exception {
        mSQLiteDatabase = mDbHelper.open(pwd);
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

                Intent intent = new Intent(MainActivity.this, TableInfoActivity.class);
                intent.putExtra(IntentKey.TABLE_NAME, mDatas.get(position).name);
                intent.putExtra("fileName",fileName);
                intent.putExtra("pwd",password);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TableInfoActivity.class);
                intent.putExtra("sql","select * from "+sqlTable.getText().toString()+" where "+sqlTerm.getText().toString());
                intent.putExtra("fileName",fileName);
                intent.putExtra("pwd",password);
                intent.putExtra(IntentKey.TABLE_NAME,sqlTable.getText().toString());
                startActivity(intent);
            }
        });
    }
}
