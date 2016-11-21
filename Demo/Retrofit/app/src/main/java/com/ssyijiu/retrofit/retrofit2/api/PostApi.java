package com.ssyijiu.retrofit.retrofit2.api;

import com.ssyijiu.retrofit.bean.MovieParam;
import com.ssyijiu.retrofit.bean.PostResp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ssyijiu on 2016/10/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface PostApi {

    /**
     * https://app.dbjb.com/api/banklist/getBusinessRules?param=b1.unionloginurl.limit;
     */

    /* Post */
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> getMessage(@Body MovieParam movieParam); // 请求体 {"count":3,"start":5}


    /* FormUrlEncoded Field */
    @FormUrlEncoded
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> getMessage(@Field("param") String param);

    /* FormUrlEncoded FieldMap */
    @FormUrlEncoded
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> getMessage(@FieldMap Map<String,String> map);/* FormUrlEncoded FieldMap */


    /* Headers */
    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> useHeaders(@FieldMap Map<String,String> map);


    // 同一个请求的同一个请求头在不同地方的设置不会被覆盖，而是会被全部添加进请求头中.
    // 如果要给每个请求都添加同样的Header时，可以使用okHttp的 Interceptor .

    /* Headers */  // 添加多个 Header
    @Headers({
            "Content-type:application/x-www-form-urlencoded;charset=UTF-8",
            "Accept: application/vnd.github.v3.full+json"
    })
    @FormUrlEncoded
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> useHeaders();

    /* Header */
    @FormUrlEncoded
    @POST("api/banklist/getBusinessRules")
    Call<PostResp> useHeader(@Header("Content-type") String contentType);




}
