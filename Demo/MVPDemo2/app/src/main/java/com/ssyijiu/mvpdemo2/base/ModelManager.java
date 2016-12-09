package com.ssyijiu.mvpdemo2.base;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ssyijiu on 2016/10/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ModelManager {

    private final static ConcurrentHashMap<String,BaseModel> sModelMap = new ConcurrentHashMap<>();

    private static <T extends BaseModel> T createModel(Class<T> modelType){

        if (modelType!=null && BaseModel.class.isAssignableFrom(modelType)){
            try {
                Constructor<T> constructor = modelType.getDeclaredConstructor ();
                constructor.setAccessible(true);
                T instance = constructor.newInstance();
                sModelMap.put(modelType.getName(), instance);
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException("Your model must extends BaseModel");
        }
        throw new IllegalArgumentException("Your model must extends BaseModel");
    }


    public static <T extends BaseModel> T getModel(Class<T> modelType) {

        String modelName = modelType.getName();

        if (sModelMap.get(modelName) == null){
            synchronized (modelType) {
                if(sModelMap.get(modelName) == null) {
                    createModel(modelType);
                }
            }

        }
        return (T) sModelMap.get(modelName);
    }

}
