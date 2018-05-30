package com.grjf365.gongrongpoints.config;

/**
 * Created by vip on 2018/3/30.
 */

public class Constant {

    public static final boolean DEBUG = true;

//    public static final String H5_BASE_URL = "http://192.168.1.102:8888";
//    public static final String H5_BASE_URL = "http://cloud.eyun.online:8888";
    public static final String H5_BASE_URL = "http://app.grjf365.com";

//    public static final String BASE_URL = "http://cloud.eyun.online:9080";
    public static final String BASE_URL = "http://app.grjf365.com:9080";


    public static final String LOGIN_URL = BASE_URL+"/auth/login";
    public static final String UPDATE_URL = BASE_URL+"/appmanager/api/version/android";

    public static final String H5_LOGIN_URL = H5_BASE_URL+"/#/Login";
//    public static final String H5_HOMEPAGE_URL = H5_BASE_URL+"/#/HomePage";
    public static final String H5_HOMEPAGE_URL = H5_BASE_URL+"/homepage/";
    public static final String H5_CLASSIFY_URL = H5_BASE_URL+"/#/Classify";
    public static final String H5_SHOPPING_URL = H5_BASE_URL+"/#/Shopping";
    public static final String H5_MINE_URL = H5_BASE_URL+"/#/Mine";
    public static final String H5_NEWS_URL = H5_BASE_URL+ "/#/News";
    public static final String H5_GR_URL = H5_BASE_URL+ "/#/myQRCode  ";

    public static final String FUNC_PAY = "pay";
    public static final String FUNC_LOGIN = "login";
    public static final String FUNC_THIRD_LOGIN = "third_login";
    public static final String FUNC_OPEN_URL = "openURL";
    public static final String FUNC_CLOSE_CURRENT = "closeCurrent";
    public static final String FUNC_SCAN = "scan";
    public static final String FUNC_SHARE = "share";
    public static final String FUNC_SET_NEW_CITY = "setNewCity";
    public static final String FUNC_CHECK_LOGISTICS = "checkLogistics";


    public static final String JS_FUNC_GEOGRAPHICAL_LOCATION = "GeographicalLocation";//定位经纬度
    public static final String JS_FUNC_GET_PARAMS = "GetParams";//url跳转参数
    public static final String JS_FUNC_PAY_GET_PARAMS = "payStatus";//支付成功后调用
    public static final String JS_FUNC_DEVICE_ID = "setDeviceId";//极光设备号
    public static final String JS_FUNC_LOCATION = "GeographicalLocation";//地址转经纬度

    public static final String PAY_TYPE_ALI = "Ali";//支付类型支付宝
    public static final String PAY_TYPE_ALI_RESULT = "AliResult";//支付类型支付宝
    public static final String PAY_TYPE_WX = "Wechat";//支付类型微信
    public static final String PAY_TYPE_WX_RESULT = "WechatResult";//支付类型微信
    public static final String PAY_WX_APP_ID = "wxf177c6755716fa32";//商户id


}
