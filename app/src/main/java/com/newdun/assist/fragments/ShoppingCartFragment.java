package com.newdun.assist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.grjf365.gongrongpoints.javascriptInterface.MyJavascriptInterface;
import com.webileapps.fragments.CordovaFragment;
import com.grjf365.gongrongpoints.fragment.BaseFragment;


/**
 * Created by yingmingbo on 2018/5/28.
 */

public class ShoppingCartFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        launchUrl = "file:///android_asset/www/index.html#/Shopping";
        View view = super.onCreateView(inflater, container, savedInstanceState);

        WebView webView = (WebView) getAppView().getEngine().getView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavascriptInterface(this.getActivity()),"androidObject");

        return view;
    }

}
