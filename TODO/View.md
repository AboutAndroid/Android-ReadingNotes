#View 
##1. inflate
```
public View inflate(int resource, ViewGroup root, boolean attachToRoot)
```
1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义， 此时 layout_width layout_height 失去作用，View 的宽高和 new 出来一样   
2. 如果root不为null，attachToRoot设为true，则会给加载的布局文件的指定一个父布局，即root。   
3. 如果root不为null，attachToRoot设为false，则会将布局文件最外层的所有layout属性进行设置，当该view被添加到父view当中时，这些layout属性会自动生效。    
4. 在不设置attachToRoot参数的情况下，如果root不为null，attachToRoot参数默认为true。