package com.bdrk.myandroidtip.singlemodle;

/**
 * Created by 5u51_5 on 2017/3/1.
 * 懒汉式
 * 如果单例初始化的操作耗时比较长而应用对于启动速度又有要求，或者单例的占用内存比较大，
 * 再或者单例只是在某个特定场景的情况下才会被使用，而一般情况下是不会使用时，
 * 使用「饿汉式」的单例模式就是不合适的，这时候就需要用到「懒汉式」的方式去按需延迟加载单例。
 *
 * 需要的时候才进行初始化
 * 单线程可用，但多线程无法保证线程安全
 */

public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton newInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
