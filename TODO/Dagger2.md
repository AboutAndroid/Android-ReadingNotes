#Dagger2
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
完成时间：2016/9/18  

- 目标类：@Inject注解依赖类
- 依赖类：@inject构造函数，构造函数带参数或第三方库使用 @Modules + @Provides
- 使用 @Component 将依赖类注入到目标类中。Component会查找目标类中用Inject注解标注的属性，查找到相应的属性后会接着查找该属性对应的用Inject标注的构造函数，剩下的工作就是初始化该属性的实例并把实例进行赋值

###问题
- 构造函数带参数怎么解决


## Thanks
[Android：dagger2让你爱不释手-基础依赖注入框架篇](http://www.jianshu.com/p/cd2c1c9f68d4)  
https://dreamerhome.github.io/2016/07/07/dagger/
https://guides.codepath.com/android/Dependency-Injection-with-Dagger-2#declare-your-singletons
http://www.jianshu.com/p/01d3c014b0b1
## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)