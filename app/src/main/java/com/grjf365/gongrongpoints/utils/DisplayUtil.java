package com.grjf365.gongrongpoints.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * @fun: 屏幕工具类
 */
public class DisplayUtil {

	public static final String TAG = "DisplayUtil";

	/**
	 * get screen height of this cellphone
	 * 
	 * @param context
	 * @return
	 */
	public static int getMobileHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels; // 得到高度
		return height;
	}

	/**
	 * get screen width of this cellphone
	 * 
	 * @param context
	 * @return
	 */
	public static int getMobileWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels; // 得到宽度
		return width;

	}

	/**
	 * 根据手机的分辨率dp 转成px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率px(像素) 转成dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	
	 public static float sp2px(Context context, float sp){
	        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
	        return sp * scale;
	    }



	/**
	 * 状态栏高度算法
	 * @param activity
	 * @return
	 */
	public static int getStatusHeight(Activity activity){
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight){
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
				statusHeight = activity.getResources().getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

}
