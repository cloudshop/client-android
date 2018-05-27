package com.newdun.frame.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by ymb on 16/11/3.
 */

public class BaseFragment extends Fragment implements IAFWrapper {
    private static final String TAG = "BaseFragment";
    private static final boolean DEBUG = true;
    private ActivityWrapper mWrapper = new ActivityWrapper(this);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 统一事件处理
     * @param id
     * @return
     */
    @Override
    public boolean onEvent(int id) {
        return false;
    }

    /**
     * 页面跳转
     */
    @Override
    public void goHome() {
        mWrapper.goHome();
    }

    @Override
    public void goHome(int tabIndex) {
        mWrapper.goHome(tabIndex);
    }

    @Override
    public boolean confirmLogin(int id) {
        return mWrapper.confirmLogin(id);
    }

    @Override
    public void startActivity(Class<?> cls) {
        mWrapper.startActivity(cls);
    }

    @Override
    public void startActivityAndFinish(Class<?> cls) {
        mWrapper.startActivityAndFinish(cls);
    }

    @Override
    public void startActivityForResult(Class<?> cls, int requestCode) {
        mWrapper.startActivityForResult(cls, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mWrapper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onActivityResultInner(int requestCode, int resultCode, Intent data) {
        return mWrapper.onActivityResultInner(requestCode, resultCode, data);
    }

    /**
     * Toast显示相关函数
     */
    @Override
    public void showToast(String msg) {
        mWrapper.showToast(msg);
    }

    @Override
    public void showToast(int resId) {
        mWrapper.showToast(resId);
    }

}
