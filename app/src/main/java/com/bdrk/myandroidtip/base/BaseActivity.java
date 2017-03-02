package com.bdrk.myandroidtip.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bdrk.myandroidtip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5u51_5 on 2017/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private List<Activity> activities;
    private LinearLayout rootLayout;
    private Toolbar toolbar;

    public BaseActivity() {
        //构造方法创建装Activity的list
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
    }

    //重写onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);

        // 经测试在代码里直接声明透明状态栏更有效（设置状态栏透明）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        activities.add(this);
    }


    public void setContentViewRes(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    /**
     * 绑定view(用LinearLayout去add自己子activity的布局)
     *
     * @param view
     */
    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
        initView();
        setTitle();
        initListener();
    }


    /**
     * 初始化标题
     * 由于toolbar的标题无法居中，而且有限制，所以设置标题为空字符串，自己配置标题样式
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected abstract void setTitle();

    protected abstract void initListener();

    protected abstract void initView();


    /**
     * 退出程序
     */
    public void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 关闭所有界面
     */
    public void finishActivities(){
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    //重写onDestory
    @Override
    protected void onDestroy() {
        activities.remove(this);
        Log.i("移出一个Activity", this.toString() + "个数：" + activities.size());
        super.onDestroy();

    }
}
