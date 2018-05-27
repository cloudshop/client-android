package com.newdun.frame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.newdun.frame.R;
import com.newdun.frame.widget.Toolbar;

public class BaseActivity extends AppCompatActivity implements IAFWrapper {
    private static final String TAG = "BaseActivity";
    private static final boolean DEBUG = true;

    private ActivityWrapper mWrapper = new ActivityWrapper(this);
    private View mProgressView;
    private FrameLayout mContentView;
    private Toolbar mToolbar;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG)
            Log.v(TAG, "onCreate");

        super.onCreate(savedInstanceState);

//        setContentView(Resource.getIdByName(getApplication(), "id", "id_iv_back")layout.activity_base);
        super.setContentView(R.layout.activity_base);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        mContentView = (FrameLayout) findViewById(R.id.gd_action_bar_content_view);
        mProgressView = findViewById(R.id.login_progress);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void setContentView(int layoutResID) {
        mContentView.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, mContentView);
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
