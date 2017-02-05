package com.ssyijiu.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.TextView;

import com.ssyijiu.demo2.resp.MovieResp;
import com.ssyijiu.demo2.retrofit2.Api;
import com.ssyijiu.demo2.retrofit2.RetrofitClient;
import com.ssyijiu.library.MLog;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = (TextView) findViewById(R.id.hello);

//        test1();
        test2();
//        test3();
//        testMap();
//        testZip();
    }

    public void testZip() {
        Observable<MovieResp> observable1 = RetrofitClient.getApi().getTop250("0", "2");
        observable1.subscribeOn(Schedulers.io());

        Observable<MovieResp> observable2 = RetrofitClient.getApi().getTop250("2", "2");
        observable2.subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<MovieResp, MovieResp, String>() {
            @Override
            public String apply(MovieResp movieResp, MovieResp movieResp2) throws Exception {
                return movieResp.getSubjects().get(0).getTitle() +
                        movieResp2.getSubjects().get(0).getTitle();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                MLog.i(s);
            }
        });


    }

    public void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(30);
                e.onComplete();
            }
        })
        .doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i(integer);
            }
        })
        .subscribeOn(Schedulers.io())
        .flatMap(new Function<Integer, Observable<MovieResp>>() {

            @Override
            public Observable<MovieResp> apply(Integer integer) throws Exception {
                String count = String.valueOf(integer);
                return RetrofitClient.getApi().getTop250("0", count);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<MovieResp>() {
            @Override
            public void accept(MovieResp movieResp) throws Exception {
                List<String> titles = new ArrayList<>();
                for (MovieResp.SubjectsBean subjectsBean : movieResp.getSubjects()) {
                    titles.add(subjectsBean.getTitle());
                }
                MLog.i(titles);
            }
        })
        .subscribe();
    }

    public void test3() {
        Api api = RetrofitClient.getApi();
        api.getTop250()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResp value) {
                        String title = value.getSubjects().get(0).getTitle();
                        hello.setText(title);
                        MLog.i("onNext:" + title);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                MLog.i("Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
            }
        })
                .subscribeOn(Schedulers.io()) // 子线程发送
                .observeOn(AndroidSchedulers.mainThread()) // 主线程接收
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        MLog.i("After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())  // io线程接收
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        MLog.i("After observeOn(io), current thread is: " + Thread.currentThread().getName());

                    }
                }).subscribe();
    }


    public void test1() {
        // 1
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                MLog.i("Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
            }
        });

        // 2
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                MLog.i("Consumer thread is : " + Thread.currentThread().getName());
                MLog.i(integer);
            }
        };

        // 3
        observable.subscribeOn(Schedulers.io()) // 子线程发送
                .observeOn(AndroidSchedulers.mainThread()) // 主线程接收
                .subscribe(consumer);
    }
}
