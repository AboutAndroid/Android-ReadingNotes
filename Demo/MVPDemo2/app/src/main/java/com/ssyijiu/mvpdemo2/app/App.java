package com.ssyijiu.mvpdemo2.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lzh.nonview.router.Router;
import com.lzh.nonview.router.exception.NotFoundException;
import com.lzh.nonview.router.module.RouteMap;
import com.lzh.nonview.router.route.ActivityRouteBundleExtras;
import com.lzh.nonview.router.route.RouteCallback;
import com.squareup.leakcanary.LeakCanary;
import com.ssyijiu.library.MLog;
import com.ssyijiu.mvpdemo2.model.LoginManager;
import com.ssyijiu.mvpdemo2.router.Routes;
import com.ssyijiu.mvpdemo2.ui.LoginActivity;
import com.ssyijiu.mvpdemo2.ui.UserInfoActivity;
import com.ssyijiu.mvpdemo2.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssyijiu on 2016/10/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        initLeakCanary();
        initRouter();

    }

    private void initRouter() {

        // -> LoginActivity
        Router.addRouteCreator(() -> {
            Map<String, RouteMap> routers = new HashMap<>();
            routers.put(Routes.LOGIN,
                    new RouteMap(LoginActivity.class)
                    // .addParam(Routes.LOGIN_PASSWORD, RouteMap.INT) // 指定参数类型，默认 String
            );
            return routers;
        });

        // -> UserInfoActivity
        Router.addRouteCreator(() -> {
            Map<String, RouteMap> routers = new HashMap<>();
            routers.put(Routes.INFO,
                    new RouteMap(UserInfoActivity.class)
            );
            return routers;
        });

        Router.setGlobalRouteCallback(new RouteCallback() {
            @Override
            public boolean interceptOpen(Uri uri, Context context, ActivityRouteBundleExtras extras) {

                // 已登录 or 到登录页面，不拦截
                if(LoginManager.isLogin || uri.toString().startsWith(Routes.LOGIN)) {
                    return false;
                } else {
                    MLog.i("interceptOpen:" + uri);

                    // 登录拦截
                    ToastUtil.show("未登录.请先登录");
                    Intent intent = new Intent(context,LoginActivity.class);
                    intent.putExtra("uri",uri);     // 把拦截到的 url 传过去
                    context.startActivity(intent);
                    return true;
                }
            }

            @Override
            public void notFound(Uri uri, NotFoundException e) {
                MLog.i(uri);
                MLog.i("notFound:" + e.getNotFoundName());
            }

            @Override
            public void onOpenSuccess(Uri uri, String clzName) {
                MLog.i(uri);
                MLog.i("onOpenSuccess:" + clzName);
            }

            @Override
            public void onOpenFailed(Uri uri, Exception e) {
                MLog.i(uri);
                MLog.i("onOpenFailed:" + e.getMessage());
            }
        });
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
