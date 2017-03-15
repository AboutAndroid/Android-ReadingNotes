https://developer.android.com/guide/topics/manifest/activity-element.html

###"stateUnspecified"    
The state of the soft keyboard (whether it is hidden or visible) is not specified. The system will choose an appropriate state or rely on the setting in the theme.        
This is the default setting for the behavior of the soft keyboard.      
未指定软键盘的状态，系统将自动选择一个合适的状态或依赖于主题中的设置，这是默认的设置   

###"stateUnchanged"      
The soft keyboard is kept in whatever state it was last in, whether visible or hidden, when the activity comes to the fore.       
当这个 activity 出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示      

###"stateHidden"     
The soft keyboard is hidden when the user chooses the activity — that is, when the user affirmatively navigates forward to the activity, rather than backs into it because of leaving another activity.      
用户选择 activity 时，软键盘总是被隐藏    

###"stateAlwaysHidden"     
The soft keyboard is always hidden when the activity's main window has input focus.     
当该 activity 主窗口获取焦点时，软键盘也总是隐藏的    

###"stateVisible"      
The soft keyboard is visible when that's normally appropriate (when the user is navigating forward to the activity's main window).     
软键盘通常是可见的    

###"stateAlwaysVisible"     
The soft keyboard is made visible when the user chooses the activity — that is, when the user affirmatively navigates forward to the activity, rather than backs into it because of leaving another activity.      
用户选择 activity 时，软键盘总是显示的状态      

###"adjustUnspecified"     
It is unspecified whether the activity's main window resizes to make room for the soft keyboard, or whether the contents of the window pan to make the current focus visible on-screen. The system will automatically select one of these modes depending on whether the content of the window has any layout views that can scroll their contents. If there is such a view, the window will be resized, on the assumption that scrolling can make all of the window's contents visible within a smaller area.      
This is the default setting for the behavior of the main window.      
默认设置，通常由系统自行决定是隐藏还是显示     

###"adjustResize"      
The activity's main window is always resized to make room for the soft keyboard on screen.      
该 activity 总是调整屏幕的大小以便留出软键盘的空间      

###"adjustPan"       
The activity's main window is not resized to make room for the soft keyboard. Rather, the contents of the window are automatically panned so that the current focus is never obscured by the keyboard and users can always see what they are typing. This is generally less desirable than resizing, because the user may need to close the soft keyboard to get at and interact with obscured parts of the window.     
当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分     

一般来说，用的比较多的还是"stateHidden|adjustResize" 和 "stateHidden|adjustPan"这两对组合。   
"adjustResize" 在使用时布局会被软键盘顶上去，体验非常不好           
"adjustPan" 在使用时获取焦点的控件下边的View将会被软键盘覆盖      