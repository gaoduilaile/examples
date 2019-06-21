package com.anyu.armmodule.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by gaoqiong on 2019/4/4
 */

class LoggingInterceptor implements Interceptor {
    String tag = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        MediaType mediaType = response.body().contentType();
        String content= response.body().string();
        Log.e(tag, response.request().url()+" ");


        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
