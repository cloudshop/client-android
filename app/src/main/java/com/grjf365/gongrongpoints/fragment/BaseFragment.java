package com.grjf365.gongrongpoints.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.ValueCallback;
import android.net.Uri;
import android.widget.ProgressBar;

import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.javascriptInterface.MyJavascriptInterface;
import com.webileapps.fragments.CordovaFragment;

import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

/**
 * Fragment基类
 */
public class BaseFragment extends CordovaFragment {
    public BaseActivity activity;
    protected WebView webView;
    private boolean isLoad;//标示是否加载过
    /** 弹出框 */

    private ProgressBar progressBar;

    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;

    private ProgressDialog pdDialog;
    private View layout;
    protected String url;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;

    public static BaseFragment newInstance(int position, Class<BaseFragment> classname, String url) {

        Bundle args = new Bundle();
        args.putInt("position",position);
        args.putString("url",url);
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) classname.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }

    public boolean onBackPressed() {
        return false;
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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        int position = getArguments().getInt("position");
        launchUrl = getArguments().getString("url");

        initView();
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
    public void initView() {

        webView = (WebView) getAppView().getEngine().getView();

        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);//启用js
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDomStorageEnabled(true);
//		webView.getSettings().setAllowFileAccessFromFileURLs(true);
        //支持缩放，默认为true。
        webSettings.setSupportZoom(true);
        //调整图片至适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //设置默认编码
        webSettings.setDefaultTextEncodingName("utf-8");
        ////设置自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        webView.addJavascriptInterface(new MyJavascriptInterface(getActivity()),"androidObject");
//        webView.setWebViewClient(new SystemWebViewClient((SystemWebViewEngine)getAppView().getEngine()) {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
////                showWaitDialog();
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
////                hideWaitDialog();
////                Location location = getLocation(activity);
////                if(location != null){
////                    showLocation(location,view);
////                }
////                GeocodeLocation();
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
////                hideWaitDialog();
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setCancelable(false);
//                builder.setTitle("提示")
//                        .setMessage("网络加载失败，请重试")
//                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                webView.reload();
//                            }
//                        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        activity.finish();
//                    }
//                });
//                builder.show();
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    progressBar.setVisibility(View.GONE);
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressBar.setProgress(newProgress);
//                }
//            }
//            // For Android < 3.0
//            public void openFileChooser(ValueCallback<Uri> valueCallback) {
//                uploadMessage = valueCallback;
//                openImageChooserActivity();
//            }
//
//            // For Android  >= 3.0
//            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
//                uploadMessage = valueCallback;
//                openImageChooserActivity();
//            }
//
//            //For Android  >= 4.1
//            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
//                uploadMessage = valueCallback;
//                openImageChooserActivity();
//            }
//
//            // For Android >= 5.0
//            @Override
//            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//                uploadMessageAboveL = filePathCallback;
//                openImageChooserActivity();
//                return true;
//            }
//
//        });
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
        loadUrl(launchUrl);
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

    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == getActivity().RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

    public void callJs(String message) {
        if(webView != null){
            webView.loadUrl(message);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != getActivity().RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

}
