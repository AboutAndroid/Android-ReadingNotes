package com.stay4it.sample.utils;

import android.database.Cursor;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by ssyijiu on 2016/9/16.
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
     * 关闭流 Cursor
     */
    public static void close(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
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
            if(bytes != null) {
                return new String(bytes, charset);
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
