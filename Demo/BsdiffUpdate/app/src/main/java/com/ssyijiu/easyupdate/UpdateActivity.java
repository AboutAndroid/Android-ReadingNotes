package com.ssyijiu.easyupdate;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import com.ssyijiu.easyupdate.callback.OnProgressCallback;
import com.ssyijiu.easyupdate.tools.$;
import com.ssyijiu.easyupdate.tools.BsPatch;
import java.io.File;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private String downUrl
        = "http://shouji.360tpcdn.com/160914/c5164dfbbf98a443f72f32da936e1379/com.tencent.mobileqq_410.apk";
    private String bspatchUrl
        = "http://shouji.360tpcdn.com/160914/c5164dfbbf98a443f72f32da936e1379/com.tencent.mobileqq_410.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        Updater.instance().setProgressCallback(new OnProgressCallback() {
            @Override public void onProgress(int progress) {
                progressBar.setProgress(progress);
            }
        });

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.bspatch).setOnClickListener(this);

        $.toast("---------------------------------");

    }


    @Override protected void onDestroy() {
        super.onDestroy();
        Updater.instance().destory();
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                Updater.instance().start(this, downUrl);
                break;
            case R.id.pause:
                Updater.instance().pause();
                break;
            case R.id.cancel:
                Updater.instance().cancel();
            case R.id.bspatch:
                bspatch();
                break;
        }
    }


    private void bspatch() {
        final File destApk = new File(Environment.getExternalStorageDirectory(), "new.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(), "my.patch");

        //一定要检查文件都存在

        BsPatch.patch($.getSource(),
            destApk.getAbsolutePath(),
            patch.getAbsolutePath());

        if (destApk.exists()) {
            $.installApk(destApk);
        }
    }
}
