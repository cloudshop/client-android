package com.newdun.assist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.grjf365.gongrongpoints.javascriptInterface.MyJavascriptInterface;
import com.webileapps.fragments.CordovaFragment;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.engine.SystemWebView;


/**
 * Created by yingmingbo on 2018/5/28.
 */

public class ClassifyFragment extends CordovaFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        launchUrl = "file:///android_asset/www/index.html#/Classify";
        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

}
