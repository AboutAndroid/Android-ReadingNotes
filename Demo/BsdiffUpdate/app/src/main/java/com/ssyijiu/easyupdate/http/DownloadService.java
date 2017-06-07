package com.ssyijiu.easyupdate.http;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.ssyijiu.easyupdate.R;
import com.ssyijiu.easyupdate.Updater;
import com.ssyijiu.easyupdate.callback.DownloadCallback;
import com.ssyijiu.easyupdate.callback.OnProgressCallback;
import com.ssyijiu.easyupdate.tools.$;
import com.ssyijiu.easyupdate.tools.MLog;
import com.ssyijiu.easyupdate.tools.NotificationUtil;
import java.io.File;

/**
 * Created by ssyijiu on 2017/5/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class DownloadService extends Service {

    private final static String EXTRA_DOWNPATH = "extra_downpath";
    private final static String EXTRA_SAVEPATH = "extra_savepath";
    private String downUrl;
    private String savePath;
    private File saveFile;
    private Notification notificationProgress;
    private NotificationCompat.Builder notificationProgressBuilder;
    private boolean isDownloading = false;
    private OnProgressCallback progressCallback;


    @Override public void onCreate() {
        super.onCreate();
        MLog.i("Service onCreate");

        // 初始化通知
        notificationProgress = NotificationUtil.easyNotification(
            DownloadService.this, R.mipmap.ic_launcher,
            "bsdiffupdate", "准备下载", false);

        notificationProgressBuilder = new NotificationCompat.Builder(this);
    }


    @Nullable @Override public IBinder onBind(Intent intent) {
        MLog.i("Service onBind");
        return new DownBinderImpl();
    }


    @Override public void onDestroy() {
        super.onDestroy();
        MLog.i("Service onDestroy");
    }


    @Override public boolean onUnbind(Intent intent) {
        MLog.i("Service onUnbind");
        return super.onUnbind(intent);
    }


    // 重复开启服务不会走 onCreate，会走 onStartCommand
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        MLog.i("Service onStartCommand");

        // 将服务设置为前台服务
        // 注意：这个 ID 不能为 0，为 0 的话这句代码无效
        // 因为后面通知会不断更新，注意和 NOTIFICATION_ID 一样，要不就是发出两个通知了
        startForeground(NotificationUtil.NOTIFICATION_ID, notificationProgress);

        if (intent != null) {
            downUrl = intent.getStringExtra(EXTRA_DOWNPATH);
            savePath = intent.getStringExtra(EXTRA_SAVEPATH);

            if (!TextUtils.isEmpty(savePath)) {
                saveFile = new File(savePath, getFileName(downUrl));
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    public static Intent newIntent(final String downUrl, final String savePath) {
        Intent intent = new Intent(Updater.getContext(), DownloadService.class);
        intent.putExtra(EXTRA_DOWNPATH, downUrl);
        intent.putExtra(EXTRA_SAVEPATH, savePath);
        return intent;
    }


    public static void start(Intent intent) {
        Updater.getContext().startService(intent);
    }


    public class DownBinderImpl extends Binder {

        public DownloadService getService() {
            return DownloadService.this;
        }  // 这样 binder 就可以调用服务中的公共方法

    }


    /**
     * 开始下载
     */
    public void start() {

        // 已经下载好直接安装
        // if ($.checkApk(saveFile.getAbsolutePath())) {
        //     $.installApk(saveFile);
        //     return;
        // }

        if (isDownloading) {
            $.toast("已在下载中");
            return;
        }

        isDownloading = true;
        // 开始下载
        HttpRequest.download(downUrl, savePath, new DownloadCallback() {

            @Override public void downloadSuccess(File file) {
                isDownloading = false;

                // 停止服务
                // bindService 后，只有所有客户端 unbindService，stopSelf 才会真正结束服务
                DownloadService.this.stopSelf();

                // 增量升级
                if (isPatch(file)) {

                }

                $.installApk(file);               // 安装 apk

                // NotificationUtil.cancel();     // 前台通知使用 cancel 无法移除
                stopForeground(true);             // 移除前台通知
            }


            @Override
            public void downloadProgress(long currentProgress, long totalProgress) {

                int progress = (int) (currentProgress * 100.0 / totalProgress + 0.5);
                if (progressCallback != null) {
                    progressCallback.onProgress(progress);
                }

                notificationProgress = NotificationUtil.downloadingNotification(
                    notificationProgressBuilder,
                    (int) currentProgress, (int) totalProgress, R.mipmap.ic_launcher,
                    "bsdiffupdate",
                    progress + "%", false);
                NotificationUtil.show(notificationProgress);
            }


            @Override public void downloadFailure(String errorMsg) {
                isDownloading = false;

                PendingIntent pendingIntent = createPendingIntent();

                Notification notification = NotificationUtil.downloadFailureNotification(
                    DownloadService.this,
                    pendingIntent,
                    R.mipmap.ic_launcher,
                    "bsdiffupdate",
                    "下载失败，点击重试", true);

                NotificationUtil.show(notification);
                $.toast(errorMsg);
            }
        });
    }


    private boolean isPatch(File file) {
        return file.getAbsolutePath().endsWith(".patch");
    }


    /**
     * 暂停下载
     */
    public void pause() {
        isDownloading = false;
        HttpRequest.interruptDownload();
    }


    /**
     * 取消下载
     */
    public void cancel() {
        isDownloading = false;
        HttpRequest.cancelDownload();
        DownloadService.this.stopSelf();
        stopForeground(true);
    }


    /**
     * 下载进度监听
     */
    public void setOnProgressCallback(OnProgressCallback callback) {
        this.progressCallback = callback;
    }


    private PendingIntent createPendingIntent() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), DownloadService.class);
        return PendingIntent.getService(DownloadService.this, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }
}
