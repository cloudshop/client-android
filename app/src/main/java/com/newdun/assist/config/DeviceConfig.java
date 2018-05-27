package com.newdun.assist.config;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;


/**
 * 设备属性
 * @author Administrator
 *
 */
public class DeviceConfig {

	
	public static final String source="Android";
	
	//public static int verCode;
	
	//public static String verName="1.0";
			
	public static int appModel = 1; //1:用户版2:商户版
	
	public static String OSVersion;//操作系统版本
	
	public static String deviceModel;//手机型号
			
	public static String imei="1234567890";
	
	public static int  mWidth;
	
	public static int mHeight;
	public static int mScreenSize;
	
	public static String pushID;
	
	public static double latitude = 28.218782;
	
	public static double longitude = 112.275476;
	
	public static void initConfig(Context context){
		
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		DeviceConfig.mWidth = metrics.widthPixels; //
		DeviceConfig.mHeight = metrics.heightPixels; //
		DeviceConfig.mScreenSize = DeviceConfig.mWidth * DeviceConfig.mHeight;
		
		//verCode = DeviceUtil.getVersionCode(act);
		//verName = DeviceUtil.getVersionName(act);
		
		imei = getMobileID(context);
		OSVersion = String.valueOf(android.os.Build.VERSION.RELEASE);
		deviceModel = android.os.Build.MODEL;
	}
	
	
	/**
	 * 手机IMEI号
	 * @param c
	 * @return
	 */
	public static String getMobileID(Context c) {
		
		TelephonyManager telephonemanage = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
		
		String phoneID = telephonemanage.getDeviceId();
		if(phoneID == null || "".equals(phoneID)) 
		{
			phoneID = String.valueOf(System.currentTimeMillis()); 
		}

		return phoneID;
	}
}
