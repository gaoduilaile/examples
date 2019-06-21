package com.bdca.visionscene.presenter;

import com.anyu.armmodule.mvp.IPresenter;
import com.anyu.armmodule.mvp.IView;

import javax.inject.Inject;

public class FragmentPosturePresenter  extends IPresenter {

    IView iView;

    @Inject
    public FragmentPosturePresenter(IView iView){
        this.iView = iView;
    }

    public void login(){
    }
}
