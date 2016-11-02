#Dagger2
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
完成时间：2016/9/18  

###目标类：
使用@Inject注解依赖类
###依赖类：  
@inject构造函数，或者 @Modules + @Provides（第三方库）
###使用 @Component 将依赖类注入到目标类中。  
Component会查找目标类中用Inject注解标注的属性，查找到相应的属性后会接着查找该属性对应的用Inject标注的构造函数，剩下的工作就是初始化该属性的实例并把实例进行赋值    
###Singleton   
1. 在Component用@Singleton标注   
2. 使用@inject在类声明时标注@Singleton，或者使用@Module在@Provides后用@Singleton标注

###Scope 在作用域内保持单例
@Singleton只是默认实现了@Scope
```
@Scope
@Documented
@Retention(RUNTIME)
public @interface Singleton {}
```
###dependencies 依赖
要依赖其他的Module的Provides，在Component声明时使用dependencies依赖[其他Module的Component]，并且在[其他Module的Component]中还要声明一个方法将你要依赖的返回。  
代码：
```
DaggerComponent.builder()
                .其他Component(参数)
                .自己的Module(参数)
                .build().inject(this);
```

###问题
- [x] 构造函数带参数怎么解决 ：参数要在Module中Provides出来
- [x] Module中Provides时需要参数怎么办?在Module中声明成员变量，在Module的构造函数中传进来。


## Thanks
[Android：dagger2让你爱不释手-基础依赖注入框架篇](http://www.jianshu.com/p/cd2c1c9f68d4)  
https://dreamerhome.github.io/2016/07/07/dagger/
https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2#declare-your-singletons
http://www.jianshu.com/p/01d3c014b0b1
## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)