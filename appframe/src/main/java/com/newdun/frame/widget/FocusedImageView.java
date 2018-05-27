package com.newdun.frame.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FocusedImageView extends ImageView {
	private boolean mSelected = false;
	private Paint paint = new Paint();

	public FocusedImageView(Context context) {
		super(context);
	}

	public FocusedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FocusedImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mSelected == true) {
			RectF rect = new RectF(canvas.getClipBounds());
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(getPaddingBottom());
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawRoundRect(rect, 10, 10, paint);
		}
	}

	public void setSelected(boolean selected) {
		if (this.mSelected != selected) {
			this.mSelected = selected;
		}
		this.invalidate();
	}
}
