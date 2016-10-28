package com.ssyijiu.dagger2.component;

import com.ssyijiu.dagger2.MainActivity;
import com.ssyijiu.dagger2.OtherActivity;
import com.ssyijiu.dagger2.PoetryScope;
import com.ssyijiu.dagger2.module.MainModule;
import com.ssyijiu.dagger2.module.PoetryModule;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


/**
 * 用@Component表示这个接口是一个连接器，能用@Component注解的只能是interface或者抽象类
 */

@PoetryScope
@Component(modules = {MainModule.class,PoetryModule.class})  //这里表示Component会从MainModule类中拿那些用@Provides注解的方法来生成需要注入的实例
public abstract class MainComponent {

    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    public abstract void inject(MainActivity activity);
    public abstract void inject(OtherActivity activity);

    private static MainComponent instance;
    public static MainComponent getInstance() {
        if(instance == null) {
            instance = DaggerMainComponent.builder().build();
        }

        return instance;
    }
}
