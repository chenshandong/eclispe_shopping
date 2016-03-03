package com.example.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyViewPagerIndicator2 extends View {

	private Paint mPaint1;
	private Paint mPaint2;
	private static final int MARGIN = 8;
	private static final int CIRCLE_R = 8;
	private static final int NUMBER = 10;
	private float len;

	public MyViewPagerIndicator2(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public void moveIndicator(int position, float offset){
		len = (position+offset)*(2*CIRCLE_R+MARGIN);
		invalidate();
	}
	
	private void initPaint() {
		mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint1.setStyle(Paint.Style.STROKE);
		mPaint1.setStrokeWidth(3);
		mPaint1.setColor(Color.GRAY);
		mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint2.setColor(Color.WHITE);
		mPaint2.setTextSize(26);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = canvas.getHeight();
		int width = canvas.getWidth()/2;
		int cx = width -CIRCLE_R*(NUMBER-1) - MARGIN*((NUMBER-1)/2);
		for (int i = 0; i < NUMBER; i++) {
			canvas.drawCircle(cx+i*2*CIRCLE_R+i*MARGIN, height/2, CIRCLE_R, mPaint1);
		}
		canvas.drawCircle(cx+len, height/2, CIRCLE_R, mPaint2);
	}
}
