> 声明：本文为作者原创，转载请注明出自[书生依旧的简书](http://zmywly8866.github.io/2014/12/26/android-do-not-store-data-in-the-application-object.html)
#Application的使用
##一. Application介绍
Application是安卓中保持应用全局状态中的一个类，继承与Context，我们使用的时候需要创建一个Application的子类，并在AndroidManifest.xml的< application>节点下配置这个子类的全类名，Application类在应用进程创建的时候被实例化，比其他类都要早，生命周期和应用的生命周期一致。
###提示：
Application类本身就是单例的，如果你的单例需要一个全局的Context对象，可以考虑使用这个类。
###注意：
由于Application的生命周期和应用一致，尽量不要在这个类中存放图片等一些消耗内存资源的对象。
#二.Application的使用（1）：提供一个全局的Context（Application）对象
直接上代码：
```java
public class ThisApp extends Application {
    // 创建一个Context对象	
	private static Context sContext;
	public static Context getContext() {
		return sContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 在Application创建的时候赋值
		sContext = this;
	}
}
```
配置清单文件：在application节点下加入这一行，这样系统在创建Application的时候就不会创建原来的Application，转而去创建我们这个Application的子类。
```html
android:name="com.ssyijiu.application.ThisApp"
```
这样，在任何地方我们都可以直接是用下面这行代码来获取一个Context
```java
ThisApp.getContext();
```
另外，在Application这个类中，this和this.getApplicationContext()有什么关系呢？
我们来打印一下：
```java
System.out.println(this);
System.out.println(this.getApplicationContext());
System.out.println(this == this.getApplicationContext());
```
看运行结果：
![](./_image/2016-05-30 14-40-01.jpg)
OK，其实是一个东西。

#二.Application的使用（2）：缓存数据？
既然在上面我们可以随时获取一个全局的Apploication对象，那么我们可以将一些数据保存在Application中啊，什么时候想要什么时候取~~~~~~
这个方法听起来不多，而且非常的简单、优雅，**但是这是完全错误的**。
那是因为在低内存情况下，Application有可能被销毁，从而导致保存在Application里面的数据信息丢失，最后程序错乱，甚至是Crash。
所以当你想在Application保存数据的时候，请做好为空判断，总之不建议这样做。
具体的看这里：[不要在Android的Application对象中缓存数据!](http://zmywly8866.github.io/2014/12/26/android-do-not-store-data-in-the-application-object.html)

#三.Application的使用（3）：处理未捕获异常与崩溃重启

#四.Application的使用（4）：退出一个打开多个Activity的Application
#五.Application的使用（5）：一些其他操作

#参考文档
https://developer.android.com/reference/android/app/Application.html
#联系作者

