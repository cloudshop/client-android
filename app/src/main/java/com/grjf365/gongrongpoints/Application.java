package com.grjf365.gongrongpoints;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.grjf365.gongrongpoints.config.Constant;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by yingmingbo on 2018/5/28.
 */

public class Application extends android.app.Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);     		// 初始化 JPush
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wxf177c6755716fa32", "dcad950cd0633a27e353477c4ec12e7a");
        /**
         * since 1.5.0，1.5.0版本后增加API，支持在代码中设置第三方appKey等信息，当PlatformConfig为null时，或者使用JShareInterface.init(Context)时需要配置assets目录下的JGShareSDK.xml
         **/
        //*
//        JShareInterface.init(this, platformConfig);
        initLogger();
    }

    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(5)        // （可选）隐藏内部方法调用到偏移量。 默认5  .logStrategy(customLog) //（可选）更改要打印的日志策略。 默认LogCat
                .tag("POWER")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return Constant.DEBUG;
            }
        });

    }
}
