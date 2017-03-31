package com.bdrk.myandroidtip.rxjava;

import android.view.View;

import com.bdrk.myandroidtip.R;
import com.bdrk.myandroidtip.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by 5u51_5 on 2017/3/14.
 */

public class RxJavaActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rxjava;
    }

    @Override
    protected void initTitle() {
        setTitleText("RxJavaActivity");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
    }



    @OnClick({R.id.btn_base_rxjava, R.id.btn_io_rxjava})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_base_rxjava:
//                RxJavaSample.simpleRx();
                break;
            case R.id.btn_io_rxjava:
//                RxJavaSample.ioRxSample();
                getData();
                break;
        }
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("test","test");
        RetrofitFactory.getInstance().getTestData(params)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TestBean testBean) {

                    }
                });
    }
}
