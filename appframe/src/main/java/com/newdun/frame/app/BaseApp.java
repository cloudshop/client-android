package com.newdun.frame.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.newdun.frame.activity.BaseActivity;

public class BaseApp extends Application {
	private static final String TAG = "Base.BaseApp";
	private static final boolean DEBUG = true;

	private static BaseApp mApp = null;
	private Class<?> mClsHomeActivity = null;
	private Class<?> mClsLoginActivity = null;

	private int mAppStatus = APP_UNKNOW;
	public static final String EXIT_EXTRAS = "EXIT";
	public static final int APP_UNKNOW = 0;
	public static final int APP_LOGINING = 1;
	public static final int APP_LOGINED = 2;
	public static final int APP_EXITING = 3;
	private final String[] APP_STATUS = { "APP_UNKNOW", "APP_LOGINING",
			"APP_LOGINED", "APP_EXITING" };
	public boolean mExiting = false;

	@Override
	public void onCreate() {
		if (DEBUG)
			Log.v(TAG, "onCreate");
		super.onCreate();

		mApp = this;
	}

	@Override
	public void onTerminate() {
		if (DEBUG)
			Log.v(TAG, "onTerminate");
		// Clean storage, memory, cache, ... , etc.

		// stop the logservice
	}

	public synchronized static BaseApp getApplication() {
		return mApp;
	}

	public void setAppStatus(int appStatus) {
		if (DEBUG)
			Log.v(TAG, String.format("setAppStatus status[%d]:%s-->[%d]:%s",
					mAppStatus, APP_STATUS[mAppStatus], appStatus,
					APP_STATUS[appStatus]));
		mAppStatus = appStatus;
	}

	public int getAppStatus() {
		if (DEBUG)
			Log.v(TAG, String.format("getAppStatus status[%d]:%s", mAppStatus,
					APP_STATUS[mAppStatus]));
		return mAppStatus;
	}

	public void setHomeActivityClass(Class<?> cls) {
		mClsHomeActivity = cls;
	}

	public Class<?> getHomeActivityClass() {
		return mClsHomeActivity;
	}

	public void setLoginActivityClass(Class<?> cls) {
		mClsLoginActivity = cls;
	}

	public Class<?> getLoginActivityClass() {
		return this.mClsLoginActivity;
	}

	public void doEnterHomePage(Context context) {
		if (DEBUG)
			Log.v(TAG, "doEnterHomePage");
		BaseActivity activity = (BaseActivity) context;
		Intent intent = new Intent(activity, mClsHomeActivity);
		activity.startActivity(intent);
	}

	public void doEnterLoginPage(Context context) {
		if (DEBUG)
			Log.v(TAG, "doEnterLoginPage");
		if (mClsLoginActivity != null) {
			Activity activity = (Activity) context;
			Intent intent = new Intent(activity, mClsLoginActivity);
			activity.startActivity(intent);
		} else {
			this.setAppStatus(APP_LOGINED);
		}
	}

	public void doBack(Context context) {
		if (DEBUG)
			Log.v(TAG, "doBack");
		BaseActivity activity = (BaseActivity) context;
		activity.finish();
	}

	public void doExit(Context context) {
		if (DEBUG)
			Log.v(TAG, "doExit");
		
		if (!mExiting) {
			Toast.makeText(context, "再按一次退出", Toast.LENGTH_LONG).show();
			mExiting = true;
			return;
		} 
//		setAppStatus(BaseApp.APP_EXITING);
		try {
//			getWebServiceController("").logout();
			Activity activity = (Activity) context;
			Activity parent = activity.getParent();
			if (parent != null) {
				parent.finish();
			} else {
				activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 1.5 - 2.1之前下面两行是ok的,2.2之后就不行了，所以不通用
		// ActivityManager am =
		// (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
		// am.restartPackage("com.tutor.exit");

//		Intent intent = new Intent(context, mClsHomeActivity);
//		// 这里设置flag还是比较 重要的
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		// 发出退出程序指示
//		intent.putExtra(EXIT_EXTRAS, true);
//		BaseActivity activity = (BaseActivity) context;
//		activity.startActivity(intent);
	}

	public void clearCacheData() {
		if (DEBUG)
			Log.v(TAG, "clearCacheData");
		throw new UnsupportedOperationException();
	}
	
	public void updateApp(Activity activity) {
		
	}
}
