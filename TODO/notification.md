http://www.jianshu.com/p/22e27a639787

http://reezy.me/2016-12-28/android-notification/

https://developer.android.google.cn/training/notify-user/display-progress.html

https://mp.weixin.qq.com/s/1ai6Kyg0lURQzjk5QPLrxw

https://blog.dreamtobe.cn/2016/01/09/notification_best_practise/



```java
    /**
     * 展示下载成功通知
     *
     * @param context 上下文
     * @param file 下载的apk文件
     * @param notificationIconResId 通知图标资源id
     * @param notificationTitle 通知标题
     * @param notificationContent 通知内容
     * @param isCanClear 通知是否可被清除
     */
    public static Notification downloadSuccessNotification(Context context, File file, int notificationIconResId, String notificationTitle, String notificationContent, boolean isCanClear) {

        // install Intent，下载完成后点击安装
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri;
        // Android 7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".bsdiffupdate",
                file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)                     // 点击自动消失
            .setShowWhen(true)                          // 显示时间
            .setSmallIcon(notificationIconResId)        // 小图标
            .setContentTitle(notificationTitle)         // 标题
            .setContentText(notificationContent)        // 内容
            .setOnlyAlertOnce(true)                     // 只显示一次
            .setDefaults(Notification.DEFAULT_SOUND);   // 默认声音

        PendingIntent pendingIntent = getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT);         // 延迟意图
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();    // 获取 Notification

        notification.flags = isCanClear
                             ? notification.flags
                             : notification.flags | Notification.FLAG_NO_CLEAR;
        return notification;
    }
```

