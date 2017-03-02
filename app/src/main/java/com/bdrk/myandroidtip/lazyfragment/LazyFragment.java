package com.bdrk.myandroidtip.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 5u51_5 on 2017/2/17.
 * 懒加载Fragment 用于ViewPager 不进行预加载到，切换到此Fragment时才会进行加载
 */

public abstract class LazyFragment extends Fragment {
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;

    /**
     * setUserVisibleHint()可能会在Fragment的生命周期之外被调用，也就是可能在view创建前就被调用，
     * 也可能在destroyView后被调用，所以如果涉及到一些控件的操作的话，可能会报 null 异常，因为控件还没初始化，或者已经摧毁了。
     *
     * @param isVisibleToUser 表示当前Fragment是否可见。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("TAG", "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            //当前Fragment从不可见到可见
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            //当前Fragment从可见到不可见
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view 还没有进行创建,并且当前Fragment是可见的
        if (!hasCreateView && getUserVisibleHint()) {
            //设置当前Fragment从不可见到可见,fragment状态为可见
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = onCreateFragmentView(inflater, container, savedInstanceState);
        initView(rootView);
        return rootView;
    }

    //抽象 由子类实现，获取View
    protected abstract View onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    //实例化操作
    protected abstract void initView(View view);

    /**
     * 默认为false
     */
    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected abstract void onFragmentVisibleChange(boolean isVisible);

}



