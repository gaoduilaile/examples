package com.bdca.visionscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.anyu.armmodule.api.AppManager;
import com.anyu.armmodule.mvp.IPresenter;
import com.anyu.armmodule.mvp.IView;
import com.anyu.armmodule.utils.Util;


/**
 * <p>Activity基类 </p>
 *
 * @name BaseActivity
 */
public abstract class BaseActivity<T extends IPresenter> extends FragmentActivity implements IView {

    public Activity mContext;
    protected T mPresenter; //泛型 presenter
    /**
     * 封装的findViewByID方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getInstance().addActivity(this);
    }

    /**
     * 供子类初始化一些配置
     *
     * @return
     */
//    public abstract void initInjector();

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 通过类名启动Activity，并且含有string数据
     */
    protected void openActivity(Class<?> pClass, String type, String pBundle) {
        Intent intent = new Intent(this, pClass);
        if (isNotEmpty(pBundle)) {
            intent.putExtra(type, pBundle);
        }
        startActivity(intent);
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && str.length() > 0 && !str.equals("null")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
        Runtime.getRuntime().gc();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Util.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Util.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }
    /**
     * 替换fragment
     *
     * @param fragment
     */
    protected void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
