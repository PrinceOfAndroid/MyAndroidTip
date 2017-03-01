package com.bdrk.myandroidtip.singlemodle;

/**
 * Created by 5u51_5 on 2017/3/1.
 *
 * 采用内部类，在这个内部类里面去创建对象实例。
 * 这样的话，只要应用中不使用内部类 JVM 就不会去加载这个单例类，也就不会创建单例对象，从而实现「懒汉式」的延迟加载和线程安全
 */

public class StaticSingleton {
    //内部类，装载该内部类时才会创建单例对象
    private static class StaticSingletonHolder {
        public static StaticSingleton instance = new StaticSingleton();
    }

    private StaticSingleton() {
    }


    public static StaticSingleton newInstance() {
        return StaticSingletonHolder.instance;
    }
}
