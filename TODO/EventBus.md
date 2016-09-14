#EventBus Documentation
##How-Tos
[开始使用EventBus](https://github.com/ssyijiu/Android-ReadingNotes/blob/master/EventBus-Doc/%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8EventBus.md)




- Event事件，用于区分事件和传输数据。
- 在Activity/Fragment的onStart()、onStop()方法中分别注册和反注册EventBus
    ```
        @Override
        public void onStart() {
            EventBus.getDefault().register(this);
            super.onStart();
        }
        @Override
        public void onStop() {
            EventBus.getDefault().unregister(this);
            super.onStop();
        }
    
    ```