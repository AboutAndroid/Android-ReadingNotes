package com.ssyijiu.retrofit.bean.resp;

import java.util.List;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class FinancingListResp extends BaseResp<List<FinancingListResp.ResponseParamsBean>>{


    public static class ResponseParamsBean {
        public String balance;
        public String allEarnings;

        public TBaseLCProcuctBean tBaseLCProcuct;
        public String couponVaild;
        public String prdType;
        public int totalGoldUsed;
        public List<?> actDefinedList;


        public static class TBaseLCProcuctBean {


            public CreateTimeBean createTime;
            public String riskLevel;
            public String memo;

            public StartAdDateBean startAdDate;
            public String reserved8;


            public EndSaleDateBean endSaleDate;
            public int id;
            public double expInrateMin;
            public int startAmount;
            public String lcPrdType;
            public String accountPrdCode;
            public String timeUnit;
            public int initVirtual;
            public int totalAmount;
            public String reserved6;
            public String reserved7;
            public int regulcPrice;
            public String reserved4;
            public String reserved5;
            public String reserved2;
            public String reserved3;

            public OpenDateBean openDate;
            public String lcPrdName;
            public String reserved1;
            public int timeLimit;
            public String companyCustomer;


            public UpdateTimeBean updateTime;
            public String status;
            public double inrate;


            public StartSaleDateBean startSaleDate;
            public String adMemo;
            public int onceLimit;
            public double expInrateMax;
            public String regulcFlag;
            public String isAging;
            public String lcPrdCode;
            public String allowRedeem;
            public String lcCurrency;
            public int countLimit;
            public String createUser;
            public int maxVirtual;


            public EndAdDateBean endAdDate;
            public int perLimit;
            public String channelSuit;
            public int stepAmount;
            public String updateUser;

            public List<BaseListBean> baseList;
            public static class CreateTimeBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class StartAdDateBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class EndSaleDateBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class OpenDateBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class UpdateTimeBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class StartSaleDateBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class EndAdDateBean {
                public long time;
                public int minutes;
                public int seconds;
                public int hours;
                public int month;
                public int year;
                public int timezoneOffset;
                public int day;
                public int date;
            }

            public static class BaseListBean {

                public CreateTimeBean createTime;
                public UpdateTimeBean updateTime;
                public String status;
                public String lcPrdCode;
                public int id;
                public String createUser;
                public String orgCode;
                public String reserved4;
                public String reserved5;
                public String reserved2;
                public String reserved3;
                public String updateUser;
                public String reserved1;


                public static class CreateTimeBean {
                    public long time;
                    public int minutes;
                    public int seconds;
                    public int hours;
                    public int month;
                    public int year;
                    public int timezoneOffset;
                    public int day;
                    public int date;

                }

                public static class UpdateTimeBean {
                    public long time;
                    public int minutes;
                    public int seconds;
                    public int hours;
                    public int month;
                    public int year;
                    public int timezoneOffset;
                    public int day;
                    public int date;


                }
            }
        }
    }
}
