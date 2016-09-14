#标题
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
阅读文章：无

##1. Activity之间传递数据
> 未完成

情形：列表页 —> 详情页 —> 其他页面 —> 返回详情页
列表页—>详情页：需要传递detailed_id
```java
Intent intent = new Intent(this,DetailedActivity.class);
intent.putExtra("detailed_id", detailed_id);
startActivity(intent);
finish();  // 你肯定会好奇这里为什么会finish,往下看
```
详情页：根据detailed_id请求网络数据
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(...);
	   initData();
}

public void initData() {
        mIntent = getIntent(); 
		int detailed_id = mIntent.getStringExtra("detailed_id");
	    RequestBody body = new FormBody.Builder()
                        .add("detailed_id","detailed_id")
                        .build();
        HttpUtil.post(url, body,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 将数据展示到页面上
            }
        });

}
```
##2. Activity之间传递数据

## 联系作者
- Github: [ssyijiu](https://github.com/ssyijiu)
- E-mail: lxmyijiu@163.com
- WeChat: ssyijiu11

##License

```
Copyright 2016 ssyijiu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.



