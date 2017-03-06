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
getTop();       // 获取 子View 左上角距 父View 顶部的距离
getLeft();      // 获取 子View 左上角距 父View 左侧的距离
getBottom();    // 获取 子View 右下角距 父View 顶部的距离
getRight();     // 获取 子View 右下角距 父View 左侧的距离
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

2. onFinishInflate (自定义 ViewGroup 会用到)

   ```java
   Finalize inflating a view from XML.  This is called as the last phase of inflation, after all child views have been added.
     
   // 这个方法在 onMeasure 之前调用，当布局填充完（当前View的结束标签解析完）执行，执行的时候知道自己有几个子 View 了，一般可以在此方法中获取子 View，这个方法执行的时候子View没有完成测量 宽高都是0
   ```

3. onMeasure

   ```java
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
     	// 获取宽度的测量大小
       int widthsize = MeasureSpec.getSize(widthMeasureSpec);  
     	// 获取宽度的测量模式
       int widthmode  MeasureSpec.getMode(widthMeasureSpec);      
       // 获取高度的测量大小
       int heightsize =  MeasureSpec.getSize(heightMeasureSpec);    
     	// 获取高度的测量模式
       int heightmode =  MeasureSpec.getMode(heightMeasureSpec);    
   }
   ```

   **测量模式一共有三种， 被定义在 Android 中的 View 类的一个内部类View.MeasureSpec中：**

   - UNSPECIFIED：默认值，父控件没有给子view任何限制，子View可以设置为任意大小。
   - EXACTLY：表示父控件已经确切的指定了子View的大小。
   - AT_MOST：表示子View具体大小没有尺寸限制，但是存在上限，上限一般为父View大小。

   **如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数**

4. onSizeChanged

   ```java
   // 这个函数在 View 大小发生改变时调用。
   @Override
   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
       super.onSizeChanged(w, h, oldw, oldh);
     	// w h 是 View 的最终大小
   }
   ```

   **Q: 在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了，那么为什么还要再次确定View的大小呢？**

   **A: 这是因为View的大小不仅由View本身控制，而且受父控件的影响，所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。**

5. onLayout  (自定义 ViewGroup 会用到)

   **确定布局的函数是 onLayout，它用于确定子 View 的位置，在自定义ViewGroup 中会用到，他调用的是子 View 的 layout 函数。**

   在自定义ViewGroup中，onLayout一般是循环取出子View，然后经过计算得出各个子View位置的坐标值，然后用以下函数设置子View位置。

   ```java
   // RelativeLayout onLayout 源码
   @Override
   protected void onLayout(boolean changed, int l, int t, int r, int b) {
           final int count = getChildCount();

           for (int i = 0; i < count; i++) {
               View child = getChildAt(i);
               if (child.getVisibility() != GONE) {
                   RelativeLayout.LayoutParams st =
                           (RelativeLayout.LayoutParams) child.getLayoutParams();
                   child.layout(st.mLeft, st.mTop, st.mRight, st.mBottom);
               }
           }
       }
   ```

   layout 函数的 4 个参数

   - l：View 左侧距 父View 左侧的距离 - getLeft();
   - t：View 顶部距 父View 顶部的距离 - getTop();
   - r：View 右侧距 父View 左侧的距离 - getRight();
   - b：View 底部距 父View 顶部的距离 - getBottom();

6. onDraw

   onDraw是实际绘制的部分，也就是我们真正关心的部分，使用的是Canvas绘图。

   ```java
   @Override
   protected void onDraw(Canvas canvas) {
       super.onDraw(canvas);
   }
   ```

##Thanks

- [http://www.gcssloop.com/customview/CustomViewIndex](http://www.gcssloop.com/customview/CustomViewIndex)
- [http://blog.csdn.net/guolin_blog/article/details/12921889](http://blog.csdn.net/guolin_blog/article/details/12921889)
