# Android 6.0 运行时权限

### 一. 运行时权限概述

1. 什么时候会触发运行时权限？

   targetSdkVersion >= 23 ，运行在 Android 6.0 及以上设备上，使用[这些权限](https://developer.android.com/guide/topics/security/permissions.html#perm-groups)的时候。

2. 触发了运行时权限，没有进行适配会怎么样？

   应用崩溃。

3. 来不及适配怎么办？

   设置 targetSdkVersion < 23，会和以前一样，在应用安装申请所有的权限。值得注意的是用户依然可以在设置里取消已授权的权限，这时候应用虽然不会崩溃，但是肯定是无法使用这个权限的，而且不会给用户任何的提示。

### 二. 适配方法

1. 在 AndroidManifest.xml 添加权限声明。

2. 使用 checkSelfPermission 检查某个权限是否被授予。

3. 未授权使用 shouldShowRequestPermissionRationale

   判断我们是否要给用户解释一下。

   - 如果用户拒绝过我们的权限申请，shouldShowRequestPermissionRationale 会返回 true。此时我们最好弹出一个对话框告诉用户，你拒绝过我的权限申请，我申请这个权限是做什么用的，希望你能同意等等。
   - shouldShowRequestPermissionRationale 会返回 true 的时候，我们再次申请权限，会有一个 "不再提醒" 的 checkBox ，当用户勾选上是，我们再次调用 shouldShowRequestPermissionRationale 会返回 false，意思说用户都不想看到了，就没有必要再解释了。
   - 总结：shouldShowRequestPermissionRationale 返回 false，有两种可能，一是我们第一次申请权限的时候，二是用户选择了 "不再提醒"；shouldShowRequestPermissionRationale 返回 true 是用户拒绝过我们的权限申请但是没有勾选 "不再提醒"
   - "不再提醒" 的处理：onRequestPermissionsResult 在用户拒绝权限后使用 shouldShowRequestPermissionRationale 判断是否需要解释，如果为 false，可以知道此时用户勾选了 "不再提醒"。记录下这个状态，下次申请的时候判断一下，如果是 "不再提醒" 引导用户去设置页面授权。

4. 不需要给用户解释使用 requestPermissions 申请权限。

   - 如果已经授权，不会弹出 Dialog，未授权会弹出 Dialog，可以同时请求多个权限。
   - 无论是否授权，都会走 onRequestPermissionsResult 的回调。

5. 使用 onRequestPermissionsResult 处理权限申请回调。

   ```java
   @Override
   public void onRequestPermissionsResult(int requestCode,
                                              @NonNull String[] permissions,
                                              @NonNull int[] grantResults) {
     		// requestCode：requestPermissions 的参数，请求码
     		// permissions：请求授权的权限
     		// grantResults：授权结果，PERMISSION_GRANTED 同意，PERMISSION_DENIED 拒绝
           
       }
   ```

   ​



### Thanks

[Android M 新的运行时权限开发者需要知道的一切](http://jijiaxin89.com/2015/08/30/Android-s-Runtime-Permission/)

[Android 6.0 运行时权限处理完全解析](http://blog.csdn.net/lmj623565791/article/details/50709663)

