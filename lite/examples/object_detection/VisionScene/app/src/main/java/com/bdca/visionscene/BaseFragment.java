package com.bdca.visionscene;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.anyu.armmodule.mvp.IPresenter;
import com.anyu.armmodule.mvp.IView;
import com.anyu.armmodule.utils.Util;

public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    public MainActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (MainActivity) context;
    }
    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected MainActivity getHoldingActivity() {
        return mActivity;
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Util.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment, frameId);

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Util.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        Util.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        Util.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        Util.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }


}
