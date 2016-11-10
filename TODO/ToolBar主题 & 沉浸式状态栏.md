#ToolBar主题 & 沉浸式状态栏
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
阅读文章：  http://stormzhang.com/android/2015/08/16/boohee-toolbar/
http://www.jianshu.com/p/0acc12c29c1b
http://www.jianshu.com/p/bae25b5eb867
完成时间：2016/11/10
##Toolbar
Toolbar的使用看 [这里](https://zhuanlan.zhihu.com/p/22842375)
Toolbar主题
android:theme="@style/AppTheme.AppBarOverlay"
```html
<style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar">
    <item name="colorControlNormal">@color/red</item>
    <item name="actionMenuTextColor">@color/black</item>
</style>
```
继承 AppTheme 和 ThemeOverlay.AppCompat.Dark.ActionBar
- < item name="colorControlNormal">@color/red</item>  // 溢出按钮颜色,包括 navigationIcon
- < item name="actionMenuTextColor">@color/black</item>  // 溢出文字颜色
溢出菜单主题  
app:popupTheme="@style/AppTheme.PopupOverlay"  
```html
<style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light">
    <item name="android:textSize">15sp</item>    
    <item name="android:textColorPrimary">@color/white</item>
</style>
```
继承 AppTheme 和 ThemeOverlay.AppCompat.Light
- < item name="android:textSize">15sp</item> // 设置文字大小
- < item name="android:textColorPrimary">@color/red</item> // 设置文字颜色
- < item name="android:colorBackground">@color/red</item> // 设置背景颜色
其他属性可以去 ThemeOverlay.AppCompat.Light 这个主题下面查找
  
## 5.0 沉浸式
###1. 设置Toolbar背景 
android:background="?attr/colorPrimary"
###2. 主题设置
style.xml
```html
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>

<style name="AppTheme.NoActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
</style>
```
values-v21/style.xml
```html
<style name="AppTheme.NoActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="android:windowDrawsSystemBarBackgrounds">true</item>
    <item name="android:statusBarColor">@android:color/transparent</item>
</style>
```
在 AppTheme 中设置主题的基本颜色，< style name="AppTheme.NoActionBar">  实际上是继承 AppTheme 和 < style name="NoActionBar" parent="AppTheme"> 效果相同。  
- < item name="windowActionBar">false</item>  // 不使用 ActionBar
- < item name="windowNoTitle">true</item>       // 无标题栏  
在 values-v21/style.xml 还有两条属性
- < item name="android:windowDrawsSystemBarBackgrounds">true</item> // 允许设置状态栏的背景颜色
- < item name="android:statusBarColor">@android:color/transparent</item> // 设置状态栏的背景颜色，这个属性写 ?colorPrimaryDark 的话 DrawerLayout 不再有沉浸式效果，@android:color/transparent 主页面和 DrawerLayout 都有沉浸式效果。
## 4.4 沉浸式

## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)