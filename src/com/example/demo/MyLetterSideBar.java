package com.example.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyLetterSideBar extends View {

	private Paint mPaint;
	private String[] letter = new String[] { "#", "$" , "*" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" , "I" , "J"
			, "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" , "U" , "V" , "W" , "X" , "Y" , "Z" };
	private int everh;
	private Paint mPaint2;
	private int index = -1;
	private onTouchScrollListener listener;

	public MyLetterSideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setTextSize(25);
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint2 = new Paint();
		mPaint2.setColor(Color.RED);
		mPaint2.setTextSize(25);
		mPaint2.setTextAlign(Paint.Align.CENTER);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float y = event.getY();
		if(y<0 || y>letter.length*everh){
			listener.stopScroll();
			index = (int) (y/everh);
			index = -1;
			invalidate();
			return true;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			index = (int) (y/everh);
			listener.onTouchCroll(letter[index]);
			break;
		case MotionEvent.ACTION_UP:
			index = -1;
			listener.stopScroll();
			break;

		default:
			break;
		}
		invalidate();
		return true;
		
	}

	public void setOnTouchScrollListener(onTouchScrollListener listener){
		this.listener = listener;
	}
	
	public interface onTouchScrollListener{
		void onTouchCroll(String letter);
		void stopScroll();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = canvas.getHeight();
		int width = canvas.getWidth();
		everh = height/letter.length;
		for (int i = 0; i < letter.length; i++) {
			if(index == i){
				canvas.drawText(letter[i], width / 2, (i+1)* everh, mPaint2);
			}else{
				canvas.drawText(letter[i], width / 2, (i+1)* everh, mPaint);
			}
		}
	}


}
