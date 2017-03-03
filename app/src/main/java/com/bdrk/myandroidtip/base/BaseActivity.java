package com.bdrk.myandroidtip.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdrk.myandroidtip.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 5u51_5 on 2017/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_right_icon)
    ImageView ivRightIcon;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root_layout)
    LinearLayout rootLayout;
    @InjectView(R.id.tv_right_text)
    TextView tvRightText;
    private OnLeftIconClick leftIconClick;
    private OnRightTextClick rightTextClick;
    private OnRightIconClick rightIconClick;

    private List<Activity> activities;


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
        setMyContentView();
        // 经测试在代码里直接声明透明状态栏更有效（设置状态栏透明）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        activities.add(this);
    }

    private void setMyContentView() {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        View view = View.inflate(this, getLayoutRes(), null);
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
        initView();
        initTitle();
        initListener();
    }

    protected abstract int getLayoutRes();

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

    protected abstract void initTitle();

    protected abstract void initListener();

    protected abstract void initView();


    public void setTitleText(String title) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }

    public void setHeadColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    public void setLeftIcon(int resId) {
        toolbar.setNavigationIcon(resId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftIconClick != null) {
                    leftIconClick.leftIconClick(view);
                }
            }
        });
    }

    public void setRightIcon(int resId) {
        ivRightIcon = (ImageView) findViewById(R.id.iv_right_icon);
        ivRightIcon.setVisibility(View.VISIBLE);
        ivRightIcon.setImageResource(resId);
        ivRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightIconClick != null) {
                    rightIconClick.rightIconClick(view);
                }
            }
        });
    }

    public void setRightText(String text) {
        tvRightText.setVisibility(View.VISIBLE);
        tvRightText.setText(text);
        tvRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightTextClick != null) {
                    rightTextClick.rightTextClick(view);
                }
            }
        });
    }

    public void setRightTextGone(boolean gone) {
        if (gone) {
            tvRightText.setVisibility(View.GONE);
        } else {
            tvRightText.setVisibility(View.VISIBLE);
        }
    }

    public void setToolbarGone() {
        toolbar.setVisibility(View.GONE);
    }

    public void setOnRightIconClick(OnRightIconClick rightIconClick) {
        this.rightIconClick = rightIconClick;
    }

    public interface OnRightIconClick {
        void rightIconClick(View view);
    }

    public void setOnRightTextClick(OnRightTextClick rightTextClick) {
        this.rightTextClick = rightTextClick;
    }

    public interface OnRightTextClick {
        void rightTextClick(View view);
    }

    public interface OnLeftIconClick {
        void leftIconClick(View view);
    }

    public void setOnLeftIconClick(OnLeftIconClick leftIconClick) {
        this.leftIconClick = leftIconClick;
    }

    /**
     * 退出程序
     */
    public void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /**
     * 关闭所有界面
     */
    public void finishActivities() {
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
