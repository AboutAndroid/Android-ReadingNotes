#SharedPreference
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
完成时间：2016/10/18  
1. commit 和 apply 的区别：  
    commit 返回一个 boolean 来表明修改是否提交成功， apply 没有返回值；commit 是将数据写入内存后同步提交到磁盘(不要在主线程中 commit )，而 apply 将数据写入内存后会在一个新线程中异步提交到磁盘，在不要求提交结果的情况下应使用 apply。

2. 批量操作：  
    当我们一次有多个修改写操作时，不要多次 edit 和 apply，尽量批量修改一次提交

3. 第一次加载 & 存储大的 key value：  
    sp 底层是以xml方式进行数据存储的，在第一次使用的时候会加载整个文件加载进入内存，接下来的读操作都是内存缓存操作而不是文件操作。sp 在加载的时候确实是异步加载，但是在 sp 文件加载未完成时 `getString()` `setString()` 等方法是阻塞等待的。所以不要在 sp 存放大的 key  和 value ，会拖慢第一次加载时的速度，引起界面卡顿；同时解析 sp(xml的解析) 还会产生很多临时对象导致频繁GC；并且这些 key 和 value 将永远存在于内存之中，占用大量内存。

4. 存储 json or html ?  
    这么做不是不可以，但是，如果这个 json 相对较大，那么也会引起sp读取速度的急剧下降。json 或者 html 格式存放在sp里面的时候，需要转义，这样会带来很多 & 这种特殊符号， sp 在解析碰到这个特殊符号的时候会进行特殊的处理，引发额外的字符串拼接以及函数调用开销。

5. sSharedPrefsCache  
    ContextImpl 有一个 static 的 ArrayMap 变量 `sSharedPrefsCache`，`sSharedPrefsCache` 保存了你应用中所有的 sp ，`sSharedPrefsCache` 在应用启动以后首次使用 SharePreference 时创建，系统结束时才可能会被垃圾回收器回收，在这个过程中你程序中使用到的那些个 sp 将永远呆在内存中，如果 App 中频繁的使用不同文件名的 SharedPreferences 这让这个 Map 变得很大，占用大量内存。

6. 一个应用只使用一个 sp ?  
    别闹了，要知道 sp 在第一次加载的时候会把整个文件加载进内存，文件越大读取速度越慢。  
    正确的做法：  
   - 将 key 按照使用是否频繁分类，经常使用的放在一个 sp ，几乎不用的放在另一个 sp。
   - 将相关性高、经常一起使用的 key 放在一个 sp。
   - 其实就是一个内存与速度的平衡关系，建议偏重速度。

7. 多进程：  
    sp 不支持多进程，即使你使用了 `MODE_MULTI_PROCESS` 也是不行的。

8. [SPUtil](https://github.com/ssyijiu/android-helper/blob/master/utils/SPUtil.java)

##Thanks
[SharedPreference在使用过程中有什么注意点？](https://github.com/ZhaoKaiQiang/AndroidDifficultAnalysis/blob/master/09.SharedPreference%E5%9C%A8%E4%BD%BF%E7%94%A8%E8%BF%87%E7%A8%8B%E4%B8%AD%E6%9C%89%E4%BB%80%E4%B9%88%E6%B3%A8%E6%84%8F%E7%82%B9%EF%BC%9F.md)    
[请不要滥用SharedPreference](http://weishu.me/2016/10/13/sharedpreference-advices/)  
[Android应用Preference相关及源码浅析(SharePreferences篇)](http://blog.csdn.net/yanbober/article/details/47866369)

## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)