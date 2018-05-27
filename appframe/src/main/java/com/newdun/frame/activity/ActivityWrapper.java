package com.newdun.frame.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.newdun.frame.app.BaseApp;
import com.newdun.frame.config.ModuleConfig;

import static android.app.Activity.RESULT_CANCELED;
import static com.newdun.frame.app.BaseApp.APP_LOGINED;


public class ActivityWrapper implements IAFWrapper {
	private static final String TAG = "ActivityWrapper";
	private static final boolean DEBUG = true;
	public BaseApp mApp = BaseApp.getApplication();
	public Object mActivityOrFragment;

	public ActivityWrapper(Object wrapped) {
		mActivityOrFragment = wrapped;
	}

	public void goHome() {
		goHome(0);
	}

	public void goHome(int tabIndex) {
		final Class<?> klass = mApp.getHomeActivityClass();
		if (klass != null && !klass.equals(mActivityOrFragment.getClass())) {
			if (ModuleConfig.getInstance().isDebuggable()) {
				Log.i(TAG, "Going back to the home activity");
			}

			if (mActivityOrFragment instanceof Activity) {
				Intent intent = new Intent((Activity) mActivityOrFragment, klass);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("INDEX", tabIndex);
				((Activity)mActivityOrFragment).startActivity(intent);
			} else if (mActivityOrFragment instanceof Fragment) {
				Intent intent = new Intent(((Fragment) mActivityOrFragment).getActivity(), klass);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("INDEX", tabIndex);
				((Fragment)mActivityOrFragment).startActivity(intent);
			}
		}
	}

	/** 判断是否登陆 */
	public boolean confirmLogin(int id) {
		if (mApp.getAppStatus() == APP_LOGINED) {
			return true;
		}
		id = id & 0xFFF;
		startActivityForResult(mApp.getLoginActivityClass(), id);
		showToast("您还未登录");
		return false;
	}

	public void startActivity(Class<?> cls) {

		((Activity)mActivityOrFragment).startActivity(new Intent((Activity)mActivityOrFragment, cls));
	}

	public void startActivityAndFinish(Class<?> cls) {
		try {
			((Activity)mActivityOrFragment).finish();
		} catch (Exception e) {

		}
		((Activity)mActivityOrFragment).startActivity(new Intent((Activity)mActivityOrFragment, cls));
	}

	public void startActivityForResult(Class<?> cls, int requestCode) {
		int id = requestCode & 0xFFF;
		((Activity)mActivityOrFragment).startActivityForResult(new Intent((Activity)mActivityOrFragment, cls), id);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultInner(requestCode, resultCode, data);
	}

	public boolean onActivityResultInner(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_CANCELED)
			return false;
		if (onEvent(requestCode)) {
			return true;
		}
		return false;
	}

	synchronized public boolean onEvent(int id) {
		return false;
	}



	/**
	 * Toast相关函数
 	 */
	public void showToast(String msg) {

		Toast.makeText((Activity)mActivityOrFragment, msg, Toast.LENGTH_SHORT).show();
	}

	public void showToast(int resId) {

		Toast.makeText((Activity)mActivityOrFragment, resId, Toast.LENGTH_SHORT).show();
	}
}
