package com.ssyijiu.demo9;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ssyijiu on 2017/1/27.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public class Demo {

    private static Subscription mSubscription;

    public static void main(String[] args) {
        demo();

        // 让主线程睡一会，否则主线程会很快结束，看不到输出
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demo() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                try {
                    File f = new File("test.txt");
                    FileReader reader = new FileReader(f);
                    BufferedReader br = new BufferedReader(reader);

                    String str;


                    while ((str = br.readLine()) != null && !emitter.isCancelled()) {
                        if(emitter.requested() != 0) {
                            emitter.onNext(str);
                        }
                    }

                    br.close();
                    reader.close();

                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                        try {
                            Thread.sleep(1000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
