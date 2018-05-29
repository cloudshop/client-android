package com.grjf365.gongrongpoints.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.grjf365.gongrongpoints.BaseActivity;
import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GuideActivity extends BaseActivity  {

	@BindView(R.id.viewpager)
	ViewPager mViewPager;
	/** 存放ViewPager图片地址的数组 */
	private int[] guide_drawable = { R.mipmap.yindao1, R.mipmap.yindao2, R.mipmap.yindao3 };
	/** 存放图片的集合 */
	private ArrayList<RelativeLayout> guide_imageView = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		ButterKnife.bind(this);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		init();
	}

	private void init(){
		for(int i : guide_drawable){
			RelativeLayout view = (RelativeLayout) View.inflate(activity,R.layout.item_guide,null);
			ImageView imageView = view.findViewById(R.id.imgView);
			imageView.setImageResource(i);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			guide_imageView.add(view);
		}
		mViewPager.setAdapter(new MyPagerAdapter());
		// 对viewPager最后一页设置点击监听，点击后进入主页面
		guide_imageView.get(guide_imageView.size() - 1).findViewById(R.id.iv_go).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//进入登录页
				gotoMain();
			}
		});
	}

	/** ViewPager的适配器 */
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return guide_imageView.size();
		}

		// 相当于适配器的getView
		// container:就是ViewPager
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 得到集合中的图片，添加到容器中，并返回
			RelativeLayout imageView = guide_imageView.get(position);
			if(position == 2){
				guide_imageView.get(guide_imageView.size() - 1).findViewById(R.id.iv_go).setVisibility(View.VISIBLE);
			}
			container.addView(imageView);
			return imageView;

		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		@Override
		public boolean isViewFromObject(View View, Object object) {
			return View == object;
		}
	}

	private void gotoMain(){
		SharedPreferencesUtil.setFirst(activity,false);
		startActivity(new Intent(activity,com.newdun.assist.MainActivity.class));
		finish();
	}
}