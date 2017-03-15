#RecyclerView & ScrollView 如何选择？  
> 不得不说，使用 RecyclerView 配合一些多类型 Item 的库，完全可以实现ScrollView 的效果，但是滥用 RecyclerView 会出现很多其他的问题。
    
###什么情况适合使用 RecyclerView ?      
- 有大量相同类型的 Item 的时候。RecyclerView 原本也就是这样设计的
- UI 根据接口可以分割成不同类型的 Item，接口之间没有嵌套关系。
    - PS1：有些 Item UI 中有多个接口，这时可以用 zip 组合成一个，但是一个 Item UI 中一定不要有嵌套接口
    - PS2：实际上多类型 Item RecyclerView 处理多个接口的界面并不好，接口是并发的，而 RecyclerView 的 Item 是有序的，这个时候要么用 zip 组合接口，要么设置默认的 Item 占位，如果又出现的 PSI 的情况，还是乖乖用 ScrollView 吧，没有必要把问题复杂化。 
- 需要 ScrollView 嵌套 RecyclerView 的时候。强烈建议使用 RecyclerView ，虽然多接口会很麻烦，但是也比嵌套好。

###什么情况适合使用 ScrollView ?      
不适合 RecyclerView 的时候