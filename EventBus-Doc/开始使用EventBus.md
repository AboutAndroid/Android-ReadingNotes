##使用EventBus的3个步骤
首先添加依赖 (请使用[最新版本](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.greenrobot%22%20AND%20a%3A%22eventbus%22))
```
compile 'org.greenrobot:eventbus:3.0.0'
```
###步骤1：定义Event
Event就是普通的Java类（没有其他任何特殊的要求）
```java
public class MessageEvent {
    public final String message;
    public MessageEvent(String message) {
        this.message = message;
    }
}
```
编者注：Event用于区分事件和传输数据。
###步骤2：准备订阅者
订阅者需要实现处理事件的方法（也叫作“订阅者方法”），“订阅者方法”在事件发布的时候执行，它们在定义的时候必须使用@Subscribe这个注解来标注。在EventBus3中“订阅者方法”的方法名可以随便去（EventBus2做了规定，不能随便取）。
```java
// 在MessageEvent事件发布时，这个方法在UI线程中被调用
@Subscribe(threadMode = ThreadMode.MAIN)
public void onMessageEvent(MessageEvent event) {
    Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
}
// 在SomeOtherEvent事件发布时这个方法被调用。
@Subscribe
public void handleSomethingElse(SomeOtherEvent event) {
    doSomethingWith(event);
}
```