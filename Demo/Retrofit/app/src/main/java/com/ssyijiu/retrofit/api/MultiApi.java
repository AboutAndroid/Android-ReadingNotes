package com.ssyijiu.retrofit.api;

import com.ssyijiu.retrofit.bean.FinancingListResp;
import com.ssyijiu.retrofit.bean.GoldPriceResp;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MultiApi {

    @POST("api/inrate/getGoldRateAndSysTime")
    Call<GoldPriceResp> getGoldPrice();


    @FormUrlEncoded
    @POST("api/banklist/queryLcDetailForUseNew")
    Call<FinancingListResp> getFinancingList(
            @FieldMap Map<String,String> map);
}
