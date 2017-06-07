package com.ssyijiu.easyupdate.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.ssyijiu.easyupdate.Updater;

/**
 * Created by ssyijiu on 2017/5/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class NotificationUtil {

    public static int NOTIFICATION_ID = 77;  // 喜欢这个数字而已 。。


    private NotificationUtil() {
    }


    public static Notification downloadingNotification(NotificationCompat.Builder builder, int currentProgress, int totalProgress, int notificationIconResId, String notificationTitle, String notificationContent, boolean isCanClear) {

        builder.setAutoCancel(false)                    // 点击自动消失
            .setShowWhen(false)                         // 显示时间（注意显示进度的时候设为 true 也不会显示时间）
            .setSmallIcon(notificationIconResId)        // 小图标
            .setContentTitle(notificationTitle)         // 标题
            .setContentText(notificationContent)        // 内容
            .setProgress(totalProgress, currentProgress, false);

        Notification notification = builder.build();    // 获取 Notification

        notification.flags = isCanClear
                             ? notification.flags
                             : notification.flags | Notification.FLAG_NO_CLEAR;

        return notification;
    }


    /**
     * 展示下载失败通知
     *
     * @param context 上下文
     * @param pendingIntent 该 intent 用来重新下载应用
     * @param notificationIconResId 通知图标资源 id
     * @param notificationTitle 通知标题
     * @param notificationContent 通知内容,比如:下载失败,点击重新下载
     * @param isCanClear 通知是否可被清除
     */
    public static Notification downloadFailureNotification(Context context, PendingIntent pendingIntent, int notificationIconResId, String notificationTitle, String notificationContent, boolean isCanClear) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)                    // 点击自动消失
            .setShowWhen(true)
            .setSmallIcon(notificationIconResId)        // 小图标
            .setContentTitle(notificationTitle)         // 标题
            .setContentText(notificationContent)        // 内容
            .setOnlyAlertOnce(true)                     // 只显示一次
            .setDefaults(Notification.DEFAULT_SOUND);   // 默认声音

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();    // 获取 Notification

        notification.flags = isCanClear
                             ? notification.flags
                             : notification.flags | Notification.FLAG_NO_CLEAR;

        return notification;
    }


    public static Notification easyNotification(Context context, int notificationIconResId, String notificationTitle, String notificationContent, boolean isCanClear) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)                     // 点击自动消失
            .setShowWhen(true)                          // 显示时间
            .setSmallIcon(notificationIconResId)        // 小图标
            .setContentTitle(notificationTitle)         // 标题
            .setContentText(notificationContent)        // 内容
            .setOnlyAlertOnce(true)                     // 只提醒一次
            .setDefaults(Notification.DEFAULT_SOUND);   // 默认声音

        Notification notification = builder.build();    // 获取 Notification

        notification.flags = isCanClear
                             ? notification.flags
                             : notification.flags | Notification.FLAG_NO_CLEAR;

        return notification;
    }


    public static void show(Notification notification) {
        NotificationManager manager = (NotificationManager) Updater.getContext().getSystemService(
            Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);
    }


    public static void cancel() {
        NotificationManager manager = (NotificationManager) Updater.getContext().getSystemService(
            Context.NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);
    }
}
