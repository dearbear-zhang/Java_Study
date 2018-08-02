package com.my.main.com.my.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class HttpMethods {
    private Retrofit mRetrofit;
    private static final String sUrl = "http://www.baidu.com";

    private HttpMethods(){
        OkHttpClient.Builder httpCLientBuilder = new OkHttpClient.Builder();
        httpCLientBuilder.connectTimeout(10, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpCLientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())       // 可以增加自定义请求解析和加密
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(sUrl)
                .build();
    }

}
