package com.ssyijiu.retrofit.bean.resp;

/**
 * Created by ssyijiu on 2016/11/18.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class GoldPriceResp extends BaseResp<GoldPriceResp.ResponseParamsBean> {

    public static class ResponseParamsBean {
        public SysTimeBean sysTime;
        public String goldRate;

        public static class SysTimeBean {
            public String time;
            public String minutes;
            public String seconds;
            public String hours;
            public String month;
            public String year;
            public String timezoneOffset;
            public String day;
            public String date;
        }
    }
}
