package com.anyu.armmodule.api;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by gaoqiong on 2018/11/12
 * 拦截器
 * 设置公共参数
 */

public class RetrofitInterceptor2 implements Interceptor {


    // 公共参数
    private final String mApiKey;
    private final String mApiSecret;

    public RetrofitInterceptor2(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("mApiKey", mApiKey)
                .addQueryParameter("mApiSecret", mApiSecret);

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .addHeader("Content-Type", "application/json")
                .build();
        //LogUtils.e("----------", newRequest.url() + " ");
        return chain.proceed(newRequest);
    }
}
