package com.grjf365.gongrongpoints.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.webileapps.fragments.CordovaFragment;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends CordovaFragment {
    public BaseActivity activity;
    protected WebView webView;
    private boolean isLoad;//标示是否加载过
    /** 弹出框 */
    private ProgressDialog pdDialog;
    private View layout;
    protected String url;


    public boolean onBackPressed() {
        return true;
    };

    /**
     * Fragment创建
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    /**
     * Fragment布局
     * <p/>
     * return 返回Fragment应该填充的View对象
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = initView();
        return view;
    }

    /**
     * Activity创建完成
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() ) {
            loadUrl();
            resume();
        }
    }

    /**
     * 初始化界面 该方法必须实现!
     */
    public View initView() {
        return null;
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    public void refreshUrl(){
        if(webView != null){
            webView.reload();
        }
    }

    protected void loadUrl(){
        isLoad = true;
        initData();
    }

    public void setLoad(boolean isLoad){
        this.isLoad = isLoad;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        Logger.i(" --------- isVisibleToUser ========  "+isVisibleToUser+"  isVisible() = "+isVisible()+"  isLoad = "+!isLoad);
        //判断Fragment中的ListView时候存在，判断该Fragment时候已经正在前台显示  通过这两个判断，就可以知道什么时候去加载数据了
        if (isVisibleToUser && isVisible() && !isLoad) {
            loadUrl();
        }
        if(isVisibleToUser && isVisible()){
            resume();
        }
        if(!isVisibleToUser && isVisible() && isLoad){
            pause();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }



    public ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    public ProgressDialog showWaitDialog(String message) {
        if(pdDialog == null){
            pdDialog = new ProgressDialog(getActivity());
            pdDialog.setCanceledOnTouchOutside(true);
//            pdDialog.setCancelable(false);
        }
        if(message != null){
            pdDialog.setMessage(message);
        }
        pdDialog.show();
        return pdDialog;
    }

    public void hideWaitDialog() {
        if(pdDialog != null && pdDialog.isShowing()){
            pdDialog.dismiss();
        }
    }

    public void resume(){

    }
    public void pause(){
    }

    public void onKey(int keyCode, KeyEvent event){}

    public void callJs(String message){}
}
