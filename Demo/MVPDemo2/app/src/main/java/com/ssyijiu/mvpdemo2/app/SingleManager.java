package com.ssyijiu.mvpdemo2.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by ssyijiu on 2016/10/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class SingleManager {
    private final static HashMap<Class<?>,AbsSingle> mModelMap = new HashMap<>();

    private static <T extends AbsSingle> T createModel(Class<T> clazz){

        if (clazz!=null && AbsSingle.class.isAssignableFrom(clazz)){
            try {

                Constructor constructor = clazz.getDeclaredConstructor ();
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();
                mModelMap.put(clazz, instance);
                return instance;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException("your model must extends AbsSingle");
        }
        throw new IllegalArgumentException("your model must extends AbsSingle");
    }


    protected static <T extends AbsSingle> T  getInstance(Class<T> clazz) {
        if (mModelMap.get(clazz) == null){
            synchronized (clazz){
                if (mModelMap.get(clazz) == null){
                    createModel(clazz);
                }
            }
        }
        return (T) mModelMap.get(clazz);
    }

}
