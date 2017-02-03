Context

1. 什么是 Context
   Android 应用模型是基于组件的应用设计模式，组件的运行要有一个完整的 Android 工程环境，在这个环境下，Activity、Service 等系统组件才能够正常工作，而这些组件并不能采用普通的 Java 对象创建方式，new 一下就能创建实例了，而是要有它们各自的上下文环境，也就是 Context。
2. 类结构图
   ![](http://obe5pxv6t.bkt.clouddn.com/context-uml.png)
   Context 的两个子类分工明确：其中 ContextImpl 是 Context 的具体实现类，它实现了 Context 中的所以函数，应用程序中所调用的各种 Context 类的方法，其实现均来自于该类；ContextWrapper 是 Context 的包装类，Activity、Application、Service 虽都继承 ContextWrapper，但它们初始化的过程中都会创建 ContextImpl 对象，由 ContextImpl 实现 Context 中的方法。
3. Context 一共有三种类型，分别是 Application、Activity 和 Service。
   Context 数量=Activity 数量+Service 数量+1
4. 内存泄漏，一般 Context 造成的内存泄漏，几乎都是当 Context 销毁的时候，却因为被引用导致销毁失败。
   使用 Context 的正确姿势：
* 当 Application 的 Context 能搞定的情况下，并且生命周期长的对象，优先使用 Application 的 Context。
* 不要让生命周期长于 Activity 的对象持有到 Activity 的引用。
* 尽量不要在 Activity 中使用非静态内部类，因为非静态内部类会隐式持有外部类实例的引用，如果使用静态内部类，将外部实例引用作为弱引用持有。
  ​
5. Context 的使用场景![](http://obe5pxv6t.bkt.clouddn.com/context.png)
   大家注意看到有一些 NO上添加了一些数字，其实这些从能力上来说是YES，但是为什么说是 NO 呢？下面一个一个解释：
   数字1：启动 Activity 在这些类中是可以的，但是需要创建一个新的task。一般情况不推荐。
   数字2：在这些类中去 layout inflate 是合法的，但是会使用系统默认的主题样式，如果你自定义了某些样式可能不会被使用。
   数字3：在 receiver 为 null 时允许，在 4.2 或以上的版本中，用于获取黏性广播的当前值（可以无视）。
   注：ContentProvider、BroadcastReceiver 之所以在上述表格中，是因为在其内部方法中都有一个context用于使用。
6. 总结：
   凡是跟 UI 相关的，都应该使用 Activity 做为 Context 来处理；其他的一些操作，Service、Activity、Application 等实例都可以
7. 源码注释：
   ```Interface to global information about an application environment.This is an abstract class whose implementation is provided by the Android system.It allows access to application-specific resources and classes,as well as up-calls for application-level operationssuch as launching activities, broadcasting and receiving intents, etc.```
8. 获取 Context
* view.getContext()
* getApplicationContext()
* Activity.this
9. getApplication() 和 getApplicationContext()是同一个对象
   getApplicationContext() 方法的作用域会更广一些，任何一个 Context 的实例，只要调用 getApplicationContext\(\) 方法都可以拿到我们的Application对象。
10. Application 方法执行的顺序
* 构造方法 -&gt; attachBaseContext\(\) -&gt; onCreate\(\)* ContextWrapper 源码分析：
* attachBaseContext 这个方法会将传入的一个 Context 参数赋值给mBase对象* 之后mBase对象就有值了，所有 Context 的方法都是调用这个mBase 对象的同名方法