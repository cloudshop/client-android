package com.newdun.frame.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.newdun.frame.R;

public class Toolbar extends android.support.v7.widget.Toolbar {
	private TextView mCustomTitleTextView;

	/**
	 * Constructor
	 */
	public Toolbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData(context);
	}

	/**
	 * Constructor
	 */
	public Toolbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData(context);
	}

	/**
	 * Constructor
	 */
	public Toolbar(Context context) {
		super(context);
		initData(context);
	}

	/**
	 * Initializes class data
	 * @param context the context
	 */
	private void initData(Context context) {
//		addView(View.inflate(context, R.layout.navagitor_bar, null));
	}

	@Override
	protected void onFinishInflate () {
		super.onFinishInflate();
		mCustomTitleTextView = (TextView)findViewById(R.id.custom_title);

//		android.support.v7.widget.Toolbar.LayoutParams p =
//				new android.support.v7.widget.Toolbar.LayoutParams(
//						android.support.v7.widget.Toolbar.LayoutParams.MATCH_PARENT,
//						android.support.v7.widget.Toolbar.LayoutParams.MATCH_PARENT);
//		mCustomTitleTextView.setLayoutParams(p);
	}

	/**
	 * Set the title of this toolbar.
	 *
	 * <p>A title should be used as the anchor for a section of content. It should
	 * describe or name the content being viewed.</p>
	 *
	 * @param title Title to set
	 */
	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(null);
		mCustomTitleTextView.setText(title);
	}
}
