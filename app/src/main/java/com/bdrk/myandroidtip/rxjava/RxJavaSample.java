package com.bdrk.myandroidtip.rxjava;

/**
 * Created by 5u51_5 on 2017/3/14.
 */

public class RxJavaSample {
//    /**
//     * 简单的链式Rx
//     */
//    public static void simpleRx() {
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1); //发送器 发送onNext事件
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete(); //发送 onComplete事件 发送后事件继续发送，但下游不会接收 唯一，不可与onError同时存在
//                //e.onError(); 发送onError事件 发送后事件继续发送，但下游不会接收
//            }
//        }).subscribe(new Observer<Integer>() {
//            private Disposable disposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                disposable = d;
//                LogUtils.i("建立好连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                LogUtils.i("onNext:" + value);
//                if (value == 2) {
//                    disposable.dispose(); //切断水管，但是发布者还是会发布，i只是接收者接收不到后面的内容
//                    LogUtils.i("切断水管");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.i("onError");
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtils.i("onComplete");
//            }
//        });
//    }
//
//    public static void ioRxSample() {
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("消息1");
//                e.onNext("消息2");
//                e.onNext("消息3");
//                e.onNext("消息4");
//                LogUtils.d("thread:" + Thread.currentThread().getName());
//            }
//        }).subscribeOn(Schedulers.io())//设置发布线程，只有第一次有效
//                .observeOn(AndroidSchedulers.mainThread()) //设置接收线程(这里为主线程)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        LogUtils.d("msg:" + s + "      thread:" + Thread.currentThread().getName());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        LogUtils.d("error");
//                    }
//                });
//    }
}
