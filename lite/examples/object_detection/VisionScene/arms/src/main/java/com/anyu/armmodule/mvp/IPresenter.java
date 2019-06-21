package com.anyu.armmodule.mvp;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by forev on 2018/7/8.
 * 所有presenter的父类，因为presenter会持有View 以及Model部分，所以
 * 索性就写到总父类里面去吧
 */

public abstract class IPresenter {

    protected IModel mIModel;

    //此处View个人感觉最好用一个弱引用。
    protected WeakReference<IView> mViewReference;

    protected Activity activity;

}
