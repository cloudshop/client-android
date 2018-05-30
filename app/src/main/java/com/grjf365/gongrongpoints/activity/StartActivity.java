package com.grjf365.gongrongpoints.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StartActivity extends BaseActivity  {

	@BindView(R.id.imageView)
	ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		ButterKnife.bind(this);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		Glide.with(this).load(R.mipmap.start_image).asGif().error(R.drawable.start_bg).into(imageView);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				stop();
			}
		}, 2500);
	}

	public void stop() {
		Intent intent = null;
		if (SharedPreferencesUtil.isFirst(activity)) {
			intent = new Intent(activity,GuideActivity.class);
		} else {
			intent = new Intent(activity, MainTabActivity.class);
		}
		this.startActivity(intent);
		finish();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}

}