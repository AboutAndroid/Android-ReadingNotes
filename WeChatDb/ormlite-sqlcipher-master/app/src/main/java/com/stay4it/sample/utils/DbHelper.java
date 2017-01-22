package com.stay4it.sample.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.stay4it.sample.utils.IOUtil;
import com.stay4it.sample.utils.ToastUtil;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DbHelper extends SQLiteOpenHelper {

    private File mDBFile;
    private SQLiteDatabase mDb;
    private String mDbName;
    private Context mContext;


    private SQLiteDatabaseHook mHook = new SQLiteDatabaseHook() {
        public void preKey(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase
                    .rawExecSQL("PRAGMA cipher_default_use_hmac = off;");
        }

        public void postKey(SQLiteDatabase sqLiteDatabase) {
        }
    };

    public DbHelper(Context context, String dbName) {
        super(context, dbName, null, 1);
        mContext = context;
        mDbName = dbName;

        mDBFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + mContext.getFilesDir(), dbName);

        //load sqlcipher libraries
        SQLiteDatabase.loadLibs(context.getApplicationContext());

    }


    public SQLiteDatabase open(String password) {

        if(!mDBFile.exists()) {
            mDBFile.getParentFile().mkdirs();
        }

        loadDb(mDbName);

        mDb = SQLiteDatabase.openOrCreateDatabase(mDBFile.getAbsolutePath(), password,
                null, mHook);

        return mDb;
    }

    private void loadDb(String fileName) {

        if (!mDBFile.exists()) {
            AssetManager assets = mContext.getAssets();
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assets.open(fileName);

                out = new FileOutputStream(mDBFile);

                int len;
                byte[] buf = new byte[4028];
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtil.close(in);
                IOUtil.close(out);
            }
        }
    }



    public void deleteDb() {
        if (mDBFile.exists()) {
            mDBFile.delete();
            ToastUtil.show("delete database file.");
        }
    }


    public synchronized void closeDb() throws SQLException {
        if (mDb != null) {
            mDb.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
