#Glide
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
阅读文章：  http://www.jianshu.com/p/e26130a93289

http://wuxiaolong.me/2016/09/07/MyAndroidLibrary/

http://mrfu.me/2016/02/27/Glide_Advanced_Loading/
完成时间：2016/9/18  
###1. 踩坑
- 加载 GIF - Glide加载 GIF 真的很糟糕，经常出现各种加载不出来，如果对 GIF 进行剪裁就更糟糕了。
    加上 `diskCacheStrategy(DiskCacheStrategy.SOURCE) ` 和 `dontAnimate()` 试试吧。

    ```
    Glide.with(this).load(gifUrl)
    	 .diskCacheStrategy(DiskCacheStrategy.SOURCE)
    	 .bitmapTransform(...)
    	 .dontAnimate()
    	 .into(imageView);
    ```

###2. 二次封装
```
public class GlideLoader {
	
	public static RequestManager with(Context context) {
		return Glide.with(context);
	}
	
	public static RequestManager with(Activity activity) {
		return Glide.with(activity);
	}
	
	public static RequestManager with(Fragment fragment) {
		return Glide.with(fragment);
	}
	
	public static void loadImage(Context context, String url, ImageView imageView) {
		with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
	}
	
	public static void loadImage(Activity activity, String url, ImageView imageView) {
		with(activity).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
	}
	
	public static void loadImage(Fragment fragment, String url, ImageView imageView) {
		with(fragment).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
	}
}
```
## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)