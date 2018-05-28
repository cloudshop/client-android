package com.grjf365.gongrongpoints;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by yingmingbo on 2018/5/28.
 */

public class Application extends android.app.Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
