package com.anyu.armmodule.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;
//import rx.Observable;

/**
 * 创建日期：2018/11/12
 * 描述: 接口service
 * 作者: gaoqing
 */
public interface ApiService {


    @GET()
    <T> Observable<ResponseBody> get(
            @Url String url,
            @QueryMap Map<String, T> maps);

    @FormUrlEncoded()
    @POST()
    Observable<ResponseBody> postMap(
            @Url String url,
            @FieldMap Map<String, Object> maps);


    @POST()
    <T> Observable<ResponseBody> postBody(
            @Url String url,
            @Body RequestBody requestBody);

//
//    @POST("KrcircleRegister/")
//    Observable<ResponseBody> KrcircleRegister(@Body RequestBody requestBody);
//
//
//    @POST("KrcircleLogin/")
//    Observable<ResponseBody> KrcircleLogin(@Body RequestBody requestBody);


}
