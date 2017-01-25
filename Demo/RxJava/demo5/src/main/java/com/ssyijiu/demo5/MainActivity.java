package com.ssyijiu.demo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testOOM();
        testThread();
    }

    private void testThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                while (true) {
                    e.onNext(1);
                    MLog.i(1);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Thread.sleep(1000);
            }
        })
        .subscribe();
    }

    private void testOOM() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                while (true) {
                    e.onNext(1);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable
                .zip(observable1, observable2, new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return integer + s;
                    }
                })
                .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                MLog.i(s);
            }
        });
    }


}
