package com.newdun.assist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grjf365.gongrongpoints.R;
import com.webileapps.fragments.CordovaFragment;


/**
 * Created by yingmingbo on 2018/5/28.
 */
public class HomePageFragment extends CordovaFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        launchUrl = "file:///android_asset/www/static/homepage/index.html";
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}
