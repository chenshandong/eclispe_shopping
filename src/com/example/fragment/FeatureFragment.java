package com.example.fragment;

import java.util.List;

import com.android.volley.VolleyError;
import com.example.api.APIClient;
import com.example.demo.MainActivity;
import com.example.homepagemodel.Bannerimage;
import com.example.homepagemodel.Datum;
import com.example.sd01_day.R;
import com.example.widget.MyViewPagerIndicator2;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;
import com.zxing.activity.CaptureActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;
import me.relex.seamlessviewpagerheader.tools.ScrollableListener;
import me.relex.seamlessviewpagerheader.tools.ViewPagerHeaderHelper;
import me.relex.seamlessviewpagerheader.widget.SlidingTabLayout;
import me.relex.seamlessviewpagerheader.widget.TouchCallbackLayout;

/**
 * Created by kaede on 2015/5/13.
 */
public class FeatureFragment extends Fragment
		implements TouchCallbackLayout.TouchEventListener, ViewPagerHeaderHelper.OnViewPagerTouchListener, OnClickListener {

	private static final long DEFAULT_DURATION = 300L;
	private static final float DEFAULT_DAMPING = 1.5f;

	private ViewPager mViewPager;
	private View mHeaderLayoutView;
	private ViewPagerHeaderHelper mViewPagerHeaderHelper;

	private int mTouchSlop;
	private int mTabHeight;
	private int mHeaderHeight;

	public static final int MAX_SIZE = 200000;
	protected static final long DURATION = 3000;
	private Runnable mCallback;
	private boolean isDragging;
	private Handler handler = new Handler();
	private List<Datum> bannerlist;

	private Interpolator mInterpolator = new DecelerateInterpolator();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if(view == null){
			view = inflater.inflate(R.layout.fragment_feature2, container, false);
			initData();
			initUI();
		}
		autoScroll();
		return view;
	}

	private void initUI() {
		
		mIndicator = (MyViewPagerIndicator2) view.findViewById(R.id.feature_indicator);
		
		mActivity = (MainActivity) getActivity();
		// 添加banner广告条
		mViewpagerBanner = (ViewPager) view.findViewById(R.id.viewpager_banner);
		mViewpagerBanner.setAdapter(new bannerAdapter(getChildFragmentManager()));
		mViewpagerBanner.setCurrentItem(MAX_SIZE / 2);
		mViewpagerBanner.addOnPageChangeListener(new MybannerpagerChangelistener());
		mCallback = new Runnable() {
			@Override
			public void run() {
				if (!isDragging) {
					int item = mViewpagerBanner.getCurrentItem();
					mViewpagerBanner.setCurrentItem(item + 1);
				}
				handler.postDelayed(this, DURATION);
			}
		};
		
		//注册home键点击事件
		view.findViewById(R.id.featured_titlebar_home).setOnClickListener(this);
		view.findViewById(R.id.featured_titlebar_scanner).setOnClickListener(this);
		
		mTouchSlop = ViewConfiguration.get(this.getActivity()).getScaledTouchSlop();
		mTabHeight = getResources().getDimensionPixelSize(R.dimen.tabs_height);
		mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.viewpager_header_height);

		mViewPagerHeaderHelper = new ViewPagerHeaderHelper(this.getActivity(), this);

		TouchCallbackLayout touchCallbackLayout = (TouchCallbackLayout) view.findViewById(R.id.layout);
		touchCallbackLayout.setTouchEventListener(this);

		mHeaderLayoutView = view.findViewById(R.id.header);

		slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.tabs);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
		mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
		
		slidingTabLayout.setViewPager(mViewPager);

		ViewCompat.setTranslationY(mViewPager, mHeaderHeight);
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		handler.removeCallbacks(mCallback);
	}

	private void initData() {
		APIClient.getBanner(getContext(), new VolleyListener() {

			@Override
			public void onResponse(String json) {
				Bannerimage bannerimage = GsonUtils.parseJSON(json, Bannerimage.class);
				bannerlist = bannerimage.getData();
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private final class MybannerpagerChangelistener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {

		}

		@Override
		public void onPageScrolled(int position, float offset, int arg2) {
			mIndicator.moveIndicator(position%10, offset);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			switch (state) {
			case ViewPager.SCROLL_STATE_IDLE:
				isDragging = false;
				break;
			case ViewPager.SCROLL_STATE_DRAGGING:
				isDragging = true;
				break;
			case ViewPager.SCROLL_STATE_SETTLING:
				isDragging = false;
				break;
			default:
				break;
			}
		}
	}

	private void autoScroll() {
		handler.postDelayed(mCallback, DURATION);
	}

	class bannerAdapter extends FragmentStatePagerAdapter {

		public bannerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			int index = position % 10;
			Datum datum = null;
			if (bannerlist != null) {
				datum = bannerlist.get(index);
			}
			return new BannerFeatureFragment(index, datum);
		}

		@Override
		public int getCount() {
			return MAX_SIZE;
		}

	}

	@Override
	public boolean onLayoutInterceptTouchEvent(MotionEvent event) {

		return mViewPagerHeaderHelper.onLayoutInterceptTouchEvent(event, mTabHeight + mHeaderHeight);
	}

	@Override
	public boolean onLayoutTouchEvent(MotionEvent event) {
		return mViewPagerHeaderHelper.onLayoutTouchEvent(event);
	}

	@Override
	public boolean isViewBeingDragged(MotionEvent event) {
		MainActivity activity = (MainActivity) getActivity();
		ScrollableListener listener = activity.getScrollableListeners().valueAt(mViewPager.getCurrentItem());
		if (listener == null)
			return true;
		return listener.isViewBeingDragged(event);
	}

	@Override
	public void onMoveStarted(float y) {

	}

	@Override
	public void onMove(float y, float yDx) {
		float headerTranslationY = ViewCompat.getTranslationY(mHeaderLayoutView) + yDx;
		if (headerTranslationY >= 0) { // pull end
			headerExpand(0L);

			// Log.d("kaede", "pull end");
			if (countPullEnd >= 1) {
				if (countPullEnd == 1) {
					downtime = SystemClock.uptimeMillis();
					simulateTouchEvent(mViewPager, downtime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 250,
							y + mHeaderHeight);
				}
				simulateTouchEvent(mViewPager, downtime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, 250,
						y + mHeaderHeight);
			}
			countPullEnd++;

		} else if (headerTranslationY <= -mHeaderHeight) { // push end
			headerFold(0L);

			// Log.d("kaede", "push end");
			// Log.d("kaede", "kaede onMove
			// y="+y+",yDx="+yDx+",headerTranslationY="+headerTranslationY);
			if (countPushEnd >= 1) {
				if (countPushEnd == 1) {
					downtime = SystemClock.uptimeMillis();
					simulateTouchEvent(mViewPager, downtime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 250,
							y + mHeaderHeight);
				}
				simulateTouchEvent(mViewPager, downtime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, 250,
						y + mHeaderHeight);
			}
			countPushEnd++;

		} else {

			// Log.d("kaede", "ing");
			/*
			 * if(!isHasDispatchDown3){
			 * simulateTouchEvent(mViewPager,SystemClock.uptimeMillis(),
			 * SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 250,
			 * y+mHeaderHeight); isHasDispatchDown3=true; }
			 */

			ViewCompat.animate(mHeaderLayoutView).translationY(headerTranslationY).setDuration(0).start();
			ViewCompat.animate(mViewPager).translationY(headerTranslationY + mHeaderHeight).setDuration(0).start();
		}
	}

	long downtime = -1;
	int countPushEnd = 0, countPullEnd = 0;
	private ViewPager mViewpagerBanner;
	private SlidingTabLayout slidingTabLayout;
	private View view;
	private MainActivity mActivity;
	private MyViewPagerIndicator2 mIndicator;

	private void simulateTouchEvent(View dispatcher, long downTime, long eventTime, int action, float x, float y) {
		MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, x,
				y, 0);
		try {
			dispatcher.dispatchTouchEvent(motionEvent);
		} catch (Throwable e) {
			Log.e("kaede", "simulateTouchEvent error: " + e.toString());
		} finally {
			motionEvent.recycle();
		}
	}

	@Override
	public void onMoveEnded(boolean isFling, float flingVelocityY) {

		// Log.d("kaede", "move end");
		countPushEnd = countPullEnd = 0;

		float headerY = ViewCompat.getTranslationY(mHeaderLayoutView); // 0到负数
		if (headerY == 0 || headerY == -mHeaderHeight) {
			return;
		}

		if (mViewPagerHeaderHelper.getInitialMotionY() - mViewPagerHeaderHelper.getLastMotionY() < -mTouchSlop) { // pull
																													// >
																													// mTouchSlop
																													// =
																													// expand
			headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
		} else if (mViewPagerHeaderHelper.getInitialMotionY() - mViewPagerHeaderHelper.getLastMotionY() > mTouchSlop) { // push
																														// >
																														// mTouchSlop
																														// =
																														// fold
			headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
		} else {
			if (headerY > -mHeaderHeight / 2f) { // headerY > header/2 = expand
				headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
			} else { // headerY < header/2= fold
				headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
			}
		}
	}

	private long headerMoveDuration(boolean isExpand, float currentHeaderY, boolean isFling, float velocityY) {

		long defaultDuration = DEFAULT_DURATION;

		if (isFling) {

			float distance = isExpand ? Math.abs(mHeaderHeight) - Math.abs(currentHeaderY) : Math.abs(currentHeaderY);
			velocityY = Math.abs(velocityY) / 1000;

			defaultDuration = (long) (distance / velocityY * DEFAULT_DAMPING);

			defaultDuration = defaultDuration > DEFAULT_DURATION ? DEFAULT_DURATION : defaultDuration;
		}

		return defaultDuration;
	}

	private void headerFold(long duration) {
		ViewCompat.animate(mHeaderLayoutView).translationY(-mHeaderHeight).setDuration(duration)
				.setInterpolator(mInterpolator).start();

		ViewCompat.animate(mViewPager).translationY(0).setDuration(duration).setInterpolator(mInterpolator).start();

		mViewPagerHeaderHelper.setHeaderExpand(false);
	}

	private void headerExpand(long duration) {
		ViewCompat.animate(mHeaderLayoutView).translationY(0).setDuration(duration).setInterpolator(mInterpolator)
				.start();

		ViewCompat.animate(mViewPager).translationY(mHeaderHeight).setDuration(duration).setInterpolator(mInterpolator)
				.start();
		mViewPagerHeaderHelper.setHeaderExpand(true);
	}

	private class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			return FeatureOneFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.tab_country);
			case 1:
				return getString(R.string.tab_continent);
			case 2:
				return getString(R.string.tab_city);
			}

			return "";
		}

	}

	//TODO
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.featured_titlebar_home:
			mActivity.activeHome();
			break;
		case R.id.featured_titlebar_scanner:
			Toast.makeText(getContext(), "开始扫描", Toast.LENGTH_LONG).show();
			Intent openCameraIntent = new Intent(getContext(), CaptureActivity.class);
			startActivityForResult(openCameraIntent , 0);
			break;
		default:
			break;
		}
	}
	//扫描之后返回
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == getActivity().RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
		}
	}
}
