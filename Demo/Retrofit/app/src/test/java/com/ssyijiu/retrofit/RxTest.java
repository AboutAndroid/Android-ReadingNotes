package com.ssyijiu.retrofit;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.R.attr.name;


/**
 * Created by ssyijiu on 2016/11/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RxTest {


    private Subscriber<String> mSubscriber;
    private Observable<String> mObservable;

    @Before
    public void setUp() {

        // 1. 创建被观察者,并注入事件

        // create
//        mObservable = Observable.create(new Observable.OnSubscribe<String>() {
//
//            // 通知订阅者
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onCompleted();
//            }
//        });


        // just
//         mObservable = Observable.just("lxm","rxjava");
        // subscriber.onNext("lxm");
        // subscriber.onNext("rxjava");
        // subscriber.onCompleted();

        // from
        mObservable = Observable.from(new String[]{"rxjava","lxm"});

        // 2. 创建订阅者，指定拿到事件后的操作
        mSubscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }
        };
    }

    @Test
    public void test() {

        // 3. 发送事件
        mObservable.subscribe(mSubscriber);
    }

    @Test@SuppressWarnings("all")
    public void printArgs() {
        String[] names = {"lxm","cjq","android"};
        Observable.from(names)  // 1
                // 3
                .subscribe(new Action1<String>() { // 2
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }
}

    /**
     * onCompleted(): 事件队列完结。
     * RxJava 不仅把每个事件单独处理，还会把它们看做一个队列。
     * RxJava 规定，当不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为标志。
     *
     * onError(): 事件队列异常。
     * 在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出。
     *
     * 在一个正确运行的事件序列中, onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。
     * 需要注意的是，onCompleted() 和 onError() 二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。
     */


