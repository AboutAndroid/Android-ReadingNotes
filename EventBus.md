#EventBus
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