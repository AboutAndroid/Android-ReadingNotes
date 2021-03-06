#Glide and Picasso 
本文来自：[书生依旧](https://github.com/ssyijiu) [Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)   
阅读文章：[Introduction to Glide, Image Loader Library for Android, recommended by Google](https://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en)  
完成时间：2016/9/13   
> Glide vs Picasso , 如何选择?

##Import to project
- Glide
    ```
    dependencies {
        compile 'com.github.bumptech.glide:glide:3.7.0'
        compile 'com.android.support:support-v4:24.4.1'
    }
    ```

- Picasso
    ```
    dependencies {
        compile 'com.squareup.picasso:picasso:2.5.2'
    }
    ```
Glide 需要 Android Support Library v4，Picasso 则不需要。 

##Basic
Glide 的 with 参数可以接受 Activity/Fragment，并且图片加载会和 Activity/Fragment 的生命周期保持一致，例如：在 Paused 状态暂停加载图片，在 Resumed 的时候又自动重新加载图片。

##Default Bitmap Format is RGB_565
Glide 默认的 Bitmap 格式是 RGB_565，Picasso 是 ARGB_8888，因此**默认情况下**，Glide 比 Picasso 加载的图片质量略差，但是内存节约了50%左右。

修改 Glide 默认的 Bitmap 格式为 ARGB_8888:  
```
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }
    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
```
AndroidManifest.xml

```
<meta-data android:name="com.inthecheesefactory.lab.glidepicasso.GlideConfiguration"
           android:value="GlideModule"/>
```
修改 Glide 的 Bitmap 格式为 ARGB_8888 后，两者加载图片的质量相差无几，但是 Glide 消耗的内存仍比 Picasso 小的多。  

原因：Picasso会加载原图片(1920x1080 pixels)到内存，而 Glide 加载的是当前 ImageView 的 size(768x432 pixels) 到内存。  
使用 Picasso 加载当前 ImageView 的 size 到内存:   
```
Picasso.with(this)
    .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
    .resize(768, 432)   // 这里需要自己计算ImageView的尺寸
    .into(ivImgPicasso);

// 或者在布局中将 ImageView 的 layout_width 和 layout_height 写死 dp，然后：
Picasso.with(this)
    .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
    .fit()
    .centerCrop()
    .into(ivImgPicasso);
```
现在两者消耗的内存相差无几，但是相对而言 Glide 更方便一些，因为它可以自动计算出任意情况下的 ImageView 大小。  

##Image's quality in details
Picasso 加载的图片比 Glide 更平滑一些。
##Disk Caching
Picasso will cache only single size of image, the full-size one. Glide acts differently, caches separate file for each size of ImageView. Although an image has already been loaded once but if you need to load another size the same image, it needs to be downloaded once again before be resized to the right resolution and then be cached.  

简单点来说，就是 Picasso 只缓存一次原图，Glide 会根据当前 ImageView 的大小不同来缓存多次，每次缓存 ImageView size 大小的图片。尽管一张图片已经被缓存了，如果你要需要以不同的尺寸加载这张图片，Glide 需要重新下载，调整成新尺寸的大小，然后将这个尺寸的也缓存起来。

让 Glide 同时缓存原图和 ImageView 大小的图片  
```
Glide.with(this)
     .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
     .diskCacheStrategy(DiskCacheStrategy.ALL)
     .into(ivImgGlide);
```
这样，下次加载图片的时候，Glide 会将原图将从缓存中取出，重新调整大小，显示，然后缓存。  
相比 Picasso 在每次显示之前都要重新调整图片大小而言，Glide 的这种缓存方式会让图片加载速度也会大大提升，当然也会耗费更多的磁盘空间。  

##What that Glide has but Picasso doesn't
- Glide 可以加载 gif 图片（很耗内存），Picasso 不可以。
- Glide 也可以加载本地 video  略缩图到 ImageView。
- Glide 可以通过 R.animator 来配置图片显示动画，而 Picasso 只有一种动画：淡入（fading in）。
- generate a thumbnail file of an image you loaded with thumbnail()  ( Glide 可以使用 thumbnail() 为你要加载的图片生成一张缩略图)

##Library's size
- Picasso (v2.5.1) : 118KB
- Glide (v3.5.2) : 430KB

##Method count 
- Picasso (v2.5.1) : 480 
- Glide (v3.5.2) : 2678（对于dex文件65535个方法的限制来说，2678是一个相当大的数字了，建议在使用Glide的时候开启ProGuard）  

##总结
比较项目 | 选择结果
--- | --- | ---
图片质量 | Picasso(两者差距几乎可以忽略)
加载速度 | Glide
与Activity/Fragment的生命周期一致 | Glide
库的大小 | Picasso
方法数 | Picasso
磁盘缓存消耗空间 | Picasso
gif | Glide
缩略图 | Glide
图片显示动画 | Glide

相对而言，Picasso 更轻一些，但是它的图片加载速度和加载 gif 动画等其他功能没有 Glide 强大。  
在使用 Glide 的使用建议修改 Bitmap 的格式为 ARGB_8888 并同时缓存原图和 ImageView size 大小的图片。

## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)
