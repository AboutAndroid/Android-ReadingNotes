package com.ssyijiu.dagger2_1.coffee;


// 加热器接口
interface Heater {
  void on();   // 打开加热器
  void off();  // 关闭加热器
  boolean isHot();    // 是否加热好了
}
