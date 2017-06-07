package com.ssyijiu.easyupdate.callback;

import java.io.File;

/**
 * Created by ssyijiu on 2017/5/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface DownloadCallback {


    /**
     * 下载成功
     *
     * @param file 下载的文件
     */
    void downloadSuccess(File file);

    /**
     * 实时下载进度
     *
     * @param currentProgress 当前下载进度(单位:字节)
     * @param totalProgress   文件总大小(单位:字节)
     */
    void downloadProgress(long currentProgress, long totalProgress);

    /**
     * 下载失败
     *
     * @param errorMsg 失败信息(捕获的异常信息)
     */
    void downloadFailure(String errorMsg);
}
