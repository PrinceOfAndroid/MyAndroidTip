package com.bdrk.myandroidtip;

import android.content.Intent;
import android.widget.Button;

import com.bdrk.myandroidtip.anim.AnimActivity;
import com.bdrk.myandroidtip.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.btn_anim)
    Button btnAnim;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {
        setTitleText("AndroidTip");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_anim)
    public void onClick() {
        Intent intent=new Intent(MainActivity.this, AnimActivity.class);
        startActivity(intent);
    }
}
