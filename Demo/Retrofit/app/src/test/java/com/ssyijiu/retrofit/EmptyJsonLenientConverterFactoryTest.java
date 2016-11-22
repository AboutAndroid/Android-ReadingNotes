package com.ssyijiu.retrofit;

import com.ssyijiu.retrofit.retrofit2.api.API;
import com.ssyijiu.retrofit.retrofit2.converter.EmptyJsonLenientConverterFactory;
import com.ssyijiu.retrofit.retrofit2.converter.YLApiError;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class EmptyJsonLenientConverterFactoryTest {
    private Retrofit mRetrofit;
    private EmptyJsonLenientConverterFactory mFactory;

    @Before
    public void setUp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API.MOVIE_API_BASEURL)
                .build();
        mFactory = new EmptyJsonLenientConverterFactory(
                GsonConverterFactory.create());
    }

    public static ResponseBody stringBody(String body) {
        return ResponseBody.create(
                MediaType.parse("application/json"), body);
    }

    @Test
    public void convertNormalJson()
            throws IOException {

        // 验证正常 JSON 能正确解析

        String normalJson = "{\"request\":\"req\","
                + "\"errcode\":123,"
                + "\"errmsg\":\"qw\"}";
        Converter<ResponseBody, ?> converter =
                mFactory.responseBodyConverter(YLApiError.class,
                        null, mRetrofit);
        Object response = converter.convert(stringBody(normalJson));
        assertTrue(response instanceof YLApiError);
        YLApiError apiError = (YLApiError) response;
        assertEquals("123", apiError.errcode);
    }

    @Test(expected = EOFException.class)
    public void gsonConverterFailOnEmptyJson()
            throws IOException {
        // 验证 GsonConverter 无法处理空字符串
        String emptyJson = "";
        Converter<ResponseBody, ?> converter =
                GsonConverterFactory.create().responseBodyConverter(
                        YLApiError.class, null, mRetrofit);
        converter.convert(stringBody(emptyJson));
    }

    @Test
    public void convertEmptyJson()
            throws IOException {
        // 验证我们的 converter 可以处理空字符串

        String emptyJson = "";
        Converter<ResponseBody, ?> converter =
                mFactory.responseBodyConverter(YLApiError.class,
                        null, mRetrofit);
        Object response = converter.convert(stringBody(emptyJson));
        assertNull(response);
    }
}
