# 避免布局嵌套，试试 ConstraintLayout 



本文来自：[书生依旧](https://github.com/ssyijiu) 的 [Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)
原文：[Build a Responsive UI with ConstraintLayout](https://developer.android.com/training/constraint-layout/index.html#constraints-overview)

ConstraintLayout 和 RelativeLayout 有点类似，都是通过协调 View 与其兄弟 View 或者父 View 的相对关系来布局，不过相比 RelativeLayout，ConstraintLayout 更加的灵活，同时可以非常有效的解决复杂 UI 界面 ViewGroup 嵌套过多的问题。更重要的是 ConstraintLayout 非常适合用 Android Studio 可视化工具来进行布局。

ConstraintLayout 最低可以兼容到 Android 2.3 版本，谷歌在 Github 上放了个 [示例](https://github.com/googlesamples/android-ConstraintLayoutExamples) 用来介绍 ConstraintLayout 的使用。

### 1. 什么是约束

往 ConstraintLayout 中添加 View 时，你至少要给这个 View 添加一个水平约束和一个竖直约束，每个约束相当于一个和其他 View 或者父 View或者 Guideline 的联系。每个约束规定了 View 在水平方向或者竖直方向的位置，所以每个 View 至少要在水平方向和竖直方向各有一个约束。

当我们拖拽一个 View 到视图上的时候，这个 View 默认是没有任何约束的，View 的位置只是为了方便我们编辑，直接运行的话这个 View 会显示在 (0,0) 的位置（左上角）。




## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)