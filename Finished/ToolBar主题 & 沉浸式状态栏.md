#ToolBar主题 & 沉浸式状态栏
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
完成时间：2016/11/11
##Toolbar
Toolbar的使用看 [这里](https://zhuanlan.zhihu.com/p/22842375)   
###Toolbar主题
android:theme="@style/AppTheme.AppBarOverlay"
```html
<style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar">
    <item name="colorControlNormal">@color/red</item>
    <item name="actionMenuTextColor">@color/black</item>
    <item name="android:windowTranslucentNavigation">false</item>
</style>
```
继承 AppTheme 和 ThemeOverlay.AppCompat.Dark.ActionBar
- < item name="colorControlNormal">@color/red</item>  // 溢出按钮颜色,包括 navigationIcon
- < item name="actionMenuTextColor">@color/black</item>  // 溢出文字颜色   
###溢出菜单主题  
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
- 其他属性可以去 ThemeOverlay.AppCompat.Light 这个主题下面查找
  
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
    <item name="android:statusBarColor">?colorPrimaryDark</item>
</style>
```
在 AppTheme 中设置主题的基本颜色，< style name="AppTheme.NoActionBar">  实际上是继承 AppTheme 和 < style name="NoActionBar" parent="AppTheme"> 效果相同。  
- < item name="windowActionBar">false</item>  // 不使用 ActionBar
- < item name="windowNoTitle">true</item>       // 无标题栏  
在 values-v21/style.xml 还有两条属性
- < item name="android:windowDrawsSystemBarBackgrounds">true</item> // 允许设置状态栏的背景颜色，默认 true
- < item name="android:statusBarColor">?colorPrimaryDark</item> // 设置状态栏的背景颜色为colorPrimaryDark **注意：5.0系统，状态栏的颜色默认是 AppTheme 主题下的colorPrimaryDark，如果没有这个属性，则是灰色**   
###3. DrawerLayout 侧拉菜单也沉浸式
- 首先在你每个页面的根布局 android:fitsSystemWindows="true"，看下官方文字的解释：If true, adjusts the padding of this view to leave space for the system windows. true 的话将会自动调整 padding 为系统窗口留出空间（布局不会侵入到状态栏上）**[官方文档是这样说的，但是我发现有时候不管用，不知道是BUG，还是使用姿势不对]**
- 修改 values-v21/style.xml，将 statusBarColor 的颜色设置为透明    

    ```html
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
        <item name="android:statusBarColor">@android:color/transparent</item>
    </style>
    ```

## 4.4 沉浸式
###1. 设置Toolbar背景 
android:background="?attr/colorPrimary"
###2. 主题设置
values-v19/style.xml
```html
<style name="AppTheme.NoActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="android:windowTranslucentStatus">true</item>
</style>
```
```
// 在代码里设置，和上面效果一样
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
}
```

把导航栏设置为透明状态，这时布局会侵入到导航栏，我使用 fitsSystemWindows 这个属性时有时候管用有时候不管用。这里采用了 [stormzhang](http://stormzhang.com/) 的方案：在Toolbar上设置一个 paddingTop 为导航栏的高度  

```html
<dimen name="status_bar_length">25dp</dimen>
```
19版本设置为 25dp，其他版本0dp。(在小米4c上不知道为什么使用了 19 版本的 dimen，一怒之下建了一个 21 版本的 dimen ，status_bar_length 设置为0，问题解决，小米4c是 Api22，这难道是就近原则？)

###3. DrawerLayout 沉浸
```
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mDrawerLayout.setFitsSystemWindows(true);
            mDrawerLayout.setClipToPadding(false);
}
```
##Finally
最后还留下一个坑：fitsSystemWindows，很多细节也没有弄明白。  
[附上代码](https://github.com/ssyijiu/Android-ReadingNotes/tree/master/Material%20Design/ToolBar)
##Thanks
[Android 状态栏着色实践](http://www.jianshu.com/p/bae25b5eb867)   
[薄荷TOOLBAR(ACTIONBAR)的适配方案](http://stormzhang.com/android/2015/08/16/boohee-toolbar/)   

## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)