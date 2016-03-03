package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.example.adapter.MyAbstractAdapter;
import com.example.api.APIClient;
import com.example.demo.CartActivity;
import com.example.demo.CityActivity;
import com.example.demo.DetailsActivity;
import com.example.demo.WebActivity;
import com.example.guessmodel.Cainixihuan;
import com.example.guessmodel.Goodlist;
import com.example.model.Type;
import com.example.sd01_day.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;
import com.xinbo.widget.ViewPagerIndicator;
import com.zxing.activity.CaptureActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements OnClickListener {

	public static final int MAX_COUNT = 2000000;
	protected static final long DURATION = 3000;
	private LayoutInflater inflater;
	private ViewPagerIndicator mHomeIndicator;
	private ViewPagerIndicator mGuanggaoIndicator;
	private ListView mGuesslistview;
	private View layout;
	private Handler handler = new Handler();
	// 猜你喜欢
	private ArrayList<Goodlist> guessList = new ArrayList<>();

	public HomeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if (layout == null) {
			layout = inflater.inflate(R.layout.fragment_home, null);
			initUI(layout);
			initData();
		}
		autoscroll();
		return layout;
	}

	private void autoscroll() {
		handler.postDelayed(mCallback, DURATION);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		handler.removeCallbacks(mCallback);
	}

	private List<Type> mTypeList;
	private ViewPager mHomeguanggaoPager;
	private Runnable mCallback;
	private guessAdapter mGuessAdapter;
	private TextView home_cityname_tv;

	private void initData() {
		// types数据暂时写死
		// APIClient.getTypes(getContext(), new VolleyListener() {
		//
		//
		// @Override
		// public void onResponse(String json) {
		// HeaderType headerType = GsonUtils.parseJSON(json, HeaderType.class);
		// mTypeList = headerType.getType();
		// }
		//
		// @Override
		// public void onErrorResponse(VolleyError arg0) {
		//
		// }
		// });

		// 加载猜你喜欢listview的数据
		APIClient.getGuess(getContext(), new VolleyListener() {

			@Override
			public void onResponse(String json) {
				Cainixihuan cainixihuan = GsonUtils.parseJSON(json, Cainixihuan.class);
				List<Goodlist> goodlist = cainixihuan.getResult().getGoodlist();
				mGuessAdapter.setData(goodlist);
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private void initUI(View layout) {
		layout.findViewById(R.id.icon_shoppingcart).setOnClickListener(this);
		
		PullToRefreshListView pulltorefreshlistview = (PullToRefreshListView) layout
				.findViewById(R.id.listview_homepage);
		mGuesslistview = pulltorefreshlistview.getRefreshableView();
		// 初始化banner广告条
		initGuanggaoBanner();
		// 初始化gridviewBanner
		initTypeBanner();
		// 初始化精品抢购
		initGoodPurchase();
		mGuessAdapter = new guessAdapter();
		mGuesslistview.setAdapter(mGuessAdapter);
		mGuesslistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getContext(), DetailsActivity.class);
				Goodlist value = guessList.get(position - mGuesslistview.getHeaderViewsCount());
				intent.putExtra("guess", value);
				startActivity(intent);
			}
		});
		layout.findViewById(R.id.img_saoyisao).setOnClickListener(this);
		// TODO
		home_cityname_tv = (TextView) layout.findViewById(R.id.home_cityname_tv);
		home_cityname_tv.setOnClickListener(this);

	}

	private void initGoodPurchase() {
		View GoodpurchaseView = inflater.inflate(R.layout.layout_goodpurchase, null);
		GoodpurchaseView.findViewById(R.id.img_goodpurchase01).setOnClickListener(this);
		GoodpurchaseView.findViewById(R.id.img_goodpurchase02).setOnClickListener(this);
		GoodpurchaseView.findViewById(R.id.img_goodpurchase03).setOnClickListener(this);
		GoodpurchaseView.findViewById(R.id.img_goodpurchase04).setOnClickListener(this);
		mGuesslistview.addHeaderView(GoodpurchaseView, null, false);
	}

	private void initGuanggaoBanner() {
		View GuanggaoBanner = inflater.inflate(R.layout.headview_bannerhomeguanggao, null);
		mGuanggaoIndicator = (ViewPagerIndicator) GuanggaoBanner.findViewById(R.id.homeguanggaoindicator);
		mHomeguanggaoPager = (ViewPager) GuanggaoBanner.findViewById(R.id.homeguangao_page);
		mHomeguanggaoPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				mGuanggaoIndicator.move(0, position % 2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		mHomeguanggaoPager.setAdapter(new MyGuanggaoBannerAdapter(getChildFragmentManager()));
		mHomeguanggaoPager.setCurrentItem(MAX_COUNT / 2);
		mCallback = new Runnable() {

			@Override
			public void run() {
				int currentItem = mHomeguanggaoPager.getCurrentItem();
				mHomeguanggaoPager.setCurrentItem(currentItem + 1);
				handler.postDelayed(this, DURATION);
			}
		};
		mGuesslistview.addHeaderView(GuanggaoBanner);
	}

	private void initTypeBanner() {
		View TypeBanner = inflater.inflate(R.layout.headview_bannerhomepage, null);
		ViewPager homebannerPager = (ViewPager) TypeBanner.findViewById(R.id.homebanner_page);
		mHomeIndicator = (ViewPagerIndicator) TypeBanner.findViewById(R.id.homepageindicator);
		// 添加indicator
		homebannerPager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

			}

			@Override
			public void onPageScrolled(int position, float offset, int arg2) {
				mHomeIndicator.move(0, position % 2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		homebannerPager.setAdapter(new MyTypeBannerAdapter(getChildFragmentManager()));
		homebannerPager.setCurrentItem(MAX_COUNT / 2);
		mGuesslistview.addHeaderView(TypeBanner);
	}

	class MyGuanggaoBannerAdapter extends FragmentPagerAdapter {

		public MyGuanggaoBannerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			int index = position % 2;
			return new BannerGuanggaoFragment(index);
		}

		@Override
		public int getCount() {
			return MAX_COUNT;
		}

	}

	class MyTypeBannerAdapter extends FragmentPagerAdapter {

		public MyTypeBannerAdapter(FragmentManager fm) {
			super(fm);
		}

		// TODO
		@Override
		public Fragment getItem(int position) {
			int index = position % 2;
			return new HomeBannerFragment(index);
		}

		@Override
		public int getCount() {
			return MAX_COUNT;
		}
	}

	class guessAdapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return guessList.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = inflater.inflate(R.layout.groupon_view_list_item, null);
			TextView title_big = (TextView) layout.findViewById(R.id.groupon_listitem_title);
			TextView title_small = (TextView) layout.findViewById(R.id.groupon_listitem_subtitle);
			TextView price = (TextView) layout.findViewById(R.id.groupon_listitem_activity_groupon);
			TextView value = (TextView) layout.findViewById(R.id.groupon_listitem_activity_market);
			TextView salecount = (TextView) layout.findViewById(R.id.groupon_listitem_salecount_tuanlist);
			ImageView icon = (ImageView) layout.findViewById(R.id.groupon_listitem_icon);
			Goodlist goodlist = guessList.get(position);
			title_big.setText(goodlist.getProduct());
			title_small.setText(goodlist.getShortTitle());
			price.setText("¥" + goodlist.getPrice());
			value.setText("¥" + goodlist.getValue());
			value.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			salecount.setText("已售" + goodlist.getBought());
			UILUtils.displayImage(goodlist.getImages().get(3).getImage(), icon);
			return layout;
		}

		@Override
		public void setData(List list) {
			guessList.addAll(list);
			this.notifyDataSetChanged();
		}

	}

	// 扫描二维码点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_saoyisao:
			Toast.makeText(getContext(), "开始扫描", Toast.LENGTH_LONG).show();
			Intent openCameraIntent = new Intent(getContext(), CaptureActivity.class);
			startActivityForResult(openCameraIntent, 0);
			break;
		// TODO
		case R.id.home_cityname_tv:
			Intent intenttocity = new Intent(getContext(), CityActivity.class);
			// startActivity(intenttocity);
			startActivityForResult(intenttocity, 0);
			break;
		case R.id.img_goodpurchase01:
			Intent intent01 = new Intent(getContext(), WebActivity.class);
			intent01.putExtra("key", 1);
			startActivity(intent01);
			break;
		case R.id.img_goodpurchase02:
			Intent intent02 = new Intent(getContext(), WebActivity.class);
			intent02.putExtra("key", 2);
			startActivity(intent02);
			break;
		case R.id.img_goodpurchase03:
			Intent intent03 = new Intent(getContext(), WebActivity.class);
			intent03.putExtra("key", 1);
			startActivity(intent03);
			break;
		case R.id.img_goodpurchase04:
			Intent intent04 = new Intent(getContext(), WebActivity.class);
			intent04.putExtra("key", 3);
			startActivity(intent04);
			break;
		case R.id.icon_shoppingcart:
			Intent intentCart = new Intent(getContext(), CartActivity.class);
			startActivity(intentCart );
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("null")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == getActivity().RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
		}
		if (data != null) {

			if (resultCode == 110) {
				String cityname = data.getStringExtra("cityname");
				home_cityname_tv.setText(cityname);
			}else if(resultCode == 111){
				String hotcity = data.getStringExtra("hotcityname");
				home_cityname_tv.setText(hotcity);
				
			}else if(resultCode == 1){
				String name1 = data.getStringExtra("tv1");
				home_cityname_tv.setText(name1);
			}else if(resultCode == 2){
				String name2 = data.getStringExtra("tv2");
				home_cityname_tv.setText(name2);
			}else if(resultCode == 3){
				String name3 = data.getStringExtra("tv3");
				home_cityname_tv.setText(name3);
			}
		}
	}
}
