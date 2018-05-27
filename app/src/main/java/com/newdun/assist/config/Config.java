package com.newdun.assist.config;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import com.newdun.frame.app.BaseApp;


public class Config {
	private SharedPreferences mSharedPreferences;
	private static Config instance;
	
	private Config() {
		mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(BaseApp.getApplication());
	}
	
	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	public boolean isAutoLogin() {
		return mSharedPreferences.getBoolean("autologin", false);
	}
	
	public void setAutoLogin (boolean autologin, String username, String password) {
		Editor e = mSharedPreferences.edit();
		e.putBoolean("autologin", autologin);
		if (autologin) {
			e.putString("username", username);
			e.putString("password", password);
		}
		e.commit();
	}
	
	public String getUserName () {
		String username = mSharedPreferences.getString("username", "");
		return username;
	}

	public void setContactForOrder (String contact) {
		Editor e = mSharedPreferences.edit();
		e.putString("contactForOrder", contact);
		e.commit();
	}
	
	public String getContactForOrder () {
		String username = mSharedPreferences.getString("contactForOrder", "");
		return username;
	}

	public String getPassword () {
		String password = mSharedPreferences.getString("password", "");
		return password;
	}
	
	public void setFirstBoot(boolean bFirstBoot) {
		Editor e = mSharedPreferences.edit();
		e.putBoolean("firstboot", bFirstBoot);
		e.commit();
	}
	
	public boolean isFirstBoot() {
		return mSharedPreferences.getBoolean("firstboot", true);
	}

//	public void setDefaultCity(City city) {
//		DataCache.save(city, "defaultCity");
//	}
//
//	public City defaultCity() {
//		try {
//			City city = (City) DataCache.load(City.class, "defaultCity");
//			return city;
//		} catch (Exception e) {
//
//		}
//		return null;
//	}
//
//
//	public void setSwitchCity(boolean bSwitch) {
//		Editor e = mSharedPreferences.edit();
//		e.putBoolean("switchCity", bSwitch);
//		e.commit();
//	}
//
//	public boolean switchCity() {
//		return mSharedPreferences.getBoolean("switchCity", false);
//	}
//
//	public void setGpsCity(City city) {
//		DataCache.save(city, "gpsCity");
//	}
//
//	public City getGpsCity() {
//		try {
//			City city = (City) DataCache.load(City.class, "gpsCity");
//			return city;
//		} catch (Exception e) {
//
//		}
//		return null;
//	}
	
	public static final String UPDATE_USERINFO = "update_userinfo";
	public int updateUserInfo(int status) {
		Editor e = mSharedPreferences.edit();
		e.putInt(UPDATE_USERINFO, 100 + status);
		e.commit();
		return status;
	}
	
	public int getUserStatus() {
		int count = mSharedPreferences.getInt(UPDATE_USERINFO, 0);
		if (count >= 100)
			return count - 100;
		return -1;
	}
	
	public int updateUserInfo() {
		try {
		int count = mSharedPreferences.getInt(UPDATE_USERINFO, 0);
		count ++;
		count = count % 100;
		Editor e = mSharedPreferences.edit();
		e.putInt(UPDATE_USERINFO, count);
		e.commit();
		return count;
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	public static final String ORDER = "xorder";
	public static final int ORDER_SYSTEM = 500;

	public static final int ORDER_MESSAGE = 501;
	public static final int ORDER_RECEIVE_ORDER = 502;
	public static final int ORDER_SEND_ORDER = 503;
	public static final int ORDER_WORK= 504;
	public static final int ORDER_OTHER= 505;

	public static final String WEIXIN = "weixin_push";
	public static final String WEIXIN_ORDERID = "weixin_orderId";
	
	public void addOrder(int type) {
		Editor e = mSharedPreferences.edit();
		int count = mSharedPreferences.getInt(ORDER + type, 0);
		count ++;
		e.putInt(ORDER + type, count);
		e.commit();
	}
	
	public void setOrder(int type, int count) {
		Editor e = mSharedPreferences.edit();
		e.putInt(ORDER + type, count);
		e.commit();
	}
	
	/**保存首页的引导已经点击*/
	public void setBusinessLodingClicked(boolean isClicked) {
		Editor e = mSharedPreferences.edit();
		e.putBoolean("businesslodingfirst", isClicked);
		e.commit();
	}
	public boolean getBusinessLodingClicked() {
		return mSharedPreferences.getBoolean("businesslodingfirst", false);
	}
	
	

	public void clearOrder(int type) {
		Editor e = mSharedPreferences.edit();
		e.putInt(ORDER + type, 0);
		e.commit();
	}
	
	public int getOrderCount(int type) {
		return mSharedPreferences.getInt(ORDER + type, 0);
	}
	
	public void registerConfigChangeListener(
			OnSharedPreferenceChangeListener listener) {
		mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
	}
	
	
	/**本地保存支付宝号*/
	public void setzFBNum(String num) {
		Editor e = mSharedPreferences.edit();
		e.putString("zFBNum", num);
		e.commit();
	}
	
	public String getzFBNum() {
		return mSharedPreferences.getString("zFBNum", "");
	}
	
	/**是否打开过，导入本地通讯录页面*/
	public void setIsImported(boolean isImported) {
		Editor e = mSharedPreferences.edit();
		e.putBoolean("isImported", isImported);
		e.commit();
	}
	
	public boolean getIsImported() {
		return mSharedPreferences.getBoolean("isImported", false);
	}
	
	
	public void saveText(String text) {
		Editor e = mSharedPreferences.edit();
		e.putString("saveEdit", text);
		e.commit();
	}
	
	public String getText() {
		return mSharedPreferences.getString("saveEdit", "");
	}
	
	/**保存上次退出订单列表页面身份状态，只适用于BC*/
	public void setCheckedIdentityState(Boolean isConsultant) {
		Editor e = mSharedPreferences.edit();
		e.putBoolean("checkedIdentityState", isConsultant);
		e.commit();
	}
	
	public boolean getCheckedIdentityState() {
		return mSharedPreferences.getBoolean("checkedIdentityState", true);
	}
	
	/**保存第三方登录的类型，新浪， qq, 微信*/
	public void setThirdPartyLoginType(String thirdPartyLoginType) {
		Editor e = mSharedPreferences.edit();
		e.putString("thirdPartyLoginType", thirdPartyLoginType);
		e.commit();
	}
	
	public String getThirdPartyLoginType() {
		return mSharedPreferences.getString("thirdPartyLoginType", "");
	}
	
	/**保存微信扫码支付的push消息个数*/
	public void setWeiXinCount(int count) {
		Editor e = mSharedPreferences.edit();
		int a = getWeiXinCount();
		e.putInt(WEIXIN, getWeiXinCount() + count);
		int c = getWeiXinCount() + count;
		e.commit();
	}
	
	public int getWeiXinCount() {
		return mSharedPreferences.getInt(WEIXIN, 0);
	}
	
	/**保存微信扫码支付的订单Id*/
	public void setWeiXinOrderId(String orderId) {
		Editor e = mSharedPreferences.edit();
		e.putString(WEIXIN_ORDERID, orderId);
		e.commit();
	}
	
	public String getWeiXinOrderId() {
		return mSharedPreferences.getString(WEIXIN_ORDERID, "");
	}
	
	/**保存订单操作状态*/
	public void setOrderClearingOperation(String clearingPeration) {
		Editor e = mSharedPreferences.edit();
		e.putString("clearingPeration", clearingPeration);
		e.commit();
	}
	
	public String getOrderClearingOperation() {
		return mSharedPreferences.getString("clearingPeration", "");
	}
}
