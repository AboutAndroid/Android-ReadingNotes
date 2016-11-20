package com.ssyijiu.retrofit.bean;

import java.util.List;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class FinancingListResp {

    /**
     * rntCode : OK
     * rntMsg :
     * rntCodeValue : 1
     * responseParams : [{"id":15,"status":"1","skuCode":"10000010","goldName":"10克AU9999金条","goldType":"1","goldGram":10,"realGram":10,"procFee":6.99,"createUser":"80000057","createTime":1459250130000,"updateUser":"80000077","updateTime":1461726884000,"oldSkuCode":null,"number":9,"goldGramName":"https://app.dbjb.comresource/images/gold/10000010.png","storeName":"物流邮寄"},{"id":16,"status":"1","skuCode":"10000050","goldName":"50克AU9999金条","goldType":"1","goldGram":50,"realGram":50,"procFee":5.99,"createUser":"80000057","createTime":1459250192000,"updateUser":"80000057","updateTime":1459250192000,"oldSkuCode":null,"number":2,"goldGramName":"https://app.dbjb.comresource/images/gold/10000050.png","storeName":"物流邮寄"},{"id":17,"status":"1","skuCode":"10000100","goldName":"100克AU9999金条","goldType":"1","goldGram":100,"realGram":100,"procFee":5.99,"createUser":"80000057","createTime":1459250225000,"updateUser":"80000057","updateTime":1459250225000,"oldSkuCode":null,"number":1,"goldGramName":"https://app.dbjb.comresource/images/gold/10000100.png","storeName":"物流邮寄"},{"id":23,"status":"1","skuCode":"10000002","goldName":"金箔扑克牌","goldType":"3","goldGram":0.2,"realGram":0.2,"procFee":0,"createUser":"80000077","createTime":1477475181000,"updateUser":"80000077","updateTime":1477475181000,"oldSkuCode":null,"number":107,"goldGramName":"https://app.dbjb.comresource/images/gold/10000002.png","storeName":"物流邮寄"}]
     * errorMsg : 错误码:1,错误信息:
     */

    private String rntCode;
    private String rntMsg;
    private int rntCodeValue;
    private String errorMsg;
    /**
     * id : 15
     * status : 1
     * skuCode : 10000010
     * goldName : 10克AU9999金条
     * goldType : 1
     * goldGram : 10.0
     * realGram : 10.0
     * procFee : 6.99
     * createUser : 80000057
     * createTime : 1459250130000
     * updateUser : 80000077
     * updateTime : 1461726884000
     * oldSkuCode : null
     * number : 9
     * goldGramName : https://app.dbjb.comresource/images/gold/10000010.png
     * storeName : 物流邮寄
     */

    private List<ResponseParamsBean> responseParams;

    public String getRntCode() {
        return rntCode;
    }

    public void setRntCode(String rntCode) {
        this.rntCode = rntCode;
    }

    public String getRntMsg() {
        return rntMsg;
    }

    public void setRntMsg(String rntMsg) {
        this.rntMsg = rntMsg;
    }

    public int getRntCodeValue() {
        return rntCodeValue;
    }

    public void setRntCodeValue(int rntCodeValue) {
        this.rntCodeValue = rntCodeValue;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ResponseParamsBean> getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(List<ResponseParamsBean> responseParams) {
        this.responseParams = responseParams;
    }

    public static class ResponseParamsBean {
        /**
         * balance : 0.6765
         * allEarnings :
         * tBaseLCProcuct : {"createTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"riskLevel":"0","memo":"1109创建 1109生效","startAdDate":{"time":1478660400000,"minutes":0,"seconds":0,"hours":11,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"reserved8":"","endSaleDate":{"time":1480561200000,"minutes":0,"seconds":0,"hours":11,"month":11,"year":116,"timezoneOffset":-480,"day":4,"date":1},"id":242,"expInrateMin":0.15,"startAmount":5,"lcPrdType":"0","accountPrdCode":"1006","timeUnit":"天","initVirtual":90,"totalAmount":30000,"reserved6":"0.00","reserved7":"","regulcPrice":0,"reserved4":"01","reserved5":"02","reserved2":"99","baseList":[{"createTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"updateTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"status":"1","lcPrdCode":"1006000180","id":242,"createUser":"80000115","orgCode":"","reserved4":"01","reserved5":"02","reserved2":"99","reserved3":"","updateUser":"80000115","reserved1":"0"}],"reserved3":"","openDate":{"time":1479571200000,"minutes":0,"seconds":0,"hours":0,"month":10,"year":116,"timezoneOffset":-480,"day":0,"date":20},"lcPrdName":"新手专享理财金12期","reserved1":"1","timeLimit":15,"companyCustomer":"","updateTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"status":"1","inrate":0.16,"startSaleDate":{"time":1478660400000,"minutes":0,"seconds":0,"hours":11,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"adMemo":"新手专享","onceLimit":5,"expInrateMax":0.17,"regulcFlag":"","isAging":"","lcPrdCode":"1006000180","allowRedeem":"0","lcCurrency":"0","countLimit":1,"createUser":"80000115","maxVirtual":31000,"endAdDate":{"time":1480561200000,"minutes":0,"seconds":0,"hours":11,"month":11,"year":116,"timezoneOffset":-480,"day":4,"date":1},"perLimit":5,"channelSuit":"1111100000000000","stepAmount":1,"updateUser":"80000115"}
         * couponVaild : 0
         * prdType : 0
         * totalGoldUsed : 9705
         * actDefinedList : []
         */

        private String balance;
        private String allEarnings;
        /**
         * createTime : {"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
         * riskLevel : 0
         * memo : 1109创建 1109生效
         * startAdDate : {"time":1478660400000,"minutes":0,"seconds":0,"hours":11,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
         * reserved8 :
         * endSaleDate : {"time":1480561200000,"minutes":0,"seconds":0,"hours":11,"month":11,"year":116,"timezoneOffset":-480,"day":4,"date":1}
         * id : 242
         * expInrateMin : 0.15
         * startAmount : 5
         * lcPrdType : 0
         * accountPrdCode : 1006
         * timeUnit : 天
         * initVirtual : 90
         * totalAmount : 30000
         * reserved6 : 0.00
         * reserved7 :
         * regulcPrice : 0
         * reserved4 : 01
         * reserved5 : 02
         * reserved2 : 99
         * baseList : [{"createTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"updateTime":{"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9},"status":"1","lcPrdCode":"1006000180","id":242,"createUser":"80000115","orgCode":"","reserved4":"01","reserved5":"02","reserved2":"99","reserved3":"","updateUser":"80000115","reserved1":"0"}]
         * reserved3 :
         * openDate : {"time":1479571200000,"minutes":0,"seconds":0,"hours":0,"month":10,"year":116,"timezoneOffset":-480,"day":0,"date":20}
         * lcPrdName : 新手专享理财金12期
         * reserved1 : 1
         * timeLimit : 15
         * companyCustomer :
         * updateTime : {"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
         * status : 1
         * inrate : 0.16
         * startSaleDate : {"time":1478660400000,"minutes":0,"seconds":0,"hours":11,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
         * adMemo : 新手专享
         * onceLimit : 5
         * expInrateMax : 0.17
         * regulcFlag :
         * isAging :
         * lcPrdCode : 1006000180
         * allowRedeem : 0
         * lcCurrency : 0
         * countLimit : 1
         * createUser : 80000115
         * maxVirtual : 31000
         * endAdDate : {"time":1480561200000,"minutes":0,"seconds":0,"hours":11,"month":11,"year":116,"timezoneOffset":-480,"day":4,"date":1}
         * perLimit : 5
         * channelSuit : 1111100000000000
         * stepAmount : 1
         * updateUser : 80000115
         */

        private TBaseLCProcuctBean tBaseLCProcuct;
        private String couponVaild;
        private String prdType;
        private int totalGoldUsed;
        private List<?> actDefinedList;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAllEarnings() {
            return allEarnings;
        }

        public void setAllEarnings(String allEarnings) {
            this.allEarnings = allEarnings;
        }

        public TBaseLCProcuctBean getTBaseLCProcuct() {
            return tBaseLCProcuct;
        }

        public void setTBaseLCProcuct(TBaseLCProcuctBean tBaseLCProcuct) {
            this.tBaseLCProcuct = tBaseLCProcuct;
        }

        public String getCouponVaild() {
            return couponVaild;
        }

        public void setCouponVaild(String couponVaild) {
            this.couponVaild = couponVaild;
        }

        public String getPrdType() {
            return prdType;
        }

        public void setPrdType(String prdType) {
            this.prdType = prdType;
        }

        public int getTotalGoldUsed() {
            return totalGoldUsed;
        }

        public void setTotalGoldUsed(int totalGoldUsed) {
            this.totalGoldUsed = totalGoldUsed;
        }

        public List<?> getActDefinedList() {
            return actDefinedList;
        }

        public void setActDefinedList(List<?> actDefinedList) {
            this.actDefinedList = actDefinedList;
        }

        public static class TBaseLCProcuctBean {
            /**
             * time : 1478656948000
             * minutes : 2
             * seconds : 28
             * hours : 10
             * month : 10
             * year : 116
             * timezoneOffset : -480
             * day : 3
             * date : 9
             */

            private CreateTimeBean createTime;
            private String riskLevel;
            private String memo;
            /**
             * time : 1478660400000
             * minutes : 0
             * seconds : 0
             * hours : 11
             * month : 10
             * year : 116
             * timezoneOffset : -480
             * day : 3
             * date : 9
             */

            private StartAdDateBean startAdDate;
            private String reserved8;
            /**
             * time : 1480561200000
             * minutes : 0
             * seconds : 0
             * hours : 11
             * month : 11
             * year : 116
             * timezoneOffset : -480
             * day : 4
             * date : 1
             */

            private EndSaleDateBean endSaleDate;
            private int id;
            private double expInrateMin;
            private int startAmount;
            private String lcPrdType;
            private String accountPrdCode;
            private String timeUnit;
            private int initVirtual;
            private int totalAmount;
            private String reserved6;
            private String reserved7;
            private int regulcPrice;
            private String reserved4;
            private String reserved5;
            private String reserved2;
            private String reserved3;
            /**
             * time : 1479571200000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 10
             * year : 116
             * timezoneOffset : -480
             * day : 0
             * date : 20
             */

            private OpenDateBean openDate;
            private String lcPrdName;
            private String reserved1;
            private int timeLimit;
            private String companyCustomer;
            /**
             * time : 1478656948000
             * minutes : 2
             * seconds : 28
             * hours : 10
             * month : 10
             * year : 116
             * timezoneOffset : -480
             * day : 3
             * date : 9
             */

            private UpdateTimeBean updateTime;
            private String status;
            private double inrate;
            /**
             * time : 1478660400000
             * minutes : 0
             * seconds : 0
             * hours : 11
             * month : 10
             * year : 116
             * timezoneOffset : -480
             * day : 3
             * date : 9
             */

            private StartSaleDateBean startSaleDate;
            private String adMemo;
            private int onceLimit;
            private double expInrateMax;
            private String regulcFlag;
            private String isAging;
            private String lcPrdCode;
            private String allowRedeem;
            private String lcCurrency;
            private int countLimit;
            private String createUser;
            private int maxVirtual;
            /**
             * time : 1480561200000
             * minutes : 0
             * seconds : 0
             * hours : 11
             * month : 11
             * year : 116
             * timezoneOffset : -480
             * day : 4
             * date : 1
             */

            private EndAdDateBean endAdDate;
            private int perLimit;
            private String channelSuit;
            private int stepAmount;
            private String updateUser;
            /**
             * createTime : {"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
             * updateTime : {"time":1478656948000,"minutes":2,"seconds":28,"hours":10,"month":10,"year":116,"timezoneOffset":-480,"day":3,"date":9}
             * status : 1
             * lcPrdCode : 1006000180
             * id : 242
             * createUser : 80000115
             * orgCode :
             * reserved4 : 01
             * reserved5 : 02
             * reserved2 : 99
             * reserved3 :
             * updateUser : 80000115
             * reserved1 : 0
             */

            private List<BaseListBean> baseList;

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public String getRiskLevel() {
                return riskLevel;
            }

            public void setRiskLevel(String riskLevel) {
                this.riskLevel = riskLevel;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public StartAdDateBean getStartAdDate() {
                return startAdDate;
            }

            public void setStartAdDate(StartAdDateBean startAdDate) {
                this.startAdDate = startAdDate;
            }

            public String getReserved8() {
                return reserved8;
            }

            public void setReserved8(String reserved8) {
                this.reserved8 = reserved8;
            }

            public EndSaleDateBean getEndSaleDate() {
                return endSaleDate;
            }

            public void setEndSaleDate(EndSaleDateBean endSaleDate) {
                this.endSaleDate = endSaleDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getExpInrateMin() {
                return expInrateMin;
            }

            public void setExpInrateMin(double expInrateMin) {
                this.expInrateMin = expInrateMin;
            }

            public int getStartAmount() {
                return startAmount;
            }

            public void setStartAmount(int startAmount) {
                this.startAmount = startAmount;
            }

            public String getLcPrdType() {
                return lcPrdType;
            }

            public void setLcPrdType(String lcPrdType) {
                this.lcPrdType = lcPrdType;
            }

            public String getAccountPrdCode() {
                return accountPrdCode;
            }

            public void setAccountPrdCode(String accountPrdCode) {
                this.accountPrdCode = accountPrdCode;
            }

            public String getTimeUnit() {
                return timeUnit;
            }

            public void setTimeUnit(String timeUnit) {
                this.timeUnit = timeUnit;
            }

            public int getInitVirtual() {
                return initVirtual;
            }

            public void setInitVirtual(int initVirtual) {
                this.initVirtual = initVirtual;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getReserved6() {
                return reserved6;
            }

            public void setReserved6(String reserved6) {
                this.reserved6 = reserved6;
            }

            public String getReserved7() {
                return reserved7;
            }

            public void setReserved7(String reserved7) {
                this.reserved7 = reserved7;
            }

            public int getRegulcPrice() {
                return regulcPrice;
            }

            public void setRegulcPrice(int regulcPrice) {
                this.regulcPrice = regulcPrice;
            }

            public String getReserved4() {
                return reserved4;
            }

            public void setReserved4(String reserved4) {
                this.reserved4 = reserved4;
            }

            public String getReserved5() {
                return reserved5;
            }

            public void setReserved5(String reserved5) {
                this.reserved5 = reserved5;
            }

            public String getReserved2() {
                return reserved2;
            }

            public void setReserved2(String reserved2) {
                this.reserved2 = reserved2;
            }

            public String getReserved3() {
                return reserved3;
            }

            public void setReserved3(String reserved3) {
                this.reserved3 = reserved3;
            }

            public OpenDateBean getOpenDate() {
                return openDate;
            }

            public void setOpenDate(OpenDateBean openDate) {
                this.openDate = openDate;
            }

            public String getLcPrdName() {
                return lcPrdName;
            }

            public void setLcPrdName(String lcPrdName) {
                this.lcPrdName = lcPrdName;
            }

            public String getReserved1() {
                return reserved1;
            }

            public void setReserved1(String reserved1) {
                this.reserved1 = reserved1;
            }

            public int getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(int timeLimit) {
                this.timeLimit = timeLimit;
            }

            public String getCompanyCustomer() {
                return companyCustomer;
            }

            public void setCompanyCustomer(String companyCustomer) {
                this.companyCustomer = companyCustomer;
            }

            public UpdateTimeBean getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(UpdateTimeBean updateTime) {
                this.updateTime = updateTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getInrate() {
                return inrate;
            }

            public void setInrate(double inrate) {
                this.inrate = inrate;
            }

            public StartSaleDateBean getStartSaleDate() {
                return startSaleDate;
            }

            public void setStartSaleDate(StartSaleDateBean startSaleDate) {
                this.startSaleDate = startSaleDate;
            }

            public String getAdMemo() {
                return adMemo;
            }

            public void setAdMemo(String adMemo) {
                this.adMemo = adMemo;
            }

            public int getOnceLimit() {
                return onceLimit;
            }

            public void setOnceLimit(int onceLimit) {
                this.onceLimit = onceLimit;
            }

            public double getExpInrateMax() {
                return expInrateMax;
            }

            public void setExpInrateMax(double expInrateMax) {
                this.expInrateMax = expInrateMax;
            }

            public String getRegulcFlag() {
                return regulcFlag;
            }

            public void setRegulcFlag(String regulcFlag) {
                this.regulcFlag = regulcFlag;
            }

            public String getIsAging() {
                return isAging;
            }

            public void setIsAging(String isAging) {
                this.isAging = isAging;
            }

            public String getLcPrdCode() {
                return lcPrdCode;
            }

            public void setLcPrdCode(String lcPrdCode) {
                this.lcPrdCode = lcPrdCode;
            }

            public String getAllowRedeem() {
                return allowRedeem;
            }

            public void setAllowRedeem(String allowRedeem) {
                this.allowRedeem = allowRedeem;
            }

            public String getLcCurrency() {
                return lcCurrency;
            }

            public void setLcCurrency(String lcCurrency) {
                this.lcCurrency = lcCurrency;
            }

            public int getCountLimit() {
                return countLimit;
            }

            public void setCountLimit(int countLimit) {
                this.countLimit = countLimit;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public int getMaxVirtual() {
                return maxVirtual;
            }

            public void setMaxVirtual(int maxVirtual) {
                this.maxVirtual = maxVirtual;
            }

            public EndAdDateBean getEndAdDate() {
                return endAdDate;
            }

            public void setEndAdDate(EndAdDateBean endAdDate) {
                this.endAdDate = endAdDate;
            }

            public int getPerLimit() {
                return perLimit;
            }

            public void setPerLimit(int perLimit) {
                this.perLimit = perLimit;
            }

            public String getChannelSuit() {
                return channelSuit;
            }

            public void setChannelSuit(String channelSuit) {
                this.channelSuit = channelSuit;
            }

            public int getStepAmount() {
                return stepAmount;
            }

            public void setStepAmount(int stepAmount) {
                this.stepAmount = stepAmount;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public List<BaseListBean> getBaseList() {
                return baseList;
            }

            public void setBaseList(List<BaseListBean> baseList) {
                this.baseList = baseList;
            }

            public static class CreateTimeBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class StartAdDateBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class EndSaleDateBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class OpenDateBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class UpdateTimeBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class StartSaleDateBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class EndAdDateBean {
                private long time;
                private int minutes;
                private int seconds;
                private int hours;
                private int month;
                private int year;
                private int timezoneOffset;
                private int day;
                private int date;

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }
            }

            public static class BaseListBean {
                /**
                 * time : 1478656948000
                 * minutes : 2
                 * seconds : 28
                 * hours : 10
                 * month : 10
                 * year : 116
                 * timezoneOffset : -480
                 * day : 3
                 * date : 9
                 */

                private CreateTimeBean createTime;
                /**
                 * time : 1478656948000
                 * minutes : 2
                 * seconds : 28
                 * hours : 10
                 * month : 10
                 * year : 116
                 * timezoneOffset : -480
                 * day : 3
                 * date : 9
                 */

                private UpdateTimeBean updateTime;
                private String status;
                private String lcPrdCode;
                private int id;
                private String createUser;
                private String orgCode;
                private String reserved4;
                private String reserved5;
                private String reserved2;
                private String reserved3;
                private String updateUser;
                private String reserved1;

                public CreateTimeBean getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(CreateTimeBean createTime) {
                    this.createTime = createTime;
                }

                public UpdateTimeBean getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(UpdateTimeBean updateTime) {
                    this.updateTime = updateTime;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getLcPrdCode() {
                    return lcPrdCode;
                }

                public void setLcPrdCode(String lcPrdCode) {
                    this.lcPrdCode = lcPrdCode;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCreateUser() {
                    return createUser;
                }

                public void setCreateUser(String createUser) {
                    this.createUser = createUser;
                }

                public String getOrgCode() {
                    return orgCode;
                }

                public void setOrgCode(String orgCode) {
                    this.orgCode = orgCode;
                }

                public String getReserved4() {
                    return reserved4;
                }

                public void setReserved4(String reserved4) {
                    this.reserved4 = reserved4;
                }

                public String getReserved5() {
                    return reserved5;
                }

                public void setReserved5(String reserved5) {
                    this.reserved5 = reserved5;
                }

                public String getReserved2() {
                    return reserved2;
                }

                public void setReserved2(String reserved2) {
                    this.reserved2 = reserved2;
                }

                public String getReserved3() {
                    return reserved3;
                }

                public void setReserved3(String reserved3) {
                    this.reserved3 = reserved3;
                }

                public String getUpdateUser() {
                    return updateUser;
                }

                public void setUpdateUser(String updateUser) {
                    this.updateUser = updateUser;
                }

                public String getReserved1() {
                    return reserved1;
                }

                public void setReserved1(String reserved1) {
                    this.reserved1 = reserved1;
                }

                public static class CreateTimeBean {
                    private long time;
                    private int minutes;
                    private int seconds;
                    private int hours;
                    private int month;
                    private int year;
                    private int timezoneOffset;
                    private int day;
                    private int date;

                    public long getTime() {
                        return time;
                    }

                    public void setTime(long time) {
                        this.time = time;
                    }

                    public int getMinutes() {
                        return minutes;
                    }

                    public void setMinutes(int minutes) {
                        this.minutes = minutes;
                    }

                    public int getSeconds() {
                        return seconds;
                    }

                    public void setSeconds(int seconds) {
                        this.seconds = seconds;
                    }

                    public int getHours() {
                        return hours;
                    }

                    public void setHours(int hours) {
                        this.hours = hours;
                    }

                    public int getMonth() {
                        return month;
                    }

                    public void setMonth(int month) {
                        this.month = month;
                    }

                    public int getYear() {
                        return year;
                    }

                    public void setYear(int year) {
                        this.year = year;
                    }

                    public int getTimezoneOffset() {
                        return timezoneOffset;
                    }

                    public void setTimezoneOffset(int timezoneOffset) {
                        this.timezoneOffset = timezoneOffset;
                    }

                    public int getDay() {
                        return day;
                    }

                    public void setDay(int day) {
                        this.day = day;
                    }

                    public int getDate() {
                        return date;
                    }

                    public void setDate(int date) {
                        this.date = date;
                    }
                }

                public static class UpdateTimeBean {
                    private long time;
                    private int minutes;
                    private int seconds;
                    private int hours;
                    private int month;
                    private int year;
                    private int timezoneOffset;
                    private int day;
                    private int date;

                    public long getTime() {
                        return time;
                    }

                    public void setTime(long time) {
                        this.time = time;
                    }

                    public int getMinutes() {
                        return minutes;
                    }

                    public void setMinutes(int minutes) {
                        this.minutes = minutes;
                    }

                    public int getSeconds() {
                        return seconds;
                    }

                    public void setSeconds(int seconds) {
                        this.seconds = seconds;
                    }

                    public int getHours() {
                        return hours;
                    }

                    public void setHours(int hours) {
                        this.hours = hours;
                    }

                    public int getMonth() {
                        return month;
                    }

                    public void setMonth(int month) {
                        this.month = month;
                    }

                    public int getYear() {
                        return year;
                    }

                    public void setYear(int year) {
                        this.year = year;
                    }

                    public int getTimezoneOffset() {
                        return timezoneOffset;
                    }

                    public void setTimezoneOffset(int timezoneOffset) {
                        this.timezoneOffset = timezoneOffset;
                    }

                    public int getDay() {
                        return day;
                    }

                    public void setDay(int day) {
                        this.day = day;
                    }

                    public int getDate() {
                        return date;
                    }

                    public void setDate(int date) {
                        this.date = date;
                    }
                }
            }
        }
    }
}
