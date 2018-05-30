/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.grjf365.gongrongpoints.activity;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.gson.Gson;
import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.config.Constant;
import com.grjf365.gongrongpoints.javascriptInterface.MyJavascriptInterface;
import com.grjf365.gongrongpoints.utils.StringUtil;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMShareAPI;

import org.apache.cordova.CordovaActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;


public class WebViewActivity extends CordovaActivity  {
	WebView webView;

//	@BindView(R.id.progressBar)
//	ProgressBar progressBar;
	private String param;
	private String url;
	private boolean showClose;

	private ValueCallback<Uri> uploadMessage;
	private ValueCallback<Uri[]> uploadMessageAboveL;
	private final static int FILE_CHOOSER_RESULT_CODE = 10000;
	private final static int RESULT_THIRD_LOGIN = 10001;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        launchUrl = getIntent().getStringExtra("url");
        launchUrl = launchUrl.replace("http://app.grjf365.com/#", "file:///android_asset/www/index.html#");

        showClose = getIntent().getBooleanExtra("showClose",false);

        init();

		webView = (WebView) appView.getView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavascriptInterface(this),"androidObject");

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

	private String initURL(String url){
		String param = "";
		if(url != null){
			Map<String,String> params = StringUtil.getUrlParams(url);
			Gson gson = new Gson();
			param = gson.toJson(params);
		}
		return param;
	}

	private void initView(){
		webView.setHorizontalScrollBarEnabled(false);//水平不显示
		webView.setVerticalScrollBarEnabled(false); //垂直不显示
		WebSettings webSettings = webView.getSettings();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webSettings.setJavaScriptEnabled(true);//启用js
		webSettings.setBlockNetworkImage(false);//解决图片不显示
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setDomStorageEnabled(true);
//		webView.getSettings().setAllowFileAccessFromFileURLs(true);
		//支持缩放，默认为true。
		webSettings.setSupportZoom(true);
		//调整图片至适合webview的大小
		webSettings.setUseWideViewPort(true);
		// 缩放至屏幕的大小
		webSettings.setLoadWithOverviewMode(true);
		//设置默认编码
		webSettings.setDefaultTextEncodingName("utf-8");
		////设置自动加载图片
		webSettings.setLoadsImagesAutomatically(true);
		webView.addJavascriptInterface(new MyJavascriptInterface(this),"androidObject");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url == null) return false;

				try {
					if (url.startsWith("http:") || url.startsWith("https:"))
					{
						view.loadUrl(url);
						return true;
					}
					else
					{
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
						startActivity(intent);
						return true;
					}
				} catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
					return false;
				}
			}


			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				String ps = initURL(url);
				String callStr = "javascript:"+ Constant.JS_FUNC_GET_PARAMS+"('"+ps+"')";
				webView.loadUrl(callStr);
				if(url.contains("login")){
					String regId = JPushInterface.getRegistrationID(WebViewActivity.this);
					JPushInterface.setAlias(WebViewActivity.this,1000,regId);
					String str = "javascript:"+Constant.JS_FUNC_DEVICE_ID+"('"+regId+"')";
					webView.loadUrl(str);
				}

//				CookieSyncManager.createInstance(activity);
//				CookieManager cookieManager = CookieManager.getInstance();
//				String cookie = cookieManager.getCookie(url);//从H5获取cookie
//				Logger.e("onPageFinished cookie :" + cookie);
//				CookieSyncManager.getInstance().sync();
			}

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//				AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//				builder.setCancelable(false);
//				builder.setTitle("提示")
//						.setMessage("网络加载失败，请重试")
//						.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								webView.reload();
//							}
//						}).setPositiveButton("取消", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						activity.finish();
//					}
//				});
//				builder.show();
            }
        });

		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if(newProgress == 100){
//					progressBar.setVisibility(View.GONE);
				}else {
//					progressBar.setVisibility(View.VISIBLE);
//					progressBar.setProgress(newProgress);
				}
			}

//			/**
//			 * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”
//			 */
//			public boolean onJsAlert(WebView view, String url, String message,
//									 final JsResult result) {
//
//				final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//				builder.setTitle("提示")
//						.setMessage(message)
//						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								result.confirm();
//							}
//						});
//
//				// 不需要绑定按键事件
//				// 屏蔽keycode等于84之类的按键
//				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//					public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//						Log.v("onJsAlert", "keyCode==" + keyCode + "event="+ event);
//						return true;
//					}
//				});
//				// 禁止响应按back键的事件
//				builder.setCancelable(false);
//				AlertDialog dialog = builder.create();
//				dialog.show();
////				result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
//				return true;
//				// return super.onJsAlert(view, url, message, result);
//			}
//
//			public boolean onJsBeforeUnload(WebView view, String url,
//											String message, JsResult result) {
//				return super.onJsBeforeUnload(view, url, message, result);
//			}
//
//			/**
//			 * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
//			 */
//			public boolean onJsConfirm(WebView view, String url, String message,
//									   final JsResult result) {
//				final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//				builder.setTitle("提示")
//						.setMessage(message)
//						.setPositiveButton("确定",new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,int which) {
//								result.confirm();
//							}
//						})
//						.setNeutralButton("取消", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int which) {
//								result.cancel();
//							}
//						});
//				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//					@Override
//					public void onCancel(DialogInterface dialog) {
//						result.cancel();
//					}
//				});
//
//				// 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
//				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//					@Override
//					public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//						Log.v("onJsConfirm", "keyCode==" + keyCode + "event="+ event);
//						return true;
//					}
//				});
//				// 禁止响应按back键的事件
//				 builder.setCancelable(false);
//				AlertDialog dialog = builder.create();
//				dialog.show();
//				return true;
//				// return super.onJsConfirm(view, url, message, result);
//			}
//
//			/**
//			 * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////”
//			 * window.prompt('请输入您的域名地址', '618119.com');
//			 */
//			public boolean onJsPrompt(WebView view, String url, String message,
//									  String defaultValue, final JsPromptResult result) {
//				final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//
//				builder.setTitle("提示").setMessage(message);
//
//				final EditText et = new EditText(view.getContext());
//				et.setSingleLine();
//				et.setText(defaultValue);
//				builder.setView(et)
//						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int which) {
//								result.confirm(et.getText().toString());
//							}
//
//						})
//						.setNeutralButton("取消", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int which) {
//								result.cancel();
//							}
//						});
//
//				// 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
//				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//					public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//						Log.v("onJsPrompt", "keyCode==" + keyCode + "event="+ event);
//						return true;
//					}
//				});
//
//				// 禁止响应按back键的事件
//				 builder.setCancelable(false);
//				AlertDialog dialog = builder.create();
//				dialog.show();
//				return true;
//				// return super.onJsPrompt(view, url, message, defaultValue,
//				// result);
//			}



			// For Android < 3.0
			public void openFileChooser(ValueCallback<Uri> valueCallback) {
				uploadMessage = valueCallback;
				openImageChooserActivity();
			}

			// For Android  >= 3.0
			public void openFileChooser(ValueCallback valueCallback, String acceptType) {
				uploadMessage = valueCallback;
				openImageChooserActivity();
			}

			//For Android  >= 4.1
			public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
				uploadMessage = valueCallback;
				openImageChooserActivity();
			}

			// For Android >= 5.0
			@Override
			public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
				uploadMessageAboveL = filePathCallback;
				openImageChooserActivity();
				return true;
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case 2000:
				if (resultCode == RESULT_OK) {
					boolean refreshParent = data.getBooleanExtra("refreshParent", false);
					if (refreshParent) {
						if (webView != null) {
							webView.reload();
						}
					}
				}
				break;
			case 1000:
				if (resultCode == RESULT_OK) {
					//扫描二维码成功
					String content = data.getStringExtra("codedContent");
					if (!TextUtils.isEmpty(content)) {
						if (content.contains("grpay") && content.startsWith("http")) {
							Intent openUrlIntent = new Intent(this, WebViewActivity.class);
							openUrlIntent.putExtra("showClose", false);
							openUrlIntent.putExtra("url", content);
							startActivity(openUrlIntent);
						} else if (content.startsWith("http")) {
							Intent openUrlIntent = new Intent(this, WebViewActivity.class);
							openUrlIntent.putExtra("showClose", true);
							openUrlIntent.putExtra("url", content);
							startActivity(openUrlIntent);
						}
					}
				}
				break;
			case RESULT_THIRD_LOGIN:
				UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
				if (resultCode == RESULT_OK) {

					String content = data.getStringExtra("codedContent");

				}
				break;
			case FILE_CHOOSER_RESULT_CODE:
				if (null == uploadMessage && null == uploadMessageAboveL) return;
				Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
				if (uploadMessageAboveL != null) {
					onActivityResultAboveL(requestCode, resultCode, data);
				} else if (uploadMessage != null) {
					uploadMessage.onReceiveValue(result);
					uploadMessage = null;
				}
				break;
		}
	}
	private void openImageChooserActivity() {
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
		i.addCategory(Intent.CATEGORY_OPENABLE);
		i.setType("image/*");
		startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
		if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
			return;
		Uri[] results = null;
		if (resultCode == RESULT_OK) {
			if (intent != null) {
				String dataString = intent.getDataString();
				ClipData clipData = intent.getClipData();
				if (clipData != null) {
					results = new Uri[clipData.getItemCount()];
					for (int i = 0; i < clipData.getItemCount(); i++) {
						ClipData.Item item = clipData.getItemAt(i);
						results[i] = item.getUri();
					}
				}
				if (dataString != null)
					results = new Uri[]{Uri.parse(dataString)};
			}
		}
		uploadMessageAboveL.onReceiveValue(results);
		uploadMessageAboveL = null;
	}


//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if(webView.canGoBack()){
//				webView.goBack();
//			}else {
//				finish();
//			}
////			if(showClose){
////				if(webView.canGoBack()){
////					webView.goBack();
////				}else {
////					finish();
////				}
////			}else {
////				finish();
////			}
//		}
//		return false;
//	}

	public void getLocation(String address,String city){
		GeocodeSearch geocodeSearch = new GeocodeSearch(this);
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
				Toast.makeText(WebViewActivity.this,""+i,Toast.LENGTH_LONG).show();
				List<GeocodeAddress> addressList = geocodeResult.getGeocodeAddressList();
				if(addressList.size() > 0){
					LatLonPoint latLonPoint = addressList.get(0).getLatLonPoint();
					Toast.makeText(WebViewActivity.this,latLonPoint.getLatitude()+" : "+latLonPoint.getLongitude(),Toast.LENGTH_LONG).show();
				}
			}
		});
//		LatLonPoint latLonPoint = new LatLonPoint(116.475271, 39.889197);
//		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500f, GeocodeSearch.AMAP);
		GeocodeQuery query = new GeocodeQuery(address,city);
		//异步查询
		geocodeSearch.getFromLocationNameAsyn(query);
	}

	public void callJs(String message) {
		if(webView != null){
			webView.loadUrl(message);
		}
	}

}
