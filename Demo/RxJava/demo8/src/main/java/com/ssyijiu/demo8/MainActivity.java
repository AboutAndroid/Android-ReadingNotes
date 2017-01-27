package com.ssyijiu.demo8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ssyijiu.library.MLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Subscription mSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDrop();
            }
        });

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(128);
            }
        });

//        testBUFFER();

        testInterval();
    }

    private void testInterval() {
        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureBuffer() // 添加背压策略
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MLog.i("onNext:",aLong);
                    }

                    @Override
                    public void onError(Throwable t) {
                        MLog.i("onError",t);
                    }

                    @Override
                    public void onComplete() {
                        MLog.i("onComplete");
                    }
                });
    }

    public void request(long n) {
        mSubscription.request(n);
    }

    int i = 0;
    private void testDrop() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                while (i<10000) {
                    e.onNext(i++);
                }
            }
        },BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        MLog.i("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        MLog.i("onError:",t);
                    }

                    @Override
                    public void onComplete() {
                        MLog.i("onComplete");
                    }
                });
    }


    /**
     * 用新水缸啦
     */
    private void testBUFFER() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    MLog.i("FlowableEmitter:" + i);
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        MLog.i("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        MLog.i("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        MLog.i("onError",t);
                    }

                    @Override
                    public void onComplete() {
                        MLog.i("onComplete");
                    }
                });
    }


}
