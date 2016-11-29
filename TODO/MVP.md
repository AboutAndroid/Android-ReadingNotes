#MVP心路
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货。     
阅读文章：  
[Android：你是如何把Activity写的如此“万能”的](http://www.jianshu.com/p/37892b4193a7)  
[Android:“万能”Activity重构篇](http://www.jianshu.com/p/559f85a42f23)  
##万能Activity
###一. 面对“万能”的Activity，我选择go die
1、一个LoginActivity做了些什么
- 处理UI
- 验证用户名密码的合法性
- 数据的解析与存储
2、“万能”的Activity的缺点
- 变化因素很多，动不动就要修改，bug出现几率增加
- 业务功能没有办法复用，在别的地方验证用户名和密码还的Copy一份代码过去啊！
- 代码很长，阅读性差，耦合度高，难以维护

###二. 写在MVP前面的话
不管是mvc，mvp还是mvvm始终都在做一件事情：怎么样能更好的解决数据与界面之间的关系，以达到数据与界面之间的耦合更低，代码的复用性更高，代码的可测性更好。**处理好界面与数据之间的关系才是我们需要的，不要被模式模式化。**
###三.model：数据中心
数据的解析、数据的存储、数据的分发、数据的增删改查等操作都在。意思就是凡是涉及到数据操作的代码都是写在model中的。model的作用如下：
- 从网络，数据库，文件，传感器，第三方等数据源读写数据。
- 对外部的数据类型进行解析转换为APP内部数据交由上层处理。
- 对数据的临时存储,管理，协调上层数据请求上层。
tip: model中的所有操作都发生于子线程。
###四.presenter：主持人
把view交给自己的命令进行一定的校验等操作交给model处理，把model的处理结果返回给view进行显示。

mvp中的数据流向：view->presenter->model->presenter->view，presenter的功能显而易见：
- 把数据交给model处理
- 根据数据处理结果刷新view
    - 封装业务逻辑，与界面展示和数据无关的代码全部写在presenter中。
    - 刷新view，根据model返回的数据来通知view要显示什么界面。（成功or失败or other）
    - 从model获取数据是时，presenter为子线程，刷新view时为UI线程。
    - presenter从model获取的数据就是解析好的数据。
##MVP框架分析
###androidmvp

##联系作者
Email：ssyijiu@126.com   
微信：SSyijiu11

- https://drakeet.me/mvp-and-thinking-in-android
- http://blog.csdn.net/sbsujjbcy/article/details/50707696  厂长最佳实践
- http://www.linuxeden.com/html/news/20160429/165924.html
- http://www.jianshu.com/p/2ecfc1f4561d
- http://www.open-open.com/lib/view/open1462625604894.html
- https://mp.weixin.qq.com/s?__biz=MzA3ODg4MDk0Ng==&mid=402868193&idx=1&sn=790e12f84dfcea171528e6d3789c69ed&scene=1&srcid=0318edkkVeWbjOhcNH6WDTZQ&key=710a5d99946419d971dd3aa71f3cb0b2e509051adcdbc9164c1787ffeeaf37389747b35a2c1c899c3ec825d8bc190ec1&ascene=0&uin=Mjc3OTU3Nzk1&devicetype=iMac+MacBookPro9%2C2+OSX+OSX+10.10.3+build%2814D136%29&version=11020201&pass_ticket=e3qL7YcbmknxduKwWiyzQxJoeiIW7hRFdqBaO206p868fDQqQ7UIiIsPe%2FiSY23E
- https://github.com/googlesamples/android-architecture
- http://gold.xitu.io/entry/574b7ec52e958a005eed0788
- http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652261695&idx=1&sn=ab5fc0468b9bbd6a7777830981a61356&scene=21#wechat_redirect
- http://blog.csdn.net/u014099894/article/details/51388170
- http://blog.csdn.net/u011459799/article/details/51360882
- https://www.zybuluo.com/shark0017/note/256112
http://qlm.pw/2016/10/01/%E5%A6%82%E4%BD%95%E5%B0%86%E5%8E%9F%E9%A1%B9%E7%9B%AE%E9%87%8D%E6%9E%84%E6%88%90mvp%E6%A8%A1%E5%BC%8F/
