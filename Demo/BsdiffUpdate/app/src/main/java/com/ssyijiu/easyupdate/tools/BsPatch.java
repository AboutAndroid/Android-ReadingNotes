package com.ssyijiu.easyupdate.tools;

/**
 * Created by ssyijiu on 2017/6/6.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class BsPatch {

    static {
        System.loadLibrary("bspatch");
    }


    /**
     * native方法 使用路径为 oldApkPath 的 apk 与路径为 patchPath 的补丁包，合成新的 apk，并存储于 newApkPath
     *
     * @param oldApkPath 示例: /sdcard/old.apk
     * @param newApkPath 示例: /sdcard/new.apk
     * @param patchPath 示例: /sdcard/xx.patch
     */
    public static native int patch(String oldApkPath, String newApkPath, String patchPath);
}
