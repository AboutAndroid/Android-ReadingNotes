package com.ssyijiu.demo7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ssyijiu.library.MLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                testRequest();
            }
        });

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(1);
            }
        });

        testFlowable();
    }


    public void request(long n) {
        mSubscription.request(n);
    }

    public void testRequest() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                MLog.i("Emitter : 1");
                e.onNext(1);
                MLog.i("Emitter : 2");
                e.onNext(2);
                MLog.i("Emitter : 3");
                e.onNext(3);
                MLog.i("Emitter : onComplete");
                e.onComplete();
            }
        },BackpressureStrategy.ERROR)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                MLog.i("onSubscribe");
                mSubscription = s;
//                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                MLog.i(integer);
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

    private void testFlowable() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {

                // Flowable 缓存 128 个事件
                for (int i = 0; i < 129; i++) {
                    MLog.i(i);
                    e.onNext(i);
                }

//                MLog.i("Emitter : 1");
//                e.onNext(1);
//                MLog.i("Emitter : 2");
//                e.onNext(2);
//                MLog.i("Emitter : 3");
//                e.onNext(3);
//                MLog.i("Emitter : onComplete");
//                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                MLog.i("onSubscribe");
//                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                MLog.i(integer);
            }

            @Override
            public void onError(Throwable t) {
                MLog.i("onError",t);
            }

            @Override
            public void onComplete() {
                MLog.i("onComplete");
            }
        };

        upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downstream);
    }
}
