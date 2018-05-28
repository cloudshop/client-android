package com.newdun.assist.fragments;

import android.os.Bundle;

import com.webileapps.fragments.CordovaFragment;


/**
 * Created by yingmingbo on 2018/5/28.
 */

public class HomePageFragment extends CordovaFragment {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        // Set by <content src="index.html" /> in config.xml
        loadUrl("index.html");
    }
}
