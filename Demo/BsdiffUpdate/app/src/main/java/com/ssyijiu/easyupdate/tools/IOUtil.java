package com.ssyijiu.easyupdate.tools;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by ssyijiu on 2017/5/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class IOUtil {

    private IOUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("IOUtil cannot be instantiated !");
    }


    /**
     * 关闭流
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }


    /**
     * 断开 HttpURLConnection
     */
    public static void close(HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }


    /**
     * 流转字节数组, 异常返回 null
     */
    public static byte[] stream2Bytes(InputStream in) {
        if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream(in);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len;
        byte[] buf = new byte[1024];

        try {
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(in);
            close(out);
        }
        return null;
    }


    /**
     * 流转字符串, 异常返回 ""
     */
    public static String stream2String(InputStream in) throws IOException {
        return stream2String(in, "UTF-8");
    }


    /**
     * 流转字符串, 异常返回 ""
     */
    public static String stream2String(InputStream in, String charset) {
        if (TextUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        try {
            byte[] bytes = stream2Bytes(in);
            if (bytes != null) {
                return new String(bytes, charset);
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 将 map 中的参数拼接成字符串
     */
    public static String map2String(Map<String, String> params) {
        StringBuilder sbr = new StringBuilder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                sbr.append(key).append("=").append(value).append("&");
            }
            sbr.delete(sbr.length() - 1, sbr.length());
        }

        return sbr.toString();
    }
}
