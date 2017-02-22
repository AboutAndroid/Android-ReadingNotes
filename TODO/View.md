#View 
##1. inflate
```
public View inflate(int resource, ViewGroup root, boolean attachToRoot)
```
1. 如果 root 为 null，attachToRoot 将失去作用，设置任何值都没有意义， 此时 layout_width layout_height 失去作用，View 的宽高和 new 出来一样。   
2. 如果 root 不为 null，attachToRoot 设为 true，则会给加载的布局文件的指定一个父布局，即 root。   
3. 如果 root 不为 null，attachToRoot 设为 false，则会将布局文件最外层的所有 layout 属性进行设置，当该 view 被添加到父 view 当中时，这些 layout 属性会自动生效。    
4. 在不设置 attachToRoot 参数的情况下，如果 root 不为 null，attachToRoot 参数默认为 true。   

##2. View 的坐标系
O-------------------------x
|
|
|
|
|
y
```
getTop();       // 获取子View左上角距父View顶部的距离
getLeft();      // 获取子View左上角距父View左侧的距离
getBottom();    // 获取子View右下角距父View顶部的距离
getRight();     // 获取子View右下角距父View左侧的距离
```

MotionEvent中 get 和 getRaw 的区别
```
event.getX();       // 触摸点相对于其所在 View 的坐标
event.getY();

event.getRawX();    // 触摸点相对于屏幕默认坐标系的坐标
event.getRawY();
```

##3. Android 中的颜色模式
- ARGB8888：四通道 32 位
- ARGB4444：四通道 16 位
- RGB565：三通道 16 位 **Android 屏幕默认的颜色模式**
- Alpha8：透明通道 8 位     

其中字母表示通道类型，数值表示该类型用多少位二进制来描述。如 ARGB8888 则表示有四个通道 (ARGB)，每个对应的通道均用 8 位来描述。     
其中 A R G B 的取值范围均为 0~255 (即 16 进制的 0x00~0xff)      
- A 从 ox00 到 oxf f表示从透明到不透明。
- RGB 从 0x00 到 0xff 表示颜色从浅到深。   

## 4. View 的绘制流程   

1. 构造函数

   ```java
   public ImageView(Context context) {
           super(context);
           initImageView();
       }

       public ImageView(Context context, @Nullable AttributeSet attrs) {
           this(context, attrs, 0);
       }

       public ImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
           this(context, attrs, defStyleAttr, 0);
       }

       public ImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
               int defStyleRes) {
                 initImageView();
       }
   ```

   一个参数的构造在 new ImageView(content) 的时候调用，两个参数的构造在 xml 中使用的时候调用。   

2. onMeasure

##Thanks

- [http://www.gcssloop.com/customview/CustomViewIndex](http://www.gcssloop.com/customview/CustomViewIndex)
- [http://blog.csdn.net/guolin_blog/article/details/12921889](http://blog.csdn.net/guolin_blog/article/details/12921889)
