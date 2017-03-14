package com.bdrk.myandroidtip.anim;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.bdrk.myandroidtip.R;
import com.bdrk.myandroidtip.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 5u51_5 on 2017/3/10.
 */

public class AnimActivity extends BaseActivity {
    @InjectView(R.id.btn_alpha)
    Button btnAlpha;
    @InjectView(R.id.btn_rotate)
    Button btnRotate;
    @InjectView(R.id.btn_translate)
    Button btnTranslate;
    @InjectView(R.id.btn_scale)
    Button btnScale;
    @InjectView(R.id.btn_set)
    Button btnSet;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_anim;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_alpha, R.id.btn_rotate, R.id.btn_translate, R.id.btn_scale, R.id.btn_set
            , R.id.btn_obj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                alpha();
                break;
            case R.id.btn_rotate:
                rotate();
                break;
            case R.id.btn_translate:
                translate();
                break;
            case R.id.btn_scale:
                scale();
                break;
            case R.id.btn_set:
                set();
                break;
            case R.id.btn_obj:
                Intent intent = new Intent(AnimActivity.this, ObjectAnimActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 动画集合
     */
    private void set() {
        AnimationSet set = new AnimationSet(true);
        set.setDuration(2000);
        //透明度变化 从0到1
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        //设置变化时间为1s
        alpha.setDuration(2000);
        set.addAnimation(alpha);
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        set.addAnimation(ra);
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 200);
        ta.setDuration(2000);
        set.addAnimation(ta);
        ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2
                , Animation.RELATIVE_TO_SELF, 0.6f,
                Animation.RELATIVE_TO_SELF, 0.3f);
        scale.setDuration(2000);
        set.addAnimation(scale);
        btnSet.startAnimation(set);
    }

    /**
     * 缩放动画
     */
    private void scale() {
        ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2
                , Animation.RELATIVE_TO_SELF, 0.6f, //设置缩放中心点x位置
                Animation.RELATIVE_TO_SELF, 0.3f); //设置缩放中心点y位置
        scale.setDuration(3000);
        btnScale.startAnimation(scale);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation scale2 = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.scale_test);
                btnScale.startAnimation(scale2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 平移动画
     */
    private void translate() {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 200);
        ta.setDuration(1000);
        btnTranslate.startAnimation(ta);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation ta2 = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.translate_test);
                btnTranslate.startAnimation(ta2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 旋转动画
     */
    private void rotate() {
//        Log.e("222", btnRotate.getX() + "    " + btnRotate.getY() + "   " + btnRotate.getMeasuredHeight());
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        btnRotate.startAnimation(ra);
        ra.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation rotate2 = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.rotate_test);
                btnRotate.startAnimation(rotate2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 透明度变化
     */
    private void alpha() {
        //透明度变化 从0到1
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        //设置变化时间为1s
        alpha.setDuration(2000);
        btnAlpha.startAnimation(alpha);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation alpha2 = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.alpha_test);
                btnAlpha.startAnimation(alpha2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
