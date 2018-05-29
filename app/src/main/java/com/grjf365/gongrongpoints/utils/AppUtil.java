package com.grjf365.gongrongpoints.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtil {

	public static String getVersion(Context context) {

		PackageManager packageManager = context.getPackageManager();

		PackageInfo packInfo = null;
		try {

			packInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);

		} catch (PackageManager.NameNotFoundException e) {

			e.printStackTrace();
		}
		String version = packInfo.versionName;
		return version;
	}

	public static int getVersionCode(Context context) {

		PackageManager packageManager = context.getPackageManager();

		PackageInfo packInfo = null;
		try {

			packInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);

		} catch (PackageManager.NameNotFoundException e) {

			e.printStackTrace();
		}
		int version = packInfo.versionCode;
		return version;
	}

}
