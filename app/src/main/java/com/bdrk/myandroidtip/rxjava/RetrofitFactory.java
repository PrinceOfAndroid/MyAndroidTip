package com.bdrk.myandroidtip.rxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 5u51_5 on 2017/3/15.
 */

public class RetrofitFactory {
    private static HttpService httpService = null;

    private RetrofitFactory() {
    }


    public static HttpService getInstance() {
        if (httpService == null) {
            synchronized (RetrofitFactory.class) {
                if (httpService == null) {
                    return createHttpService();
                }
            }
        }
        return httpService;
    }

    private static HttpService createHttpService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .connectTimeout(8000L, TimeUnit.MINUTES)
                .readTimeout(8000L, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        httpService = retrofit.create(HttpService.class);
        return httpService;
    }
}
