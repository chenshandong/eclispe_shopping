package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {



	private OnMyscrollChangedlistener listener;

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		listener.OnMyscrollChanged(t);
	}

	public interface OnMyscrollChangedlistener{
		void  OnMyscrollChanged(int y);
	}
	
	public  void setOnMyscrollChanged (OnMyscrollChangedlistener listener){
		this.listener = listener;
	}
}
