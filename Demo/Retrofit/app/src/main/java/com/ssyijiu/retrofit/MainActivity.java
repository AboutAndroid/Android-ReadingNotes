package com.ssyijiu.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import com.ssyijiu.library.MLog;
import com.ssyijiu.mvp.MvpActivity;
import com.ssyijiu.mvp.i.MvpPresenter;
import com.ssyijiu.retrofit.bean.vo.User;
import com.ssyijiu.retrofit.mvp.RetrofitContract;
import com.ssyijiu.retrofit.mvp.RetrofitPresenter;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * http://www.codexiu.cn/android/blog/18502/
 * http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237358&idx=1&sn=f71478d5c450f588ed1678752ec36f6b&chksm=886398c1bf1411d7d8ae4369114e6737291d278c8a9225364d2a7f8b29d6c40e0db291f74217&mpshare=1&scene=1&srcid=1012ccqYkXxK2jVQpdHdT9bO#wechat_redirect
 */
public class MainActivity extends MvpActivity implements RetrofitContract.View {

    private TextView tvInfo;
    RetrofitPresenter presenter = new RetrofitPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        findViewById(R.id.btn_get).setOnClickListener((view) -> presenter.getGetMsg());

        findViewById(R.id.btn_post).setOnClickListener((view) -> presenter.getPostMsg());

        findViewById(R.id.btn_gold_price).setOnClickListener((view) -> presenter.getGoldPrice());

        findViewById(R.id.btn_financing_list).setOnClickListener((view) -> presenter.getFinancingList());

        findViewById(R.id.btn_rxjava).setOnClickListener((view) -> runRxjava());


    }

    @SuppressWarnings("all")
    private void runRxjava() {

        // http://gank.io/post/560e15be2dca930e00da1083
        // https://realm.io/cn/news/gotocph-jake-wharton-exploring-rxjava2-android/
        // https://zhuanlan.zhihu.com/p/23617414

        // 1.创建被观察者，并注入事件
        // 2.创建订阅者，并指定拿到事件后的操作
        // 3.发送事件

        /*String[] names = {"lxm","android","java","rxjava"};
        Observable.from(names)  // 1
                .subscribeOn(Schedulers.io())               // IO线程订阅
                .observeOn(AndroidSchedulers.mainThread())  // 主线程回调
                .map(new Func1<String, String>() {   // 变换，将一个类型变成另一个
                    @Override
                    public String call(String s) {
                        return "rx-map:"+s;
                    }
                })
                // 3
                .subscribe(new Action1<String>() { // 2
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });*/

        User[] users = {new User("001", "lxm"), new User("002", "java"),
                new User("003", "android"), new User("004", "linux")};

        Observable.from(users) // 1

                // 变换，将 User 变换成 Observable<User.Course>
                .flatMap(new Func1<User, Observable<User.Course>>() {
                    @Override
                    public Observable<User.Course> call(User user) {
                        return Observable.from(user.courses); // 001,lxm
                                                              // 002,java
                                                              // 003,android
                                                              // 004,linux
                    }
                })
                // 3
                .subscribe(new Action1<User.Course>() {  // 2
                    @Override
                    public void call(User.Course course) {
                        MLog.i(course.cname);
                    }
                });
    }

    @Override
    protected MvpPresenter[] getPresenters() {
        return new MvpPresenter[]{presenter};
    }

    @Override
    public void showLoading(String loadingMsg) {
        tvInfo.setText(loadingMsg);
    }

    @Override
    public void showSuccess(Object resp) {
        tvInfo.setText((String) resp);
    }

    @Override
    public void showError(String errorMsg) {
        tvInfo.setText(errorMsg);
    }

    @Override
    public void showException(Throwable tr) {
        tvInfo.setText(tr.getMessage());
    }

}


