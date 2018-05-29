package com.grjf365.gongrongpoints.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	private static final String SHAREDPREFERE_NAME = "GongRongPoints";

	public static final String TAG_FIRST_INSTALL = "isFirst";


	
	public synchronized static void setFirst(Context context, boolean isFirst) {
		SharedPreferences sp = context.getSharedPreferences(
				SHAREDPREFERE_NAME, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(TAG_FIRST_INSTALL, isFirst);
		editor.commit();
	}
	
	public synchronized static boolean isFirst(Context context) {
		SharedPreferences sp = context.getSharedPreferences(
				SHAREDPREFERE_NAME, Context.MODE_PRIVATE);
		boolean isFirst = sp.getBoolean(TAG_FIRST_INSTALL, true);
		return isFirst;
	}

}
