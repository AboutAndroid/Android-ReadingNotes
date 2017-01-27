package com.ssyijiu.demo9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ssyijiu.library.MLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubscription.request(96);
            }
        });

//        test1();
//        test2();
//        test3();
        test4();
    }

    int i = 0;
    private void test4() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                while (true) {
                    if(e.requested() != 0) {
                        e.onNext(i++);
                        MLog.i("emit " + i + " , requested = " + e.requested());
                    }

                }
            }
        },BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        mSubscription = s;

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

    private void test3() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                MLog.i("Emitter requested:" + e.requested());

                e.onNext(1);
                MLog.i("Emitter requested:" + e.requested());

                e.onNext(2);
                MLog.i("Emitter requested:" + e.requested());

                e.onNext(3);
                MLog.i("Emitter requested:" + e.requested());
            }
        },BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        MLog.i("onSubscribe");
                        s.request(10);
//                        s.request(2);
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

    private void test2() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                MLog.i("subscribe FlowableEmitter requested:" + e.requested());
            }
        },BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        MLog.i("onSubscribe");
                        s.request(10);
                        s.request(100);
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

    private void test1() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 5; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        mSubscription.request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        MLog.i("onNext:" + integer);
                        mSubscription.request(1);
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
