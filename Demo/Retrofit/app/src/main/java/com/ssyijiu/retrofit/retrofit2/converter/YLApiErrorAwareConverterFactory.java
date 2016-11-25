package com.ssyijiu.retrofit.retrofit2.converter;

import com.google.gson.JsonSyntaxException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ssyijiu on 2016/11/25.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * API Error 处理，看不懂。
 * http://blog.piasy.com/2016/09/04/RESTful-Android-Network-Solution-2/
 */

public class YLApiErrorAwareConverterFactory extends Converter.Factory {

    private final Converter.Factory mDelegateFactory;           // 1

    public YLApiErrorAwareConverterFactory(
            Converter.Factory delegateFactory) {
        mDelegateFactory = delegateFactory;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return mDelegateFactory
                .requestBodyConverter(type, parameterAnnotations,
                        methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        final Converter<ResponseBody, ?> apiErrorConverter =    // 2
                mDelegateFactory.responseBodyConverter(YLApiError.class,
                        annotations, retrofit);
        final Converter<ResponseBody, ?> delegateConverter =
                mDelegateFactory.responseBodyConverter(type,
                        annotations, retrofit);
        return value -> {
            // read them all, then create a new ResponseBody for ApiError
            // because the response body is wrapped,
            // we can't clone the ResponseBody correctly
            MediaType mediaType = value.contentType();
            String stringBody = value.string();                   // 3
            try {
                Object apiError = apiErrorConverter
                        .convert(ResponseBody.create(mediaType, stringBody));
                if (apiError instanceof YLApiError
                        && ((YLApiError) apiError).isApiError()) {
                    throw (YLApiError) apiError;                  // 4
                }
            } catch (JsonSyntaxException notApiError) {
            }
            // then create a new ResponseBody for normal body
            return delegateConverter
                    .convert(ResponseBody.create(mediaType, stringBody));
        };
    }
}
