package com.bdrk.myandroidtip.singlemodle;

/**
 * Created by 5u51_5 on 2017/3/1.
 * 懒汉式同步锁
 * 使用同步锁 synchronized (Singleton.class) 防止多线程同时进入造成instance被多次实例化
 */

public class SyLazySingleton {
    private static SyLazySingleton instance = null;

    private SyLazySingleton() {
    }

    public static SyLazySingleton newInstance() {
        synchronized (SyLazySingleton.class) {
            if (instance==null) {
                instance = new SyLazySingleton();
            }
            return instance;
        }
    }
}
