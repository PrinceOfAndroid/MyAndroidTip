package com.bdrk.myandroidtip.singlemodle;

/**
 * Created by 5u51_5 on 2017/3/1.
 * 饿汉式
 * 是最简单的实现方式，这种实现方式适合那些在初始化时就要用到单例的情况，
 * 这种方式简单粗暴，如果单例对象初始化非常快，而且占用内存非常小的时候这种方式是比较合适的，可以直接在应用启动时加载并初始化。
 *
 * 因为JVM只会加载一次单例类，线程安全
 */

public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    //构造方法私有
    private HungrySingleton() {
    }

    public static HungrySingleton newInstance() {
        return instance;
    }
}
