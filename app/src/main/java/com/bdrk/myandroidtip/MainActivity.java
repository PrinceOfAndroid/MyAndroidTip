package com.bdrk.myandroidtip;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.bdrk.myandroidtip.anim.AnimActivity;
import com.bdrk.myandroidtip.base.BaseActivity;
import com.bdrk.myandroidtip.rxjava.RxJavaActivity;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.btn_anim)
    Button btnAnim;
    private Tencent mTencent;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

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
        mTencent = Tencent.createInstance("1105982551", this.getApplicationContext());
    }

    @OnClick({R.id.btn_anim, R.id.btn_rxjava, R.id.btn_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_anim:
                Intent intent = new Intent(MainActivity.this, AnimActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_rxjava:
                Intent intent2 = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_share:
                onClickShare();
                break;
        }
    }

    private void onClickShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用222222");
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");
        mTencent.shareToQQ(MainActivity.this, params, new BaseUiListener());
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError e) {
//            showResult("onError:", "code:" + e.errorCode + ", msg:"
//                    + e.errorMessage + ", detail:" + e.errorDetail);
        }
        @Override
        public void onCancel() {
//            showResult("onCancel", "");
        }
    }
}
