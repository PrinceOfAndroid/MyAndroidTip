package com.bdrk.myandroidtip.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;

import com.bdrk.myandroidtip.R;
import com.bdrk.myandroidtip.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 5u51_5 on 2017/3/10.
 */

public class ObjectAnimActivity extends BaseActivity {
    @InjectView(R.id.btn_obj_animator)
    Button btnObjAnimator;
    @InjectView(R.id.btn_holder)
    Button btnHolder;
    @InjectView(R.id.btn_obj_set)
    Button btnObjSet;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_object_anim;
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


    @OnClick({R.id.btn_obj_animator, R.id.btn_holder, R.id.btn_obj_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_obj_animator:
                objAnimator();
                break;
            case R.id.btn_holder:
                holderAnim();
                break;
            case R.id.btn_obj_set:
                objAnimatorSet();
                break;
        }
    }

    /**
     * 属性动画集，同时作用多个动画
     * 也可通过AnimatorSet 实现（可控制协同方式）
     */
    private void holderAnim() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("rotation", 360f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 0, 300, 0);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(btnHolder, holder1, holder2, holder3).setDuration(4000).start();
    }

    /**
     *  The animations that will be started one after another. 顺序播放
     *  set.playSequentially();
     *
     *  The animations that will be started simultaneously. 同时播放
     *  set.playTogether()
     *
     *
     */
    private void objAnimatorSet() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btnObjSet, "translationY", 0, 400, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(btnObjSet, "rotation", 360);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(btnObjSet, "alpha", 1, 0, 1);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(4000);
        set.playTogether(animator1,animator2,animator3);
        set.start();
    }

    /**
     * 属性动画
     * translation 偏移动画（X、Y轴都可以）
     * sclae 缩放动画(X、Y缩放)
     * rotation 控制翻转（X、Y轴）
     * alpha 透明度变化
     */
    private void objAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnObjAnimator,
                "rotationX", 360);
        animator.setDuration(2000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
    }

    private static class WrapperView {
        private View mTarget;

        public WrapperView(View mTarget) {
            this.mTarget = mTarget;
        }

        public float getTranslationX() {
            return mTarget.getTranslationX();
//            mTarget.getro
        }
    }

}
