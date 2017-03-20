# Android 6.0 运行时权限

### 一. 运行时权限概述

1. 什么时候会触发运行时权限？

   targetSdkVersion >= 23 ，运行在 Android 6.0 及以上设备上，使用 [危险权限](https://developer.android.com/guide/topics/security/permissions.html#perm-groups) 的时候。

2. 触发了运行时权限，没有进行适配会怎么样？

   应用崩溃。

3. 来不及适配怎么办？

   设置 targetSdkVersion < 23，会和以前一样，在应用安装申请所有的权限。值得注意的是用户依然可以在设置里取消已授权的权限，这时候应用虽然不会崩溃，但是肯定是无法使用这个权限的，而且不会给用户任何的提示。

###二. 危险权限有哪些
危险权限共 9 组 24 个，每组有一个权限申请成功则该组其他权限默认申请成功。

- CALENDAR	 
    - READ_CALENDAR    
    - WRITE_CALENDAR    

    - CAMERA
    - CAMERA   

    - CONTACTS
    - READ_CONTACTS
    - WRITE_CONTACTS
    - GET_ACCOUNTS   

    - LOCATION
    - ACCESS_FINE_LOCATION
    - ACCESS_COARSE_LOCATION   

    - MICROPHONE
    - RECORD_AUDIO   

    - PHONE
    - READ_PHONE_STATE
    - CALL_PHONE
    - READ_CALL_LOG
    - WRITE_CALL_LOG
    - ADD_VOICEMAIL
    - USE_SIP
    - PROCESS_OUTGOING_CALLS   

    - SENSORS
    - BODY_SENSORS   

    - SMS
    - SEND_SMS
    - RECEIVE_SMS
    - READ_SMS
    - RECEIVE_WAP_PUSH
    - RECEIVE_MMS   

    - STORAGE
    - READ_EXTERNAL_STORAGE
    - WRITE_EXTERNAL_STORAGE    

注意：READ_PHONE_STATE、READ_EXTERNAL_STORAGE、WRITE_EXTERNAL_STORAGE 几乎是必须的，放在启动页申请，用户拒绝后引导至设置页面。

### 三. API 详解

1. ```java
   // 检查某个权限是否已授权
   ActivityCompat.checkSelfPermission(Context context, String permission)  
   // permission：要检查的权限
   // 返回值是 int 类型，PackageManager#PERMISSION_GRANTED 表示以有权限，PackageManager#PERMISSION_DENIED 表示没有权限
   ```

2. ```java
   // 申请权限
   ActivityCompat.requestPermissions(final @NonNull Activity activity, final @NonNull String[] permissions, final @IntRange(from = 0) int requesPackageManager#PERMISSION_GRANTEDtCode)
   // permissions：要申请的权限，可以一次申请多个
   // requestCode：请求码，在申请权限的回调中用到
   ```

   - 调用这个方法必然会走 onRequestPermissionsResult 的回调。

3. ```java
   // 申请权限的回调，在 Activity 和 Fragment 中都有
   onRequestPermissionsResult(int requestCode,
                                              @NonNull String[] permissions,
                                              @NonNull int[] grantResults)
   // requestCode：requestPermissions 的参数，请求码
   // permissions：请求授权的权限
   // grantResults：授权结果，PERMISSION_GRANTED 同意，PERMISSION_DENIED 拒绝
   ```

4. ```java
   // 	对于某个我们是否要给用户解释一下
   ActivityCompat.shouldShowRequestPermissionRationale(@NonNull Activity activity,
               @NonNull String permission)
   // permission：要解释的权限
   ```

   - 如果用户拒绝过我们的权限申请，shouldShowRequestPermissionRationale 会返回 true。此时我们最好弹出一个对话框告诉用户，你拒绝过我的权限申请，我申请这个权限是做什么用的，希望你能同意等等。
   - shouldShowRequestPermissionRationale 会返回 true 的时候，我们再次申请权限，会有一个 "不再提醒" 的 checkBox ，当用户勾选上时，我们再次调用 shouldShowRequestPermissionRationale 会返回 false，意思说用户都不想看到了，就没有必要再解释了。
   - 注意：用户选择 "不再提醒" 后，再次 requestPermissions 总是会失败，但是会走 onRequestPermissionsResult 的回调。
   - 总结：shouldShowRequestPermissionRationale 返回 false，有两种可能，一是我们第一次申请权限的时候，二是用户选择了 "不再提醒"；shouldShowRequestPermissionRationale 返回 true 是用户拒绝过我们的权限申请但是没有勾选 "不再提醒"


### 四. 权限适配最佳套路

1. 在 `AndroidManifest.xml` 添加权限声明。

2. 使用 `checkSelfPermission` 检查某个权限是否已经申请。

3. 权限未申请，使用 `requestPermissions` 申请权限。

4. 在 `onRequestPermissionsResult` 回调中判断权限是否申请成功。

5. 申请失败使用 `shouldShowRequestPermissionRationale` 判断用户是否勾选了 "不再提醒"。

   - 勾选了的话，弹出一个 Dialog 引导用户到设置界面授予权限。
   - 没有勾选，可以什么都不做，也可以弹出弹出一个 Dialog 引导用户到设置界面授予权限。

   ​

### Thanks

[Android M 新的运行时权限开发者需要知道的一切](http://jijiaxin89.com/2015/08/30/Android-s-Runtime-Permission/)       
[Android 6.0 运行时权限处理完全解析](http://blog.csdn.net/lmj623565791/article/details/50709663)      
[Android 6.0 RuntimePermission](http://wuxiaolong.me/2016/02/04/RuntimePermission/)

