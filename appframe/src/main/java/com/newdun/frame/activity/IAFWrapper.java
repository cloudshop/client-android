package com.newdun.frame.activity;

import android.content.Intent;

public interface IAFWrapper {

	/** Intent DEFINES */
	public static final String CONTENT_EXTRA = "content_extra";
	public static final String TITLE_EXTRA = "title_extra";

	/**
	 * 页面跳转
	 */
	public void goHome();

	public void goHome(int tabIndex);

	/** 判断是否登陆,未登陆进入登陆界面 */
	public boolean confirmLogin(int id);

	public void startActivity(Class<?> cls);

	public void startActivityAndFinish(Class<?> cls);

	public void startActivityForResult(Class<?> cls, int requestCode);

	public void onActivityResult(int requestCode, int resultCode, Intent data);

	public boolean onActivityResultInner(int requestCode, int resultCode, Intent data);

	/**
	 * 统一事件处理
	 * @param id
	 * @return
     */
	public boolean onEvent(int id);

	/**
	 * Toast显示相关函数
 	 */
	public void showToast(String msg);

	public void showToast(int resId);
}
