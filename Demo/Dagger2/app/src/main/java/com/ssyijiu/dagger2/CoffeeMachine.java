package com.ssyijiu.dagger2;

/**
 * Created by ssyijiu on 2016/10/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * https://dreamerhome.github.io/2016/07/07/dagger/
 */

public class CoffeeMachine {
    private CoffeeMaker maker;

    public CoffeeMachine(){
        maker = new SimpleMaker();
    }

    public String makeCoffee(){
        return maker.makeCoffee();
    }
}
