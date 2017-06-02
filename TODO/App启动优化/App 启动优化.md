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
小米 4c，Android 5.0 系统，注意这是 Art 虚拟机，第一次安装时会将字节码编译成机器码保存在本地，以后运行时会直接加载机器码，所以第一次安装的启动时间会长一些。
第一次安装启动：2s160ms
五次正常启动：1s547ms、1s485ms、1s656ms、1s539ms、1s552ms
正常启动平均值： 1s556ms       

将除 MultiDex 和 Common.init() 外的其他工作都放在子线程并延迟执行：
```
// 我这里用 RxJava 实现的
Observable.create(e -> {
            // 执行一些初始化操作
            }).delay(DELAY, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).subscribe();
```
第一次安装启动时间：1s490ms 
正常启动时间：1s363ms、1s201ms、1s395ms、1s372ms、1s324ms
正常启动平均值： 1s331ms       
可以看到，我们的优化对第一次安装启动效果十分明显，对后续的正常启动速度也有提升。

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
按照套路，此时我们应该尽量减少耗时操作，并将一些不需要立即使用的初始化延迟加载。   
但是问题来了，我现在不清楚哪些是耗时操作，而且看上去这些初始化都不能延迟加载。
OK，现在我们借助 TraceView 分析一下耗时操作在哪里。

## Thanks
[Launch-Time Performance](https://developer.android.google.cn/topic/performance/launch-time.html)   
[Android性能优化（一）之启动加速35%](http://www.jianshu.com/p/f5514b1a826c)
[一触即发 App启动优化最佳实践](http://www.jianshu.com/p/672d9bbbf684)
[Android性能优化典范 - 第6季](http://hukai.me/android-performance-patterns-season-6/)

