package com.grjf365.gongrongpoints.activity;


import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.bean.ResultBean;
import com.grjf365.gongrongpoints.bean.UpdateResult;
import com.grjf365.gongrongpoints.config.Constant;
import com.grjf365.gongrongpoints.fragment.BaseFragment;
import com.grjf365.gongrongpoints.permission.Acp;
import com.grjf365.gongrongpoints.permission.AcpListener;
import com.grjf365.gongrongpoints.permission.AcpOptions;
import com.grjf365.gongrongpoints.qrcode.android.CaptureActivity;
import com.grjf365.gongrongpoints.service.UpdateService;
import com.grjf365.gongrongpoints.utils.AppUtil;
import com.grjf365.gongrongpoints.utils.FileUtil;
import com.grjf365.gongrongpoints.utils.ServiceUtil;
import com.grjf365.gongrongpoints.view.HomeViewPager;
import com.grjf365.gongrongpoints.view.UpdateDialog;
import android.support.v4.app.NotificationCompat.Builder;

import com.newdun.assist.fragments.ClassifyFragment;
import com.newdun.assist.fragments.HomePageFragment;
import com.newdun.assist.fragments.MineFragment;
import com.newdun.assist.fragments.ShoppingCartFragment;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


public class MainTabActivity extends BaseActivity {

	private MainOnCheckedChangeListener onCheckedChangeListener = new MainOnCheckedChangeListener();
	private List<Fragment> fragments = new ArrayList<>();
	private BaseActivity activity;
	private HomeViewPager vp_home;
	private int currentFragment = 0;//默认显示第几个fragment
	private RadioGroup rgFoot;
	private FragmentPagerAdapter pagerAdapter;

	private UpdateService updateService;
	private TextView tvHint;
	private TextView tvCancel;
	private UpdateDialog dialog;
	private NotificationManager mNotificationManager;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		setContentView(R.layout.activity_main_old);
		initView();
		askMultiPermission();
		getUpdateInfo();
	}

	public static Fragment newInstance(int position, Class<?> c) {

		Bundle args = new Bundle();
		args.putInt("position",position);
		Fragment fragment = null;
		try {
			fragment = (Fragment) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		fragment.setArguments(args);
		return fragment;
	}

	private void initView(){
		rgFoot = (RadioGroup) findViewById(R.id.rg_main_foot);
		rgFoot.setOnCheckedChangeListener(onCheckedChangeListener);
		vp_home = (HomeViewPager) findViewById(R.id.vp_home);
		vp_home.setEnabled(false);
		fragments.add(newInstance(0, HomePageFragment.class));
		fragments.add(newInstance(1, ClassifyFragment.class));
		fragments.add(newInstance(2, HomePageFragment.class));
		fragments.add(newInstance(3, ShoppingCartFragment.class));
		fragments.add(newInstance(4, MineFragment.class));
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return fragments.get(position);
			}

			@Override
			public int getCount() {
				return fragments.size();
			}
		};
		vp_home.setOffscreenPageLimit(6);
		vp_home.setAdapter(pagerAdapter);
		vp_home.setCurrentItem(0,false);
		findViewById(R.id.rb_main_foot_gongrong).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openUrlIntent = new Intent(activity, WebViewActivity.class);
				openUrlIntent.putExtra("showClose",true);
				openUrlIntent.putExtra("url",Constant.H5_GR_URL);
				startActivityForResult(openUrlIntent,2000);
//				activity.startActivityForResult(new Intent(activity, CaptureActivity.class),1000);
			}
		});
	}
	/**
	 * 菜单切换监听
	 */
	private class MainOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
				case R.id.rb_main_foot_home:
					currentFragment = 0;
					vp_home.setCurrentItem(currentFragment,false);
					break;
				case R.id.rb_main_foot_classify:
					currentFragment = 1;
					vp_home.setCurrentItem(currentFragment,false);
					break;
				case R.id.rb_main_foot_gongrong:
					Intent openUrlIntent = new Intent(activity, WebViewActivity.class);
					openUrlIntent.putExtra("showClose",true);
					openUrlIntent.putExtra("url",Constant.H5_GR_URL);
					startActivityForResult(openUrlIntent,2000);
//					activity.startActivityForResult(new Intent(activity, CaptureActivity.class),1000);
//					currentFragment = 2;
//					vp_home.setCurrentItem(currentFragment,false);
					break;
				case R.id.rb_main_foot_shopping:
					currentFragment = 3;
					vp_home.setCurrentItem(currentFragment,false);
					break;
				case R.id.rb_main_foot_my:
					currentFragment = 4;
					vp_home.setCurrentItem(currentFragment,false);
					break;
				default:
					break;
			}
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		Logger.e("  MainActivity  onResume");
	}
	@Override
	protected void onPause() {
		super.onPause();
		Logger.e("  MainActivity  onPause");
	}
//	@Override
//	public void appCallWeb(String command, String sn,String content) {
//		//拦截fragment请求接口结果通知h5
//		switch (command){
//			case Constant.GET_BOOK_SHELF_LIST:
//			case Constant.GET_PURCHASE_BOOK_LIST:
//				if(bookcaseView != null){
//					bookcaseView.evaluateJavascript("javascript:Elf.AppCallWeb('"+sn+"','"+content+"')", null);
//				}
//				break;
//			default:
//				fragments.get(currentFragment).appCallWeb(sn,content);
//				break;
//		}
//
//	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentFragment);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		Logger.e(" onNewIntent ======= ");
		boolean isRefresh = intent.getBooleanExtra("isRefresh",false);
		if(isRefresh){
			for(Fragment fragment : fragments){
				((BaseFragment)fragment).setLoad(false);
			}
			((BaseFragment)fragments.get(currentFragment)).refreshUrl();
		}
		int finallyIndex = intent.getIntExtra("finallyIndex",0);
		int id = R.id.rb_main_foot_home;
		switch (finallyIndex){
			case 1:
				id = R.id.rb_main_foot_home;
				break;
			case 2:
				id = R.id.rb_main_foot_classify;
				break;
			case 3:
				id = R.id.rb_main_foot_shopping;
				break;
			case 4:
				id = R.id.rb_main_foot_my;
				break;
		}
		if(finallyIndex != 0){
			rgFoot.check(id);
		}
		if(finallyIndex-1 == currentFragment){
			((BaseFragment)fragments.get(currentFragment)).refreshUrl();
		}
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK){
//			(fragments.get(currentFragment)).onKey(keyCode,event);
//			return false;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	public void callJs(String message){
		((BaseFragment)getCurrentFragment()).callJs(message);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1000 && resultCode == RESULT_OK){
			//扫描二维码成功
			String content = data.getStringExtra("codedContent");
			if(!TextUtils.isEmpty(content)){
				if(content.contains("grpay") && content.startsWith("http")){
					Intent openUrlIntent = new Intent(activity, WebViewActivity.class);
					openUrlIntent.putExtra("showClose",false);
					openUrlIntent.putExtra("url",content);
					startActivity(openUrlIntent);
				}else if(content.startsWith("http")){
					Intent openUrlIntent = new Intent(activity, WebViewActivity.class);
					openUrlIntent.putExtra("showClose",true);
					openUrlIntent.putExtra("url",content);
					startActivity(openUrlIntent);
				}
			}
		}
	}

	private void askMultiPermission() {
		Acp.getInstance(this).
				request(new AcpOptions.Builder()
								.setPermissions(Manifest.permission.CAMERA
										, Manifest.permission.READ_EXTERNAL_STORAGE,
										Manifest.permission.WRITE_EXTERNAL_STORAGE,
										//                               Manifest.permission.RECEIVE_SMS,
										Manifest.permission.ACCESS_FINE_LOCATION,
										Manifest.permission.ACCESS_COARSE_LOCATION)

                /*以下为自定义提示语、按钮文字
                .setDeniedMessage()
                .setDeniedCloseBtn()
                .setDeniedSettingBtn()
                .setRationalMessage()
                .setRationalBtn()*/

								.build(),
						new AcpListener() {
							@Override
							public void onGranted() {
								//getIMEI();
							}

							@Override
							public void onDenied(List<String> permissions) {
							}
						});
	}

	private void getUpdateInfo(){
		String url = Constant.UPDATE_URL+"?version="+ AppUtil.getVersion(activity);
		RequestQueue requestQueue = Volley.newRequestQueue(activity);
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String s) {
						//String s即为服务器返回的数据
						Gson gson = new Gson();
						UpdateResult updateResult = gson.fromJson(s, UpdateResult.class);
						if(updateResult != null && updateResult.getNewestVersion() != null){
							if(updateResult.getNewestVersion().getVersionCode() > AppUtil.getVersionCode(activity)){
								boolean isforeUpdate = false;
								if(updateResult.getCurrentVersion() != null){
									isforeUpdate = updateResult.getCurrentVersion().isForceUpdate();
								}
								initPopupWindow(isforeUpdate,updateResult.getNewestVersion().getApkUrl(),updateResult.getNewestVersion().getVersion());
								tvHint.setText(updateResult.getNewestVersion().getDescription());
								dialog.show();
							}
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				if(volleyError != null){
					String string = volleyError.getMessage();
				}

			}
		});
		//3、将请求添加进请求队列
		requestQueue.add(stringRequest);
	}

	/** 初始化更新的popupwindow */
	private void initPopupWindow(final boolean forceUpdate, final String apkUrl ,final String version) {
		LayoutInflater layoutInflater = LayoutInflater.from(activity);
		View popupWindow = layoutInflater.inflate(R.layout.dialog_app_update, null);
		tvHint = (TextView) popupWindow.findViewById(R.id.tvHint);
		final TextView tvInstall = (TextView) popupWindow.findViewById(R.id.tvInstall);
		tvCancel = (TextView) popupWindow.findViewById(R.id.tvCancel);
//		ivLine = (ImageView) popupWindow.findViewById(R.id.ivLine);
		tvInstall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (forceUpdate) {
					tvInstall.setText("正在更新 ");
					tvInstall.setClickable(false);
				} else {
					if(dialog != null && dialog.isShowing()){
						dialog.dismiss();
					}
				}
				// 下载安装包
				String downloadDir;
				try {
					downloadDir = FileUtil.getSDPath(activity) + "/apk";
					File downloadDirFile = new File(downloadDir);
					if (downloadDirFile != null && !downloadDirFile.exists()) {
						downloadDirFile.mkdirs();
					}
					setUpNotification();
					bindUpdateService(apkUrl, downloadDirFile + "/gongrong_v" + version + ".apk");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		tvCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(dialog != null && dialog.isShowing()){
					dialog.dismiss();
				}
			}
		});
		dialog = new UpdateDialog(activity);
		dialog.setContentView(popupWindow);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				// 退出应用
//				if (update == null || "1".equals(update.getForceUpdate())) {
////					exit();
//				} else {
//
//				}
			}
		});
	}

	/** 绑定更新服务 */
	private void bindUpdateService(final String url, final String target) {
		Intent service = new Intent(activity, UpdateService.class);
		ServiceConnection conn = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				UpdateService.UpdateBinder updateBinder = (UpdateService.UpdateBinder) service;
				updateService = updateBinder.getService();
				updateService.startDownload(url, target);
			}
		};
		if (ServiceUtil.isServiceRunning(activity, UpdateService.class.getName())) {
			stopService(service);
			startService(service);
		} else {
			startService(service);
		}
		bindService(service, conn, 0);
	}

	/** 创建通知 */
	private void setUpNotification() {

		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);

		// 此Builder为android.support.v4.app.NotificationCompat.Builder中的，下同。
		Builder mBuilder = new Builder(activity);
		// 系统收到通知时，通知栏上面显示的文字。
		mBuilder.setTicker("开始下载");
		// 显示在通知栏上的小图标
		mBuilder.setSmallIcon(R.drawable.small_icon);
		// 设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
		mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.small_icon));
		// 通知标题
		mBuilder.setContentTitle("贡融积分.apk");
		// 通知内容
		mBuilder.setContentText("0%");

		// 设置点击一次后消失（如果没有点击事件，则该方法无效。）
		mBuilder.setAutoCancel(true);

		// 点击通知之后需要跳转的页面
		Intent notifyIntent = new Intent(activity, MainTabActivity.class);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pIntent = PendingIntent.getActivity(activity, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pIntent);

		// 设置为不可清除模式
		mBuilder.setOngoing(false);

		// 显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
		mNotificationManager.notify(1, mBuilder.build());
	}


}