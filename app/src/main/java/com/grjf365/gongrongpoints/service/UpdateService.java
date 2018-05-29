package com.grjf365.gongrongpoints.service;

import java.io.File;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.FileProvider;

import com.grjf365.gongrongpoints.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class UpdateService extends Service {

	private UpdateService INSTANCE;
	private NotificationManager mNotificationManager;
	private com.lidroid.xutils.http.HttpHandler<File> handler;
	
	@Override
	public IBinder onBind(Intent intent) {
		return new UpdateBinder();
	}

	public class UpdateBinder extends Binder {
		/**
		 * 获取当前Service的实例
		 * 
		 * @return
		 */
		public UpdateService getService() {
			return INSTANCE;
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		INSTANCE = UpdateService.this;
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		if(handler != null){
			handler.cancel();
		}
		super.onDestroy();
	}
	
	/**
	 * 开始下载
	 * 
	 * @param url
	 * @param target
	 */
	public void startDownload(String url, final String target) {
		HttpUtils http = new HttpUtils();
		handler = http.download(url, target,
		    false, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
		    new RequestCallBack<File>() {
		        @Override
		        public void onStart() {
					
		        }

				@Override
		        public void onLoading(final long total, final long current, boolean isUploading) {
					Builder mBuilder = new Builder(INSTANCE);
					// 系统收到通知时，通知栏上面显示的文字。
					mBuilder.setTicker("开始下载");
					// 通知标题
					mBuilder.setContentTitle("贡融积分.apk");
					// 通知内容
					mBuilder.setContentText((int)(current*100/total)+"%");
					mBuilder.setSmallIcon(R.drawable.small_icon);
					// 设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
					mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
							R.drawable.small_icon));
					
					mBuilder.setProgress((int)total, (int)current, false);

					//设置点击一次后消失（如果没有点击事件，则该方法无效。）
					mBuilder.setAutoCancel(true);
					
					// 点击通知之后需要跳转的页面
//					Intent notifyIntent = new Intent(INSTANCE, MainActivity.class);
//					notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					PendingIntent pIntent = PendingIntent.getActivity(INSTANCE, 0,
//							notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//					mBuilder.setContentIntent(pIntent);

					// 设置为不可清除模式
					mBuilder.setOngoing(false);

					// 显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
					mNotificationManager.notify(1, mBuilder.build());
		        }

		        @Override
		        public void onSuccess(ResponseInfo<File> responseInfo) {
		        	Builder mBuilder = new Builder(INSTANCE);
					// 通知标题
					mBuilder.setContentTitle("贡融积分.apk");
					// 通知内容
					mBuilder.setContentText("下载完成，点击安装");
					mBuilder.setSmallIcon(R.drawable.small_icon);
					// 设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
					mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
							R.drawable.small_icon));

					//设置点击一次后消失（如果没有点击事件，则该方法无效。）
					mBuilder.setAutoCancel(true);
					Uri uri = null;
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
						uri = FileProvider.getUriForFile(getApplicationContext(),getApplicationContext().getPackageName()+".FileProvider",new File(target));
					}else {
						uri = Uri.fromFile(new File(target));
					}
					Intent installIntent = new Intent(Intent.ACTION_VIEW);
					installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
					PendingIntent pIntent = PendingIntent.getActivity(INSTANCE, 0,
							installIntent, PendingIntent.FLAG_UPDATE_CURRENT);
					mBuilder.setContentIntent(pIntent);

					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setDataAndType(uri,
							"application/vnd.android.package-archive");
					startActivity(intent);

					// 设置为不可清除模式
					mBuilder.setOngoing(false);

					// 显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
					mNotificationManager.notify(1, mBuilder.build());
					Intent service = new Intent(INSTANCE, UpdateService.class);
					stopService(service);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	if(msg != null && msg.equals("maybe the file has downloaded completely")){
		        		Builder mBuilder = new Builder(INSTANCE);
						// 通知标题
						mBuilder.setContentTitle("贡融积分.apk");
						// 通知内容
						mBuilder.setContentText("下载完成，点击安装");
						mBuilder.setSmallIcon(R.drawable.small_icon);
						// 设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
						mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
								R.drawable.small_icon));

						//设置点击一次后消失（如果没有点击事件，则该方法无效。）
						mBuilder.setAutoCancel(true);
						
						Uri uri = Uri.fromFile(new File(target));
						Intent installIntent = new Intent(Intent.ACTION_VIEW);
						installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
						PendingIntent pIntent = PendingIntent.getActivity(INSTANCE, 0,
								installIntent, PendingIntent.FLAG_UPDATE_CURRENT);
						mBuilder.setContentIntent(pIntent);
						
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.setDataAndType(uri,
								"application/vnd.android.package-archive");
						startActivity(intent);

						// 设置为不可清除模式
						mBuilder.setOngoing(false);

						// 显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
						mNotificationManager.notify(1, mBuilder.build());
		        	}else{
			        	Builder mBuilder = new Builder(INSTANCE);
						// 通知标题
						mBuilder.setContentTitle("贡融积分.apk");
						// 通知内容
						mBuilder.setContentText("下载失败");
						mBuilder.setSmallIcon(R.drawable.small_icon);
						// 设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
						mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
								R.drawable.small_icon));
	
						//设置点击一次后消失（如果没有点击事件，则该方法无效。）
						mBuilder.setAutoCancel(true);
						
						// 点击通知之后需要跳转的页面
//						Intent notifyIntent = new Intent(INSTANCE, MainActivity.class);
//						notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						PendingIntent pIntent = PendingIntent.getActivity(INSTANCE, 0,
//								notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//						mBuilder.setContentIntent(pIntent);
	
						// 设置为不可清除模式
						mBuilder.setOngoing(false);
	
						// 显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
						mNotificationManager.notify(1, mBuilder.build());
		        	}
		        	Intent service = new Intent(INSTANCE, UpdateService.class);
		        	stopService(service);
		        }
		});
	}
}
