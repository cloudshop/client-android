package com.newdun.assist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webileapps.fragments.CordovaFragment;


/**
 * Created by yingmingbo on 2018/5/28.
 */

public class MineFragment extends CordovaFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        launchUrl = "file:///android_asset/www/index.html#/Mine";
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}
