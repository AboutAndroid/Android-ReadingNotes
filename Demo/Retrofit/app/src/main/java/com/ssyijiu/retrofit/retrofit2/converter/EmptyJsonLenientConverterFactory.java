package com.ssyijiu.retrofit.retrofit2.converter;

import java.io.EOFException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首先，如果一个 API 请求不需要返回数据，
 * 很可能我们的服务器也就不会返回数据（返回空的 response body），
 * 而空字符串并不是合法的 JSON，所以 Square 实现的 GsonResponseBodyConverter 会不认账，
 * 直接抛出 JSON 解析错误。
 *
 * 这个解析器装饰了 GsonConverterFactory 将空字符串直接解析为 null。
 * 并且这里 Junit 测试写的很好，值得一看。
 *
 * 作者 Blog：http://blog.piasy.com/2016/09/04/RESTful-Android-Network-Solution-2/
 */


public class EmptyJsonLenientConverterFactory extends Converter.Factory {

    private final GsonConverterFactory mGsonConverterFactory;       // 1

    public EmptyJsonLenientConverterFactory(GsonConverterFactory gsonConverterFactory) {

        mGsonConverterFactory = gsonConverterFactory;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {

        return mGsonConverterFactory.requestBodyConverter(type,     // 2
                parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegateConverter =        // 3
                mGsonConverterFactory.responseBodyConverter(type,
                        annotations, retrofit);

        return value -> {
            try {
                return delegateConverter.convert(value);            // 4
            } catch (EOFException e) {
                // just return null
                return null;                                        // 5
            }
        };
    }
}
