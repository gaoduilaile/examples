package com.anyu.armmodule.api;

import com.anyu.armmodule.utils.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建日期：2018/11/12
 * 描述:rx + retrofit封装网络请求框架
 * 作者: gaoqing
 */
public class RetrofitClient {

    //是不是测试环境
    public static boolean isDebug = true;
    private static String BASE_URL_RELEASE = "http://krcircle6.hz.taeapp.com/";
    private static String BASE_URL_DEBUG = "http://testforkrcircle.hz.taeapp.com/";
    //视氪导航服务器地址
    public static String BASE_URL = isDebug ? BASE_URL_DEBUG : BASE_URL_RELEASE;


    public static String TAG = "RetrofitClient: ";
    private static final int DEFAULT_TIMEOUT = 10;

    public static ApiService apiService;
    private static Retrofit retrofit;
    private static RetrofitClient mInstance;


    /**
     * RetrofitClient 单例
     */
    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    /**
     * ApiService 单例
     */
    private static ApiService getApiServiceInstance() {
        if (apiService == null) {
            synchronized (RetrofitClient.class) {
                if (apiService == null) {
                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        OkHttpClient client = httpClientBuilder
                .retryOnConnectionFailure(true) //连接失败后是否重新连接,
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //并设置超时时间,读写时间
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new RetrofitInterceptor())    //添加拦截器 设置公参
                .build();


        //获取Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)  //设置baseUrl,注意，baseUrl必须后缀"/"
                .addConverterFactory(GsonConverterFactory.create())   //添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //这个是用来决定你的返回值是Observable,不设置加返回值是Call。
                .client(client)
                .build();
    }


    public interface ApiSubscribeRequest {
        void getDataSuccess(String string);

        void getDataFailure();
    }

    /**
     * 封装线程管理和订阅的过程
     * <p>
     * subscribeOn 和 observeOn 的实现方法：
     * subscribeOn 的原理就是在指定线程中向上游订阅（白话就是在指定线程中去调上游的subscribe方法），
     * observeOn 的原理是收到数据后在指定的线程中调用下游的回调方法（onNext/onError/onComplete等），
     * 而数据又总是在发生订阅关系之后才被收到，所以 subscribeOn 即使出现 observeOn 之后也能保证数据源运行的线程。
     */
    public void ApiSubscribe(Map<String, Object> map, String url, ObserverOption option, final ApiSubscribeRequest subscribeRequest) {

        Observable<ResponseBody> observable = RetrofitClient.getApiServiceInstance()
                .postMap(url, map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .safeSubscribe(new MyObserver(option, new MyObserver.BaseObserverFun() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtils.e("onNext ",string);
                            subscribeRequest.getDataSuccess(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                            subscribeRequest.getDataFailure();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError ",e.toString());
                        subscribeRequest.getDataFailure();
                    }
                }));
    }


    /**
     * map转 RequestBody
     *
     * @param map
     * @return
     */

    public static RequestBody getRequestBodyFromMap(Map<String, Object> map) {
        map.put("mobile_type", "Android");
        Gson gson = new Gson();
        String toJson = gson.toJson(map);////通过Gson将Bean转化为Json字符串形式
//        LogUtils.e(TAG, "getRequestBody: " + toJson);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), toJson);
        return requestBody;
    }


    public static RequestBody getRequestBody(Object t) {
        Gson gson = new Gson();
        String toJson = gson.toJson(t);////通过Gson将Bean转化为Json字符串形式
//        LogUtils.e(TAG, "getRequestBody: " + toJson);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), toJson);
        return requestBody;
    }


}
