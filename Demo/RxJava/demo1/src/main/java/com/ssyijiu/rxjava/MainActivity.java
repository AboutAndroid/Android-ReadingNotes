package com.ssyijiu.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssyijiu.library.MLog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ssyijiu.library.MLog.LogLev.I;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test2();
        test3();
    }

    public void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
                e.onNext(3);
            }

        // 只关心 onNext 事件,收到 onError、onComplete 依旧终止接收
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i("accept:" + integer);
            }
        });
    }

    public void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                e.onNext(1);
                MLog.i(1);

                e.onNext(2);
                MLog.i(2);

                e.onNext(3);
                MLog.i(3);

                e.onComplete();

                e.onNext(4);
                MLog.i(4);
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                MLog.i("onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                MLog.i("onNext:" + value);
                if(value == 2) {
                    mDisposable.dispose();
                    MLog.i("dispose");
                    MLog.i("isDisposed:" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                MLog.i("onError");
            }

            @Override
            public void onComplete() {
                MLog.i("onComplete");
            }
        });
    }

    public void test1() {
        // 1. 创建一个被观察者(上游)
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });


        // 2. 创建一个被观察者(下游)
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                MLog.i("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                MLog.i(value);
            }

            @Override
            public void onError(Throwable e) {
                MLog.i("onError");
            }

            @Override
            public void onComplete() {
                MLog.i("onComplete");
            }
        };

        // 3. 发送事件(建立连接)
        observable.subscribe(observer);
    }
}
