package com.ssyijiu.dagger2_1.coffee;


// 电加热器
class ElectricHeater implements Heater {
  boolean heating;

  @Override
  public void on() {
    System.out.println("~ ~ ~ heating ~ ~ ~");
    this.heating = true;
  }

  @Override
  public void off() {
    this.heating = false;
    System.out.println("~ ~ ~ heating off ~ ~ ~");
  }

  @Override public boolean isHot() {
    return heating;
  }
}
