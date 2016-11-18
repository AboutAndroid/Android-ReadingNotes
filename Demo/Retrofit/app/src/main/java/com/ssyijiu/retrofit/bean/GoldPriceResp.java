package com.ssyijiu.retrofit.bean;

/**
 * Created by ssyijiu on 2016/11/18.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class GoldPriceResp {

    /**
     * rntCodeValue : 1
     * responseParams : {"sysTime":{"time":1479435585677,"minutes":19,"seconds":45,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":5,"date":18},"goldRate":268.31}
     * rntMsg :
     * errorMsg : 错误码:1,错误信息:
     * rntCode : OK
     */

    private String rntCodeValue;
    /**
     * sysTime : {"time":1479435585677,"minutes":19,"seconds":45,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":5,"date":18}
     * goldRate : 268.31
     */

    private ResponseParamsBean responseParams;
    private String rntMsg;
    private String errorMsg;
    private String rntCode;

    public String getRntCodeValue() {
        return rntCodeValue;
    }

    public void setRntCodeValue(String rntCodeValue) {
        this.rntCodeValue = rntCodeValue;
    }

    public ResponseParamsBean getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(ResponseParamsBean responseParams) {
        this.responseParams = responseParams;
    }

    public String getRntMsg() {
        return rntMsg;
    }

    public void setRntMsg(String rntMsg) {
        this.rntMsg = rntMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRntCode() {
        return rntCode;
    }

    public void setRntCode(String rntCode) {
        this.rntCode = rntCode;
    }

    public static class ResponseParamsBean {
        /**
         * time : 1479435585677
         * minutes : 19
         * seconds : 45
         * hours : 10
         * month : 10
         * year : 116
         * timezoneOffset : -480
         * day : 5
         * date : 18
         */

        private SysTimeBean sysTime;
        private String goldRate;

        public SysTimeBean getSysTime() {
            return sysTime;
        }

        public void setSysTime(SysTimeBean sysTime) {
            this.sysTime = sysTime;
        }

        public String getGoldRate() {
            return goldRate;
        }

        public void setGoldRate(String goldRate) {
            this.goldRate = goldRate;
        }

        public static class SysTimeBean {
            private String time;
            private String minutes;
            private String seconds;
            private String hours;
            private String month;
            private String year;
            private String timezoneOffset;
            private String day;
            private String date;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getMinutes() {
                return minutes;
            }

            public void setMinutes(String minutes) {
                this.minutes = minutes;
            }

            public String getSeconds() {
                return seconds;
            }

            public void setSeconds(String seconds) {
                this.seconds = seconds;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(String timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
