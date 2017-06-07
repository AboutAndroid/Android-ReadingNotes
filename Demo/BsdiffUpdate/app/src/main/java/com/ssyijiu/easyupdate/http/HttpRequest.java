package com.ssyijiu.easyupdate.http;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.ssyijiu.easyupdate.Updater;
import com.ssyijiu.easyupdate.callback.DownFailureRun;
import com.ssyijiu.easyupdate.callback.DownloadCallback;
import com.ssyijiu.easyupdate.callback.RequestCallback;
import com.ssyijiu.easyupdate.callback.RequestFailureRun;
import com.ssyijiu.easyupdate.tools.$;
import com.ssyijiu.easyupdate.tools.IOUtil;
import com.ssyijiu.easyupdate.tools.MLog;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by ssyijiu on 2017/5/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class HttpRequest {

    private final static int CONNECTION_TIME_OUT = 10000;   // 连接超时时间
    private final static int READ_TIME_OUT = 10000;         // 读取超时时间
    private final static int THREAD_COUNT = 3;              // 默认线程数目
    private final static long PUBLISH_PROGRESS_TIME = 100;  // 发送进度的时间间隔

    private static boolean interruptDownload = false;       // 是否中断了下载
    private static boolean cancelDownload = false;          // 是否取消了下载

    private static int threadRunning = 0;                   // 运行的线程数目
    private static boolean allThreadStart = false;          // 是否所有线程都已经开启
    private static long currentProgress = 0;                // 当前下载进度
    private static long totalProgress;                      // 服务器文件大小
    private static Handler handler = new Handler();

    private static File saveFile;
    private static String downUrl;

    private static final String ERROR_SDCARD = "SDCard不可用，下载失败";
    private static final String ERROR_NO_SPACE = "没有足够的存储空间，下载失败";
    private static final String ERROR_FILE = "创建文件失败，请检查存储空间权限";


    public static void get(final String url, final RequestCallback callback) {
        get(url, null, callback);
    }


    // http://gank.io/api/data/Android/10/1
    public static void get(final String url, final Map<String, String> params, final RequestCallback callback) {
        new Thread(
            new Runnable() {
                @Override public void run() {

                    HttpURLConnection connection = null;
                    InputStream in = null;

                    String paramsStr = IOUtil.map2String(params);
                    String httpUrl = parseGetUrl(paramsStr, url);

                    try {
                        URL url = new URL(httpUrl);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(READ_TIME_OUT);
                        connection.setConnectTimeout(CONNECTION_TIME_OUT);

                        // 若不 ok,则数据请求失败
                        if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                            final String errorMsg = connection.getResponseCode() + ":" +
                                connection.getResponseMessage();
                            handler.post(new RequestFailureRun(callback, errorMsg));
                            return;
                        }

                        in = connection.getInputStream();
                        final String result = IOUtil.stream2String(in);

                        // 网络请求成功，回调到主线程
                        handler.post(new Runnable() {
                            @Override public void run() {
                                callback.onSuccess(result);
                            }
                        });

                    } catch (final Exception e) {
                        e.printStackTrace();
                        handler.post(new RequestFailureRun(callback, e.getMessage()));
                    } finally {
                        IOUtil.close(in);
                        IOUtil.close(connection);
                    }
                }


                private String parseGetUrl(String paramsStr, String url) {
                    if (TextUtils.isEmpty(paramsStr)) {
                        return url;
                    } else {
                        return url + "?" + paramsStr;
                    }
                }
            }
        ).start();

    }


    // https://app.dbjb.com/api/banklist/getBusinessRules?param=b1.unionloginurl.limit;
    public static void post(final String url, final RequestCallback callback) {
        post(url, null, callback);
    }


    public static void post(final String url, final Map<String, String> params, final RequestCallback callback) {
        new Thread(new Runnable() {
            @Override public void run() {

                InputStream in = null;
                HttpURLConnection connection = null;
                DataOutputStream dos = null;

                // 拼接请求参数
                String paramsStr = IOUtil.map2String(params);

                URL httpUrl;
                try {
                    httpUrl = new URL(url);

                    connection = (HttpURLConnection) httpUrl.openConnection();
                    // 设置请求头header
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(READ_TIME_OUT);
                    connection.setConnectTimeout(CONNECTION_TIME_OUT);
                    connection.setDoOutput(true);  // post 请求设置允许写数据
                    // 若参数不为空,则写入参数
                    if (!TextUtils.isEmpty(paramsStr)) {
                        dos = new DataOutputStream(connection.getOutputStream());
                        dos.write(paramsStr.getBytes("UTF-8"));   // post 请求写入参数
                        dos.flush();
                    }

                    // 若不 ok,则数据请求失败
                    if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                        final String errorMsg = connection.getResponseCode() + ":" +
                            connection.getResponseMessage();

                        handler.post(new RequestFailureRun(callback, errorMsg));
                        return;
                    }

                    // 获取内容
                    in = connection.getInputStream();
                    final String result = IOUtil.stream2String(in);

                    // 网络请求成功，回调到主线程
                    handler.post(new Runnable() {
                        @Override public void run() {
                            callback.onSuccess(result);
                        }
                    });

                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new RequestFailureRun(callback, e.getMessage()));
                } finally {
                    IOUtil.close(dos);
                    IOUtil.close(in);
                    IOUtil.close(connection);
                }

            }
        }).start();
    }


    public static void download(final String downUrl, final DownloadCallback callback) {
        if ($.isSDCardAvailable()) {
            download(downUrl, $.getSDCardPath(), callback);
        } else {
            handler.post(new DownFailureRun(callback, ERROR_SDCARD));
        }
    }


    public static void download(final String downUrl, final String savePath, final DownloadCallback callback) {

        HttpRequest.downUrl = downUrl;

        new Thread(new Runnable() {
            @Override public void run() {
                HttpURLConnection connection = null;
                try {
                    URL httpUrl = new URL(downUrl);

                    // 处理 https
                    if (downUrl.toLowerCase().startsWith("https://")) {
                        TrustAllCertificates.install();
                    }

                    connection = (HttpURLConnection) httpUrl.openConnection();
                    connection.setUseCaches(false);
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(CONNECTION_TIME_OUT);
                    connection.setReadTimeout(READ_TIME_OUT);

                    if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                        final String errorMsg = connection.getResponseCode() + ":" +
                            connection.getResponseMessage();

                        handler.post(new DownFailureRun(callback, errorMsg));
                        return;
                    }

                    //1. 获取服务器文件长度
                    totalProgress = connection.getContentLength();
                    MLog.i("服务器文件大小：" + totalProgress);

                    long usableSpace = new File(savePath).getUsableSpace();
                    MLog.i("存储空间大小：" + usableSpace);

                    // 存储空间不足
                    if (usableSpace < totalProgress) {
                        handler.post(new DownFailureRun(callback, ERROR_NO_SPACE));
                        return;
                    }

                    // 检查运行时权限
                    if (!$.checkPermission()) {
                        handler.post(new DownFailureRun(callback, ERROR_FILE));
                        return;
                    }

                    // 创建并检查要保存的文件
                    saveFile = new File(savePath, getFileName(downUrl));
                    File parentFile = saveFile.getParentFile();
                    if (parentFile.exists() && !parentFile.isDirectory()) {
                        parentFile.delete();
                    }
                    if (!parentFile.exists()) {
                        if (!parentFile.mkdirs()) {
                            handler.post(new DownFailureRun(callback, ERROR_FILE));
                            return;
                        }
                    }

                    // 开始下载了
                    RandomAccessFile raf = new RandomAccessFile(saveFile, "rwd");
                    raf.setLength(totalProgress);
                    raf.close();

                    //3. 确定有多少线程下载，默认 3
                    //4. 确定每个线程下载的大小及开始位置和结束位置
                    long blockSize = totalProgress / THREAD_COUNT;
                    currentProgress = 0;    // 即将开始下载，进度清零
                    for (int i = 0; i < THREAD_COUNT; i++) {
                        long startIndex = i * blockSize;
                        long endIndex = (i + 1) * blockSize - 1;
                        if (endIndex == THREAD_COUNT - 1) {
                            endIndex = totalProgress - 1;
                        }
                        MLog.i("线程" + i + "：" + startIndex + "-" + endIndex);
                        new Thread(new DownloadThread(i, startIndex, endIndex, callback)).start();

                    }

                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new DownFailureRun(callback, e.getMessage()));
                } finally {
                    IOUtil.close(connection);
                }
            }
        }).start();
    }


    private static class DownloadThread implements Runnable {

        private int id;
        private long startIndex;
        private long endIndex;
        private long total;
        private DownloadCallback callback;
        private long lastTime = 0;


        public DownloadThread(int id, long startIndex, long endIndex, DownloadCallback callback) {
            super();
            interruptDownload = false;    // 重置下载标志
            cancelDownload = false;
            this.id = id;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.callback = callback;
        }


        @Override
        public void run() {
            URL url;
            InputStream in = null;
            HttpURLConnection connection = null;
            try {
                synchronized (HttpRequest.class) {
                    threadRunning++;
                    MLog.i("线程" + id + "启动完成");
                    if (threadRunning == THREAD_COUNT) {
                        allThreadStart = true;
                        MLog.i(THREAD_COUNT + "个线程全部启动完成");
                    }
                }

                //断点续传 2. 读取进度文件, 重新设置开始位置
                File file = getProgressFile(id);
                if (file.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    try {
                        long lastTotal = Integer.parseInt(br.readLine());
                        startIndex += lastTotal;
                        total = lastTotal;
                        currentProgress += total;
                        MLog.i(("开始断点续传，线程" + id + "的 startIndex = " + startIndex));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        handler.post(new DownFailureRun(callback, e.getMessage()));
                    } finally {
                        IOUtil.close(br);
                    }
                }

                url = new URL(downUrl);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(CONNECTION_TIME_OUT);
                connection.setReadTimeout(READ_TIME_OUT);

                connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                MLog.i(id + ":bytes=" + startIndex + "-" + endIndex);

                if (HttpURLConnection.HTTP_PARTIAL == connection.getResponseCode()) {

                    in = connection.getInputStream();

                    // 开启 gzip 压缩
                    String contentEndcoding = connection.getHeaderField("Content-Encoding");
                    if (contentEndcoding != null && contentEndcoding.equalsIgnoreCase("gzip")) {
                        in = new GZIPInputStream(in);
                    }

                    RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rwd");
                    randomAccessFile.seek(startIndex);

                    int len;
                    byte[] buf = new byte[1024 * 1024];

                    while (!interruptDownload && (len = in.read(buf)) != -1) {
                        randomAccessFile.write(buf, 0, len);
                        total += len;
                        // MLog.i(id + "下载了" + total + "个字节");

                        //断点续传 1. 保存进度文件
                        RandomAccessFile raf = new RandomAccessFile(
                            getProgressFile(id), "rwd");
                        raf.write(String.valueOf(total).getBytes());
                        raf.close();

                        currentProgress += len;

                        publishProgress();

                    }
                    randomAccessFile.close();
                } else {
                    // Range  所请求的范围无法满足
                    // 中断的时候如果有线程下载完了，会出现这个，忽略即可
                    if (416 == connection.getResponseCode()) {
                        return;
                    }
                    // 服务器不支持 Range
                    handler.post(new DownFailureRun(callback,
                        connection.getResponseCode() + ":" + connection.getResponseMessage()));
                }

            } catch (IOException e) {
                e.printStackTrace();
                handler.post(new DownFailureRun(callback, e.getMessage()));
            } finally {

                //6. 此时一个线程下载完成
                synchronized (HttpRequest.class) {
                    threadRunning--;
                    if (threadRunning == 0 && allThreadStart) {
                        MLog.i("文件下载完成！");

                        // 未中断，下载完成
                        if (!interruptDownload) {
                            handler.post(new Runnable() {
                                @Override public void run() {
                                    callback.downloadSuccess(saveFile);
                                }
                            });
                            deleteProgressFile();
                        } else if (cancelDownload) {  // 取消下载
                            deleteProgressFile();
                        }
                    }
                }

                IOUtil.close(in);
                IOUtil.close(connection);
            }

        }


        private void publishProgress() {
            long currentTime = SystemClock.currentThreadTimeMillis();

            // 没有必要实时发送进度
            // 对 Handler.post 进行限流，节约资源
            if (currentTime - lastTime >= PUBLISH_PROGRESS_TIME) {
                handler.post(new Runnable() {
                    @Override public void run() {
                        callback.downloadProgress(currentProgress, totalProgress);
                    }
                });
                lastTime = currentTime;
            }

        }

    }


    public static void interruptDownload() {
        interruptDownload = true;
    }


    public static void cancelDownload() {
        cancelDownload = true;
        // 已经中断，直接删除进度文件
        if (interruptDownload) {
            deleteProgressFile();
        } else {  // 未中断，中断后通过 cancelDownload 标志删除进度文件
            interruptDownload();
        }

    }


    private static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }


    private static void deleteProgressFile() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            File f = getProgressFile(i);
            if (f.delete()) {
                MLog.i("线程" + i + "临时文件删除成功");
            } else {
                MLog.i("线程" + i + "临时文件删除失败");
                MLog.i(f.getAbsolutePath());
            }
        }
    }


    @NonNull private static File getProgressFile(int id) {
        String fileName = "." + $.md5(downUrl + "_" + id);
        return new File(Updater.getContext().getFilesDir(), fileName);
    }
}
