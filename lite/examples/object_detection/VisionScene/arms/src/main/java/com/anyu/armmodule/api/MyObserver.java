package com.anyu.armmodule.api;

import android.content.Context;
import android.widget.Toast;

import com.anyu.armmodule.utils.Util;
import com.anyu.armmodule.weight.CustomProgressDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by gaoqiong on 2019/5/9
 * 自定义Observer 中添加loading弹窗
 */
public class MyObserver implements Observer<ResponseBody> {

    private Context context;
    private final CustomProgressDialog closeLoadingProgress;
    private final BaseObserverFun observerFun;
    public String textShow;
    public boolean notShow;

    public MyObserver(ObserverOption option, BaseObserverFun observerFun) {
        this.context = option.context;
        this.textShow = option.textShow;
        this.notShow = option.notShow;
        this.observerFun = observerFun;

        if (textShow == null || textShow.length() == 0) {
            textShow = "加载中";
        }
        closeLoadingProgress = new CustomProgressDialog(context, textShow);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!Util.isConn(context)) {
            Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            // 一定好主动调用下面这一句
            onComplete();
            return;
        }
        // 显示进度条
        if (!notShow) {
            closeLoadingProgress.show();
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        observerFun.onNext(responseBody);
    }

    @Override
    public void onError(Throwable e) {
        observerFun.onError(e);
        closeLoadingProgress.dismiss();
    }

    @Override
    public void onComplete() {
        closeLoadingProgress.dismiss();
    }


    interface BaseObserverFun {

        void onNext(ResponseBody responseBody);

        void onError(Throwable e);
    }

}
