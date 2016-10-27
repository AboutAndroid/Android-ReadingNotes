//package com.ssyijiu.dagger2.coffeeshop;
//
//import javax.inject.Inject;
//
//import static com.ssyijiu.library.MLog.LogLev.I;
//
///**
// * Created by ssyijiu on 2016/10/27.
// * Github: ssyijiu
// * E-mail: lxmyijiu@163.com
// */
//
//public class SimpleMaker implements CoffeeMaker {
//    Cooker cooker;
//
//    @Inject
//    public SimpleMaker(Cooker cooker){
//        this.cooker = cooker;
//    }
//
//    @Override
//    public String makeCoffee() {
//        return cooker.make();
//    }
//}
