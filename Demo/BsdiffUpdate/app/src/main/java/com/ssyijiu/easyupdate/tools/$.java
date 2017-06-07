package com.ssyijiu.easyupdate.tools;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;
import com.ssyijiu.easyupdate.BuildConfig;
import com.ssyijiu.easyupdate.Updater;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ssyijiu on 2016/9/29.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class $ {

    private $() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("$ cannot be instantiated !");
    }

    ////////////////////// App //////////////////////////


    public static void installApk(final File file) {

        if (file == null || !file.exists() || !file.isFile()) return;
        if (!checkApk(file.getAbsolutePath())) return;

        Context context = Updater.getContext();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        context.startActivity(intent);

        // 60 秒后删除
        int delayMillis = 1000 * 60;
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                file.delete();
            }
        }, delayMillis);
    }


    /**
     * 检查apk文件是否有效(是正确下载,没有损坏的)
     */
    public static boolean checkApk(String apkFilePath) {
        boolean result;
        try {
            PackageManager packageManager = Updater.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkFilePath,
                PackageManager.GET_ACTIVITIES);
            result = packageInfo != null;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }


    public static String getSource() {
        ApplicationInfo applicationInfo = Updater.getContext().getApplicationInfo();
        return applicationInfo.sourceDir;
    }

    /////////////////// MD5 //////////////////////

    private final static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
        'b', 'c', 'd', 'e', 'f' };


    /**
     * MD5加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String md5(String data) {
        return md5(data.getBytes());
    }


    /**
     * MD5加密
     *
     * @param data 明文数组
     * @return 密文
     */
    public static String md5(byte[] data) {
        byte[] digest;
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md.update(data);

            digest = md.digest();

        } catch (NoSuchAlgorithmException neverHappened) {
            throw new RuntimeException(neverHappened);
        }
        // 把密文转换成十六进制的字符串形式
        return bytes2Hex(digest);
    }


    /**
     * 一个byte转为2个hex字符
     */
    private static String bytes2Hex(byte[] src) {
        char[] res = new char[src.length * 2];
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }
        return new String(res);
    }

    ////////////////// SDCard //////////////////////


    /**
     * 判断SDCard是否可用
     *
     * @return true 可用，false 不可用
     */
    public static boolean isSDCardAvailable() {
        String state = Environment.getExternalStorageState();
        return state != null && state.equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取SD卡路径
     *
     * @return sdcard path
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }


    /**
     * 获取SD卡的可用容量 单位byte
     *
     * @return the byte sdcard can use
     */
    public static long getUsableSize() {
        if (isSDCardAvailable()) {
            File file = new File(getSDCardPath());
            return file.getUsableSpace();
        }
        return 0;
    }


    /////////////////// Toast ////////////////////
    private static final Toast toast = Toast.makeText(Updater.getContext(), "", Toast.LENGTH_SHORT);


    public static void toast(int resId) {
        String msg = Updater.getContext().getString(resId);
        toast(msg);
    }


    public static void toast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        toast.setText(msg);
        toast.show();
    }


    public static boolean checkPermission() {

        String permission = "android.permission.READ_EXTERNAL_STORAGE";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(Updater.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED;
        } else {
            PackageManager pm = Updater.getContext().getPackageManager();
            return pm.checkPermission(permission, Updater.getContext().getPackageName()) ==
                PackageManager.PERMISSION_GRANTED;
        }
    }
}
