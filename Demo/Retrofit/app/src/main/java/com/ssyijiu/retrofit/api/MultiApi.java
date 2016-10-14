package com.ssyijiu.retrofit.api;

import com.ssyijiu.retrofit.bean.MovieParam;
import com.ssyijiu.retrofit.bean.MultiResp;

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

public interface MultiApi {

    /**
     * https://app.dbjb.com/api/banklist/getBusinessRules?param=b1.unionloginurl.limit;
     */
    String baseUrl = "https://app.dbjb.com/api/banklist/";

    /* Post */
    @POST("getBusinessRules")
    Call<MultiResp> getMessage(@Body MovieParam movieParam); // 请求体 {"count":3,"start":5}


    /* FormUrlEncoded Field */
    @FormUrlEncoded
    @POST("getBusinessRules")
    Call<MultiResp> getMessage(@Field("param") String param);

    /* FormUrlEncoded FieldMap */
    @FormUrlEncoded
    @POST("getBusinessRules")
    Call<MultiResp> getMessage(@FieldMap Map<String,String> map);/* FormUrlEncoded FieldMap */


    /* Headers */
    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("getBusinessRules")
    Call<MultiResp> useHeaders(@FieldMap Map<String,String> map);

    /* Header */
    @FormUrlEncoded
    @POST("getBusinessRules")
    Call<MultiResp> useHeader(@Header("Content-type") String contentType);


}
