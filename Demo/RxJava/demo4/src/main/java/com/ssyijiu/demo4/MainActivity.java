package com.ssyijiu.demo4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zip();
    }

    private void zip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                MLog.i("Emitter:" + 1);
                e.onNext(1);
                Thread.sleep(1000);

                MLog.i("Emitter:" + 2);
                e.onNext(2);

                MLog.i("Emitter:" + 3);
                e.onNext(3);

                MLog.i("Emitter:" + 4);
                e.onNext(4);

                MLog.i("Emitter:" + "onComplete-1234");
                e.onComplete();

            }
        }).subscribeOn(Schedulers.newThread());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                MLog.i("Emitter:A");
                e.onNext("A");

                MLog.i("Emitter:B");
                e.onNext("B");

                MLog.i("Emitter:C");
                e.onNext("C");

                MLog.i("Emitter:onComplete-ABC");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                MLog.i("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                MLog.i(value);
            }

            @Override
            public void onError(Throwable e) {
                MLog.i("onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                MLog.i("onComplete");
            }
        });
    }
}