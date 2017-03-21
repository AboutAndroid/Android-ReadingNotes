# App 冷启动优化实践
## 一. App 冷启动流程
1. 用户在 Launcher 上点击 App Icon，加载启动 App。
2. 在 App 启动之后立即显示一个空白的启动窗口（白屏或者黑屏）。
3. 系统为 App 创建进程。
在创建 App 进程后，App 进程会执行以下任务。
1. 创建 Application 对象。
2. 启动主线程。
3. 创建 Launcher Activity。
4. 加载 View。
5. 确定 View 在屏幕上的摆放位置。
6. 执行 View 的初次绘制。
在 View 的初次绘制完成后系统进程就会把启动窗口替换成 App 的显示窗口，整个App 启动完成。
具体流程如下图：
![](http://obe5pxv6t.bkt.clouddn.com/launch_time_start_process.png)
对于以上流程，我们的优化主要集中在系统创建 App 进程后，主要可以分为以下几点：
1. 替换系统的空白的启动窗口，可以作为 Splash。
2. Application 的 onCreate 方法。
3. Launcher Activity 的 onCreate 方法。
4. Launcher Activity 布局优化，提高 View 的加载及绘制效率。


## 二. App 启动时间测量
知道了 App 的启动流程及可优化的部分，我们还要准确的测量一下 App 的启动时间，要不怎么知道我们的优化是否有效呢？   


## Finaly 实践：
### 0. 替换系统的空白的启动窗口
这并不能加快 App 的启动速度，只是让用户体验更好。
### 1. Application onCreate 优化：
优化前Application onCreate 方法，其实还是挺多的。   
```
Common.init();  // 给底层工具库传递 context
MultiDex.install(this);  // MultiDex 初始化
LeakCanary.install();  // LeakCanary 初始化
BlockCanary.install();  // BlockCanary 初始化
mPushAgent.register();  // 注册友盟推送
QbSdk.initX5Environment(); // X5WebView 初始化
CrashReport.initCrashReport(); // Bugly 初始化
CrashInterceptor.install()    // 处理未捕获异常
RxActivityResult.register();  // RxActivityResult 初始化
```
使用 `Displayed` 过滤 log
小米 4c，Android 5.0 五次启动时间：1s478ms、1s504ms、1s413ms、1s515ms、1s654ms
平均值： 1s513ms       

将除 MultiDex 和 Common.init() 外的其他工作都放在子线程并延迟执行后：
```
// 我这里用 RxJava 实现的
Observable.create(e -> {
            // 执行一些初始化操作
            }).delay(DELAY, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).subscribe();
```
小米 4c，Android 5.0 五次启动时间：1s357ms、1s357ms、1s334ms、1s427ms、1s421ms   
平均值： 1s379ms       

###2. MainActivity onCreate 优化：
优化前：
```
mContext = this;
AppManager.getInstance().addActivity(this);
ButterKnife.bind(this);
bindBundle(savedInstanceState);
initView()
mLoadingDialog = new LoadingDialog(mContext);
parseIntent(getIntent()
NetUtil.registerNetChangedListener(mNetChangedListener);
PushAgent.getInstance(mContext).onAppStart();
```


## Thanks
[Launch-Time Performance](https://developer.android.google.cn/topic/performance/launch-time.html)   
[Android性能优化（一）之启动加速35%](http://www.jianshu.com/p/f5514b1a826c)
[一触即发 App启动优化最佳实践](http://www.jianshu.com/p/672d9bbbf684)
[Android性能优化典范 - 第6季](http://hukai.me/android-performance-patterns-season-6/)

