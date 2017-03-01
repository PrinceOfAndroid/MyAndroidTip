package com.bdrk.myandroidtip.singlemodle;

/**
 * Created by 5u51_5 on 2017/3/1.
 * 双重检验锁
 *
 * 若已存在实例，则不需要重复获取对象锁
 */

public class DoubleSyLazySingleton {
    private static DoubleSyLazySingleton instance = null;

    private DoubleSyLazySingleton() {
    }

    public static DoubleSyLazySingleton newInstance() {
        if (instance == null) {
            synchronized (DoubleSyLazySingleton.class) {
                if (instance == null) {
                    instance = new DoubleSyLazySingleton();
                }
            }
        }
        return instance;
    }
}
