package com.ssyijiu.demo6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testFilter();
//        testSample();
        testDelay();
    }

    private void testDelay() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                while (true) {
                    e.onNext(i++);
                    Thread.sleep(2000);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i(integer);
            }
        })
        .subscribe();
    }

    private void testSample() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                while (true) {
                    e.onNext(i++);
                    Thread.sleep(2000);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .sample(2, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i(integer);
            }
        })
        .subscribe();
    }

    int i = 0;
    private void testFilter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                while (true) {
                    e.onNext(i++);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer % 100 == 0;

            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i(integer);
                Thread.sleep(1000);
            }
        })
        .subscribe();
    }
}
