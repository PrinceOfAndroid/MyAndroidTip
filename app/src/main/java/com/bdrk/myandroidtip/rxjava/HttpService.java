package com.bdrk.myandroidtip.rxjava;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 5u51_5 on 2017/3/15.
 */

public interface HttpService {
    @POST(Url.TEST_URL)
    Observable<TestBean> getTestData(@QueryMap Map<String, String> params);

}
