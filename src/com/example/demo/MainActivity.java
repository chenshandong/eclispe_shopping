package com.example.demo;

import com.example.fragment.FeatureFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.MineFragment;
import com.example.fragment.NearbyFragment;
import com.example.sd01_day.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.relex.seamlessviewpagerheader.tools.ScrollableFragmentListener;
import me.relex.seamlessviewpagerheader.tools.ScrollableListener;

public class MainActivity extends FragmentActivity implements ScrollableFragmentListener {
	public static SparseArrayCompat<ScrollableListener> mScrollableListenerArrays = new SparseArrayCompat<>();

	private FragmentTabHost mTabHost;
	private String[] letters = { "simple", "contacts", "custom", "throttle" };
	private Class[] clas = { HomeFragment.class, NearbyFragment.class, FeatureFragment.class, MineFragment.class};
	private int[] imgId = { R.drawable.selector_home, R.drawable.selector_nearby, R.drawable.selector_featured,
			R.drawable.selector_mine };
	private String[] indicatorLetters = { "首页", "附近", "精选", "我的" };

	public void activeHome(){
		mTabHost.setCurrentTab(0);
	}
	public void activeFeature(){
		mTabHost.setCurrentTab(2);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		for (int i = 0; i < indicatorLetters.length; i++) {
			View layout = getLayoutInflater().inflate(R.layout.layout_tabs, null);
			ImageView img = (ImageView) layout.findViewById(R.id.imageView1);
			TextView text = (TextView) layout.findViewById(R.id.textView1);
			img.setImageResource(imgId[i]);
			text.setText(indicatorLetters[i]);
			mTabHost.addTab(mTabHost.newTabSpec(letters[i]).setIndicator(layout), clas[i], null);
		}
	}

	@Override
	public void onFragmentAttached(ScrollableListener listener, int position) {
		mScrollableListenerArrays.put(position, listener);
	}

	@Override
	public void onFragmentDetached(ScrollableListener listener, int position) {
		mScrollableListenerArrays.remove(position);
	}

	public SparseArrayCompat<ScrollableListener> getScrollableListeners() {
		return mScrollableListenerArrays;
	}
}
