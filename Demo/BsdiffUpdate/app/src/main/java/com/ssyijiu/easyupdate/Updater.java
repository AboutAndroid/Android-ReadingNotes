package com.ssyijiu.easyupdate;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.ssyijiu.easyupdate.callback.OnProgressCallback;
import com.ssyijiu.easyupdate.http.DownloadService;
import com.ssyijiu.easyupdate.tools.$;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by ssyijiu on 2017/6/2.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class Updater {

    private DownloadService downService;
    private DownServiceConn downServiceConn;
    private Context context;
    private boolean bind;
    private static Application sApp;

    private OnProgressCallback progressCallback;


    private Updater() {
    }


    private static final Updater instance = new Updater();


    public static Updater instance() {
        return instance;
    }


    public static void init(Application app) {
        sApp = app;
    }


    public static Context getContext() {
        return sApp;
    }


    public void start(Context context, String downUrl) {
        if ($.isSDCardAvailable()) {
            start(context, downUrl, $.getSDCardPath());
        } else {
            $.toast("SDCard is not available");
        }
    }


    public void start(Context context, String downUrl, String savePath) {

        this.context = context;
        downServiceConn = new DownServiceConn();

        // 开启服务
        final Intent intent = DownloadService.newIntent(downUrl, savePath);
        DownloadService.start(intent);
        context.bindService(intent, downServiceConn, BIND_AUTO_CREATE);
    }


    public void pause() {
        if (isBind()) {
            downService.pause();
        }
    }


    public void cancel() {
        if (isBind()) {
            downService.cancel();
        }
    }


    public void setProgressCallback(OnProgressCallback callback) {
        this.progressCallback = callback;
    }


    public void destory() {
        if (downServiceConn != null && context != null && isBind()) {
            context.unbindService(downServiceConn);
        }
    }


    private class DownServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bind = true;
            downService = ((DownloadService.DownBinderImpl) service).getService();
            // 服务绑定后，开始下载
            downService.start();
            if (progressCallback != null) {
                downService.setOnProgressCallback(progressCallback);
            }
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            bind = false;
        }
    }


    public boolean isBind() {
        return bind && downService != null;
    }
}
