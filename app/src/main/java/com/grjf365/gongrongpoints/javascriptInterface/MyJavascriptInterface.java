package com.grjf365.gongrongpoints.javascriptInterface;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.activity.WebViewActivity;
import com.grjf365.gongrongpoints.bean.PayResult;
import com.grjf365.gongrongpoints.bean.PayWXResultBean;
import com.grjf365.gongrongpoints.bean.ResultBean;
import com.grjf365.gongrongpoints.config.Constant;
import com.grjf365.gongrongpoints.qrcode.android.CaptureActivity;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.newdun.assist.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatFavorite;
import cn.jiguang.share.wechat.WechatMoments;

/**
 * Created by vip on 2018/4/10.
 */

public class MyJavascriptInterface {
    private Context context;
    private Handler handler;
    public MyJavascriptInterface(Context context){
        this.context = context;
        handler = new MyHandler(context);
    }

   static class MyHandler extends Handler{
       private Context context;
       private WXPayReceiver wxPayReceiver;
       public MyHandler(Context context){
           this.context = context;
           wxPayReceiver = new WXPayReceiver(this, (Activity) context);
       }

        @Override
        public void handleMessage(Message msg) {
            ResultBean resultBean = (ResultBean) msg.obj;
            switch (resultBean.type){
                case Constant.FUNC_THIRD_LOGIN:
                    switch (resultBean.param) {
                        case "wechat":
                            UMShareAPI.get(context).getPlatformInfo((Activity)context, SHARE_MEDIA.WEIXIN, umAuthListener);
                            break;
                        case "alipay":
                            UMShareAPI.get(context).getPlatformInfo((Activity)context, SHARE_MEDIA.ALIPAY, umAuthListener);
                            break;
                        case "qq":
                            UMShareAPI.get(context).getPlatformInfo((Activity)context, SHARE_MEDIA.QQ, umAuthListener);
                            break;
                    }
                    break;
                case Constant.FUNC_OPEN_URL:
                    String openUrl = resultBean.result;
                    if(!openUrl.startsWith("http")){
                        openUrl = Constant.H5_BASE_URL + openUrl;
                    }
                    boolean showClose = resultBean.isShowClose;
                    Intent openUrlIntent = new Intent(context, WebViewActivity.class);
                    openUrlIntent.putExtra("showClose",showClose);
                    openUrlIntent.putExtra("url",openUrl);
                    ((Activity)context).startActivityForResult(openUrlIntent,2000);
                    break;
                case Constant.FUNC_CLOSE_CURRENT:
//                    boolean isHome = Boolean.parseBoolean(resultBean.result);
                    boolean isRefresh = resultBean.isRefresh;
                    if(isRefresh){
                        Intent intentHome = new Intent(context, MainActivity.class);
                        intentHome.putExtra("isRefresh",isRefresh);
                        intentHome.putExtra("finallyIndex",resultBean.finallyIndex);
                        context.startActivity(intentHome);
                    }else if(resultBean.finallyIndex != 0){
                        Intent intentHome = new Intent(context, MainActivity.class);
                        intentHome.putExtra("isRefresh",isRefresh);
                        intentHome.putExtra("finallyIndex",resultBean.finallyIndex);
                        context.startActivity(intentHome);
                    }else  {
                        Intent intentParent = new Intent();
                        intentParent.putExtra("refreshParent",resultBean.refreshParent);
                        ((Activity)context).setResult(2000,intentParent);
                        if(!( context instanceof MainActivity)){
                            ((Activity)context).finish();
                        }
                    }
                    break;
                case Constant.FUNC_SCAN:
                    ((Activity)context).startActivityForResult(new Intent(context, CaptureActivity.class),1000);
                    break;
                case Constant.PAY_TYPE_ALI:
                    String orderStr = resultBean.result;
                    payV2(orderStr,((Activity)context),this);
                    break;
                case Constant.PAY_TYPE_WX:
                    //微信支付，判断是否安装微信
                    IntentFilter intentFilter = new IntentFilter("com.grjf365.gongrongpoints.WXPAY");
                    context.registerReceiver(wxPayReceiver,intentFilter);
                    IWXAPI msgApi = WXAPIFactory.createWXAPI(context, Constant.PAY_WX_APP_ID);
                    if (!msgApi.isWXAppInstalled()) {
                        Toast.makeText(context, "未安装微信",Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!msgApi.isWXAppSupportAPI()) {
                        Toast.makeText(context, "微信版本过低",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    PayWXResultBean payWXResultBean = (PayWXResultBean) resultBean.resultObject;
                    PayReq req = new PayReq();
                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                    req.appId			= payWXResultBean.appid;
                    req.partnerId		= payWXResultBean.partnerid;
                    req.prepayId		= payWXResultBean.prepayid;
                    req.nonceStr		= payWXResultBean.noncestr;
                    req.timeStamp		= payWXResultBean.timestamp;
                    req.packageValue	= payWXResultBean.packageValue;
                    req.sign			= payWXResultBean.sign;
                    req.extData			= "app data"; // optional
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    msgApi.sendReq(req);
                    break;
                case Constant.PAY_TYPE_ALI_RESULT:
                    PayResult payResult = new PayResult((Map<String, String>) resultBean.resultObject);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Logger.e(resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                        String message = "javascript:"+Constant.JS_FUNC_PAY_GET_PARAMS+"('success')";
                        ((BaseActivity)context).callJs(message);
                    } else if(TextUtils.equals("6001",resultStatus)){
                        Toast.makeText(context, "支付取消", Toast.LENGTH_SHORT).show();
                        String message = "javascript:"+Constant.JS_FUNC_PAY_GET_PARAMS+"('cancel')";
                        ((BaseActivity)context).callJs(message);
                    }else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                        String message = "javascript:"+Constant.JS_FUNC_PAY_GET_PARAMS+"('failed')";
                        ((BaseActivity)context).callJs(message);
                    }
                    break;
                case Constant.PAY_TYPE_WX_RESULT:
                    //
                    if(wxPayReceiver != null){
                        context.unregisterReceiver(wxPayReceiver);
                    }
                    String payWXResult = resultBean.result;
                    String message = "javascript:"+Constant.JS_FUNC_PAY_GET_PARAMS+"('"+payWXResult+"')";
                    ((BaseActivity)context).callJs(message);
                    break;
                case Constant.FUNC_SHARE:
                   // resultBean.shareType 目前是有URL
                    share(resultBean.title,resultBean.content,resultBean.platformType,resultBean.imageUrl,resultBean.url);
                    break;
                case Constant.FUNC_SET_NEW_CITY:
                    getLocation(resultBean.city,resultBean.county,resultBean.province,context);
                    break;
            }
        }
    }


    @JavascriptInterface
    public void JSCallAndroid(String json){
//        Toast.makeText(activity,"JSCallAndroid参数: "+json,Toast.LENGTH_SHORT).show();
        Logger.e(json);
        try {
            JSONObject contentObj = new JSONObject(json);
            JSONObject paramObj = null;
            if(contentObj.has("param")){
                paramObj = contentObj.getJSONObject("param");
            }
            String type = contentObj.getString("func");
            switch (type){
                case Constant.FUNC_THIRD_LOGIN:
                    String loginType = paramObj.getString("login_type");
                    ResultBean result = new ResultBean();
                    result.type = type;
                    result.param = loginType;
                    sendResultMessage(result);
                    break;
                case Constant.FUNC_PAY:
                    String payType = paramObj.getString("payType");
                    String orderStr = paramObj.getString("orderStr");
                    ResultBean payResult = new ResultBean();
                    if(TextUtils.equals(Constant.PAY_TYPE_ALI,payType)){
                        payResult.result = orderStr;
                        payResult.status = 1000;
                        payResult.type = Constant.PAY_TYPE_ALI;
                    }else {
                        PayWXResultBean payWXResultBean = parseWXResult(orderStr);
                        payResult.resultObject = payWXResultBean;
                        payResult.status = 1000;
                        payResult.type = Constant.PAY_TYPE_WX;
                    }
                    sendResultMessage(payResult);
                    break;
                case Constant.FUNC_OPEN_URL:
//                    String orderStr = "<xml>\n" +
//                            "   <appid>wxf177c6755716fa32</appid>\n" +
//                            "   <nonce_str>GUBXaWMNcmio22v6</nonce_str>\n" +
//                            "   <package>Sign=WXPay</package>\n" +
//                            "   <partnerid>1500998061</partnerid>\n" +
//                            "   <prepayid>wx141619251008134bfcca995d1251431738</prepayid>\n" +
//                            "   <timestamp>1526286100364</timestamp>\n" +
//                            "   <sign>73CEB533C7096FF947CDF036F5C20122</sign>\n" +
//                            "</xml>";
//                    ResultBean payResult = new ResultBean();
//                    PayWXResultBean payWXResultBean = parseWXResult(orderStr);
//                    payResult.resultObject = payWXResultBean;
//                    payResult.status = 1000;
//                    payResult.type = Constant.PAY_TYPE_WX;
//                    sendResultMessage(payResult);

                    String openUrl = paramObj.getString("URL");
                    boolean isShowClose = false;
                    if(paramObj.has("showClose")){
                        isShowClose = paramObj.getBoolean("showClose");
                    }
//                    openUrl = "https://m.kuaidi100.com/index_all.html?type=zhongtong&postid=488211056017";
                    ResultBean openUrlResult = new ResultBean();
                    openUrlResult.result = openUrl;
                    openUrlResult.status = 1000;
                    openUrlResult.isShowClose = isShowClose;
                    openUrlResult.type = Constant.FUNC_OPEN_URL;
                    sendResultMessage(openUrlResult);
                    break;
                case Constant.FUNC_CLOSE_CURRENT://finallyIndex
                    boolean isHome = false;
                    boolean isRefresh = false;
                    boolean refreshParent = false;
                    int finallyIndex = 0;
//                    if(paramObj.has("backToHome")){
//                        isHome = paramObj.getBoolean("backToHome");
//                    }
                    if(paramObj.has("refreshAll")){
                        isRefresh = paramObj.getBoolean("refreshAll");
                    }
                    if(paramObj.has("finallyIndex")){
                        finallyIndex = paramObj.getInt("finallyIndex");
                    }
                    if(paramObj.has("refreshParent")){
                        refreshParent = paramObj.getBoolean("refreshParent");
                    }
                    ResultBean backResult = new ResultBean();
                    backResult.result = isHome+"";
                    backResult.isRefresh = isRefresh;
                    backResult.finallyIndex = finallyIndex;
                    backResult.refreshParent = refreshParent;
                    backResult.status = 1000;
                    backResult.type = Constant.FUNC_CLOSE_CURRENT;
                    sendResultMessage(backResult);
                    break;
                case Constant.FUNC_SCAN:
                    ResultBean scanResult = new ResultBean();
                    scanResult.result = "";
                    scanResult.status = 1000;
                    scanResult.type = Constant.FUNC_SCAN;
                    sendResultMessage(scanResult);
                    break;
                case Constant.FUNC_LOGIN:
                    final String username = paramObj.getString("username");
                    final String password = paramObj.getString("password");
                    final RequestQueue requestQueue = Volley.newRequestQueue(context);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,Constant.LOGIN_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    //String s即为服务器返回的数据
                                    Logger.i(s);
                                    ResultBean resultBean = new ResultBean();
                                    resultBean.result = s;
                                    resultBean.status = 1000;
                                    resultBean.type = Constant.FUNC_LOGIN;
                                    sendResultMessage(resultBean);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            //用HashMap来存储请求参数
                            Map<String,String> map = new HashMap<String,String>();
                            map.put("username",username);
                            map.put("password",password);
                            return map;
                        }
                    };
                    //3、将请求添加进请求队列
                    requestQueue.add(stringRequest);
                    break;
                case Constant.FUNC_SHARE:
                    String shareType = paramObj.getString("shareType");
                    String platformType = paramObj.getString("platformType");
                    String URL = paramObj.getString("URL");
                    String title = paramObj.getString("title");
                    String content = paramObj.getString("content");
                    String imageUrl = paramObj.getString("imageUrl");
                    //好友会话:WechatSession 朋友圈:TimeLine
                    if(TextUtils.equals("WechatSession",platformType)){
                        platformType = Wechat.Name;
                    }else {
                        platformType = WechatMoments.Name;
                    }
                    ResultBean resultBean = new ResultBean();
                    resultBean.type = Constant.FUNC_SHARE;
                    resultBean.shareType =shareType;
                    resultBean.platformType = platformType;
                    resultBean.url = URL;
                    resultBean.title = title;
                    resultBean.content= content;
                    resultBean.imageUrl = imageUrl;
                    sendResultMessage(resultBean);
                    break;
                case Constant.FUNC_SET_NEW_CITY:
                    //param:{province：河北省 city:邯郸市 county：复兴区}
                    String province = paramObj.getString("province");
                    String city = paramObj.getString("city");
                    String county = paramObj.getString("county");
                    ResultBean setCityBean = new ResultBean();
                    setCityBean.type = Constant.FUNC_SET_NEW_CITY;
                    setCityBean.province = province;
                    setCityBean.city = city;
                    setCityBean.county = county;
                    sendResultMessage(setCityBean);
                    break;
                case Constant.FUNC_CHECK_LOGISTICS:
                    //快递查询
                    //{"func":"checkLogistics","param":{"LogisticsNumber":"134256743212","ogisticsCode":"圆通"}}
                    String 	logisticsNumber = "";
                    if(paramObj.has("LogisticsNumber")){
                        logisticsNumber= paramObj.getString("LogisticsNumber");
                    }
                    String 	logisticsCode = "";
                    if(paramObj.has("LogisticsCode")){
                        logisticsCode = paramObj.getString("LogisticsCode");
                    }
                    ResultBean logisticsResult = new ResultBean();
                    String logisticsUrl = "https://m.kuaidi100.com/index_all.html?type="+logisticsCode+"&postid="+logisticsNumber;
                    logisticsResult.result = logisticsUrl;
                    logisticsResult.status = 1000;
                    logisticsResult.isShowClose = true;
                    logisticsResult.type = Constant.FUNC_OPEN_URL;
                    sendResultMessage(logisticsResult);
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(activity,"H5解析错误",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void sendResultMessage(ResultBean resultBean){
        Message message = Message.obtain();
        message.obj = resultBean;
        handler.sendMessage(message);
    }

    public static void sendResultMessage(ResultBean resultBean,Handler handler){
        Message message = Message.obtain();
        message.obj = resultBean;
        handler.sendMessage(message);
    }

    /**
     * 支付宝支付业务
     */
    public static void payV2(final String orderInfo, final Activity activity, final Handler handler) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                ResultBean resultBean = new ResultBean();
                resultBean.type = Constant.PAY_TYPE_ALI_RESULT;
                resultBean.resultObject = result;
                sendResultMessage(resultBean,handler);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public static void share(String title,String content,String platform ,String url,String imagePath){
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(content);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(url);
        shareParams.setImagePath("");
        JShareInterface.share(Wechat.Name, shareParams, new PlatActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, int i1, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

//    public static PayWXResultBean parseWXResult(String xml) {
//        PayWXResultBean result = new PayWXResultBean();
//
//        try {
//            InputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
//            XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
//            xmlPullParser.setInput(inputStream, "UTF-8");
//            int eventType = xmlPullParser.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                switch (eventType) {
//                    case XmlPullParser.START_DOCUMENT:
//                        break;
//                    case XmlPullParser.START_TAG:
//                        if ("BOOKS".equals(xmlPullParser.getName())) {
//                            /*
//                            <xml>
//" +
//                            "   <appid>wxf177c6755716fa32</appid>\n" +
//                            "   <nonce_str>GUBXaWMNcmio22v6</nonce_str>\n" +
//                            "   <package>Sign=WXPay</package>\n" +
//                            "   <partnerid>1500998061</partnerid>\n" +
//                            "   <prepayid>wx141619251008134bfcca995d1251431738</prepayid>\n" +
//                            "   <timestamp>1526286100364</timestamp>\n" +
//                            "   <sign>73CEB533C7096FF947CDF036F5C20122</sign>\n" +
//                            "</xml>
//                             */
//
//                        } else if ("return_code".equals(xmlPullParser.getName())) {
//                            result.resultCode = xmlPullParser.nextText();
//                        } else if ("appid".equals(xmlPullParser.getName())) {
//                            result.appid = xmlPullParser.nextText();
//                        } else if ("nonce_str".equals(xmlPullParser.getName())) {
//                            result.nonce_str = xmlPullParser.nextText();
//                        } else if ("partnerid".equals(xmlPullParser.getName())) {
//                            result.partnerid = xmlPullParser.nextText();
//                        } else if ("prepayid".equals(xmlPullParser.getName())) {
//                            result.prepayid = xmlPullParser.nextText();
//                        } else if ("timestamp".equals(xmlPullParser.getName())) {
//                            result.timestamp = xmlPullParser.nextText();
//                        } else if ("sign".equals(xmlPullParser.getName())) {
//                            result.sign = xmlPullParser.nextText();
//                        }
//                        break;
//                    case XmlPullParser.END_TAG:
//
//                        break;
//                }
//                eventType = xmlPullParser.next();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public static PayWXResultBean parseWXResult(String json) {
        /*
        {"appid":"wxf177c6755716fa32","partnerid":"1500998061","package":"Sign=WXPay","noncestr":"EmgiIiBR4Hbf6RVl",
        "timestamp":1526984101,"prepayid":"wx22181501116483ada83cf6ac2447536252","sign":"CD494FE8998F5B5A87926F482BF5D16F"}";
    };
}
         */
        PayWXResultBean result = new PayWXResultBean();
        try {
            JSONObject object = new JSONObject(json);
            result.appid = object.getString("appid");
            result.partnerid = object.getString("partnerid");
            result.packageValue = object.getString("package");
            result.noncestr = object.getString("noncestr");
            result.timestamp = object.getString("timestamp");
            result.prepayid = object.getString("prepayid");
            result.sign = object.getString("sign");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static class WXPayReceiver extends BroadcastReceiver{

        private Handler handler;
        private Activity activity;

        public WXPayReceiver(Handler handler,Activity activity){
            this.handler = handler;
            this.activity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(TextUtils.equals("com.grjf365.gongrongpoints.WXPAY",action)){
                String result = intent.getStringExtra("payResult");
                ResultBean resultBean = new ResultBean();
                resultBean.type = Constant.PAY_TYPE_WX_RESULT;
                switch (result){
                    case "0":
                        Toast.makeText(activity,"微信支付成功",Toast.LENGTH_SHORT).show();
                        resultBean.result = "success";
                        break;
                    case "-1":
                        Toast.makeText(activity,"微信支付错误",Toast.LENGTH_SHORT).show();
                        resultBean.result = "failed";
                        break;

                    case "-2":
                        Toast.makeText(activity,"微信支付取消",Toast.LENGTH_SHORT).show();
                        resultBean.result = "cancel";
                        break;

                    default:
                        Toast.makeText(activity,"未知错误",Toast.LENGTH_SHORT).show();
                        resultBean.result = "failed";
                        break;
                }
                sendResultMessage(resultBean,handler);
            }
        }
    }

    public static void getLocation(String city, final String county, String province, final Context context){
        GeocodeSearch geocodeSearch = new GeocodeSearch(context);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
//				RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
//				String formatAddress = regeocodeAddress.getFormatAddress();
//				String simpleAddress = formatAddress.substring(9);
//				Toast.makeText(activity,"查询经纬度对应详细地址："+simpleAddress,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
//                Toast.makeText(activity,""+i,Toast.LENGTH_LONG).show();
                List<GeocodeAddress> addressList = geocodeResult.getGeocodeAddressList();
                if(addressList.size() > 0){
                    LatLonPoint latLonPoint = addressList.get(0).getLatLonPoint();
                    Logger.e(latLonPoint.getLongitude() +" : "+ latLonPoint.getLatitude());
                    String message = "javascript:"+Constant.JS_FUNC_LOCATION+"('"+latLonPoint.getLongitude()+"','"+latLonPoint.getLatitude()+"','"+county+"')";
                    ((BaseActivity)context).callJs(message);
                }
            }
        });
//		LatLonPoint latLonPoint = new LatLonPoint(116.475271, 39.889197);
//		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500f, GeocodeSearch.AMAP);
        String address = city+county;
        GeocodeQuery query = new GeocodeQuery(address,province);
        //异步查询
        geocodeSearch.getFromLocationNameAsyn(query);
    }

    static private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
            Logger.d("Authorize began");
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            Logger.d("Authorize succeed");
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
            Logger.d("Authorize fail");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
            Logger.d("Authorize cancel");
        }
    };

}
