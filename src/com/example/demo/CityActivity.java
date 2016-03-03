package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.example.adapter.MyAbstractAdapter;
import com.example.citymodel.Allcity;
import com.example.citymodel.Cities;
import com.example.citymodel.Hotcity;
import com.example.demo.MyLetterSideBar.onTouchScrollListener;
import com.example.sd01_day.R;
import com.example.utils.DBUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CityActivity extends Activity implements onTouchScrollListener, OnClickListener {

	private final class MyTextWatcher implements TextWatcher {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			ListSearch.clear();
			if (s.toString() == null || "".equals(s.toString())) {
				mPullToRefreshListView.setVisibility(View.VISIBLE);
				mSearchListView.setVisibility(View.GONE);
				mLayoutResult.setVisibility(View.GONE);
			} else {
				mPullToRefreshListView.setVisibility(View.INVISIBLE);
				mSearchListView.setVisibility(View.VISIBLE);
				mLayoutResult.setVisibility(View.VISIBLE);
				for (int i = 0; i < ListCities.size(); i++) {
					Allcity allcity = ListCities.get(i);
					String pinyin = allcity.getPinyin();
					String name = allcity.getName();
					if (pinyin.length() >= s.length()) {
						String substring = pinyin.substring(0, s.length());
						if (s.toString().equalsIgnoreCase(substring.toString())) {
							ListSearch.add(allcity);
						}
					}
					if (name.length() >= s.length()) {
						String substring = name.substring(0, s.length());
						if(s.toString().equals(substring.toString())){
							ListSearch.add(allcity);
						}
					}
				}
				if (ListSearch.size() > 0) {
					mTextresult.setText("搜索结果");
				} else {
					mTextresult.setText("暂未查询到结果！");
				}
				myRealSearchAdapter.notifyDataSetChanged();
			}
			// 必须加上这句话，容器清空，必须通知适配器
			myRealSearchAdapter.notifyDataSetChanged();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	}

	class CitiAdapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return ListCities.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.cityname = (TextView) convertView.findViewById(R.id.tv_cityname);
				viewHolder.alphabet = (TextView) convertView.findViewById(R.id.tv_alphabet);
				viewHolder.linear_alpha = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			Allcity nowCity = ListCities.get(position);
			if (position == 0) {
				viewHolder.linear_alpha.setVisibility(View.VISIBLE);
				viewHolder.alphabet.setText(nowCity.getPinyin().substring(0, 1).toUpperCase());
			} else {
				Allcity beforeCity = ListCities.get(position - 1);
				if (beforeCity.getPinyin() == null
						|| nowCity.getPinyin().charAt(0) != beforeCity.getPinyin().charAt(0)) {
					viewHolder.linear_alpha.setVisibility(View.VISIBLE);
					viewHolder.alphabet.setText(nowCity.getPinyin().substring(0, 1).toUpperCase());
				} else {
					viewHolder.linear_alpha.setVisibility(View.GONE);
				}
			}
			viewHolder.cityname.setText(nowCity.getName());
			return convertView;
		}

		@Override
		public void setData(List list) {
			ListCities.addAll(list);
			notifyDataSetChanged();
		}

	}

	class ViewHolder {
		TextView cityname;
		TextView alphabet;
		LinearLayout linear_alpha;
	}

	private static final int TV1 = 1;
	private static final int TV2 = 2;
	private static final int TV3 = 3;

//	private String url = "http://192.168.56.1:8080/cities.txt";
	private String url = "http://7xptcs.com1.z0.glb.clouddn.com/cities.txt";
	
	private ArrayList<Allcity> ListCities = new ArrayList<>();
	private ArrayList<Allcity> ListSearch = new ArrayList<>();
	private ArrayList<Hotcity> hotlist = new ArrayList<>();
	private CitiAdapter mAdapter;
	private MyLetterSideBar mLetterBar;
	private TextView mLetterText;
	private TextView recentA;
	private TextView recentB;
	private TextView recentC;
	private myGridAdapter myGridAdapter;
	private ListView mListView;
	private PullToRefreshListView mPullToRefreshListView;
	private EditText mEditsearch;
	private PullToRefreshListView mSearchListView;
	private ListView mRealSearchListview;
	private myRealSearchAdapter myRealSearchAdapter;
	private LinearLayout mLayoutResult;
	private TextView mTextresult;

	private TextView tvrecent1;

	private TextView tvrecent2;

	private TextView tvrecent3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		initUI();
		initData();
	}

	private void initData() {
		HTTPUtils.get(this, url, new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				Cities cities = GsonUtils.parseJSON(arg0, Cities.class);
				List<Allcity> listAllcities = cities.getAllcity();
				List<Hotcity> hotcitylist = cities.getHotcity();
				mAdapter.setData(listAllcities);
				myGridAdapter.setData(hotcitylist);
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private void initUI() {
		// 添加下拉刷新
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listview_allcities);
		mListView = mPullToRefreshListView.getRefreshableView();
		mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {

			}
		});
		mAdapter = new CitiAdapter();
		initHeadview();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int index = position - mListView.getHeaderViewsCount();
				if (index >= 0) {
					DBUtils.saveRecent(ListCities.get(index));
					initRecent();
					Intent data = new Intent();
					data.putExtra("cityname", ListCities.get(index).getName());
					setResult(110, data );
					finish();
				}
			}
		});
		mLetterBar = (MyLetterSideBar) findViewById(R.id.myLetterSideBar1);
		mLetterText = (TextView) findViewById(R.id.tv_suoyi);
		mLetterBar.setOnTouchScrollListener(this);
		// 添加搜索
		mEditsearch = (EditText) findViewById(R.id.edit_search);
		mSearchListView = (PullToRefreshListView) findViewById(R.id.listview_search);
		// 搜索结果
		mLayoutResult = (LinearLayout) findViewById(R.id.linear_searchresult);
		mTextresult = (TextView) findViewById(R.id.tv_searchresult);
		mSearchListView.setVisibility(View.GONE);
		// 得到真正的listview
		mRealSearchListview = mSearchListView.getRefreshableView();
		myRealSearchAdapter = new myRealSearchAdapter();
		mRealSearchListview.setAdapter(myRealSearchAdapter);
		MyTextWatcher watcher = new MyTextWatcher();
		mEditsearch.addTextChangedListener(watcher);

		//设置最近点击事件
		findViewById(R.id.rela_recentfirst).setOnClickListener(this);
		findViewById(R.id.rela_recentsecond).setOnClickListener(this);
		findViewById(R.id.rela_recentthird).setOnClickListener(this);
		tvrecent1 = (TextView) findViewById(R.id.tv_recentfirst);
		tvrecent2 = (TextView) findViewById(R.id.tv_recentsecond);
		tvrecent3 = (TextView) findViewById(R.id.tv_recentthird);
	}

	class myRealSearchAdapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return ListSearch.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_search_item, null);
				viewHolder = new ViewHolder();
				viewHolder.cityname = (TextView) convertView.findViewById(R.id.tv_cityname);
				viewHolder.alphabet = (TextView) convertView.findViewById(R.id.tv_alphabet);
				viewHolder.linear_alpha = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			Allcity nowCity = ListSearch.get(position);
			viewHolder.cityname.setText(nowCity.getName());
			return convertView;
		}

		@Override
		public void setData(List list) {
			ListSearch.addAll(list);
			notifyDataSetChanged();
		}
	}

	// 暂时添加下拉动画延时
	private class GetDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return "aaaa";
		}

		@Override
		protected void onPostExecute(String result) {
			mAdapter.notifyDataSetChanged();

			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	private void initHeadview() {
		View headview = getLayoutInflater().inflate(R.layout.head_grid_item, null);
		View hotcity = getLayoutInflater().inflate(R.layout.headview_gridview, null);
		recentA = (TextView) headview.findViewById(R.id.tv_recentfirst);
		recentB = (TextView) headview.findViewById(R.id.tv_recentsecond);
		recentC = (TextView) headview.findViewById(R.id.tv_recentthird);
		initRecent();
		mListView.addHeaderView(headview);
		addhotcity(hotcity);
		mListView.addHeaderView(hotcity);
	}

	private void addhotcity(View hotcity) {
		MyGridVierw myGridview = (MyGridVierw) hotcity.findViewById(R.id.myGridVierw1);
		myGridAdapter = new myGridAdapter();
		myGridview.setAdapter(myGridAdapter);
		myGridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for (int i = 0; i < ListCities.size(); i++) {
					if (ListCities.get(i).getName().equals(hotlist.get(position).getName())) {
						DBUtils.saveRecent(ListCities.get(i));
						initRecent();
					}
				}
				Intent data = new Intent();
				data.putExtra("hotcityname", hotlist.get(position).getName());
				setResult(111, data );
				finish();
			}
		});
	}

	// 热门城市适配器
	class myGridAdapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return hotlist.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.hotcity_item, null);
			TextView text = (TextView) convertView.findViewById(R.id.tv_hotcity);
			text.setText(hotlist.get(position).getName());
			return convertView;
		}

		@Override
		public void setData(List list) {
			hotlist.addAll(list);
			notifyDataSetChanged();
		}

	}

	// 设置最近浏览的城市
	private void initRecent() {
		List<Allcity> list = DBUtils.queryRecent();
		Log.e("list", list.size() + "");
		if (list.size() > 0 && list.size() <= 1) {
			recentA.setText(list.get(0).getName());
		} else if (list.size() > 0 && list.size() <= 2) {
			recentA.setText(list.get(0).getName());
			recentB.setText(list.get(1).getName());
		} else if (list.size() > 0 && list.size() <= 3) {
			recentA.setText(list.get(0).getName());
			recentB.setText(list.get(1).getName());
			recentC.setText(list.get(2).getName());
		}
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onTouchCroll(String letter) {
		mLetterText.setVisibility(View.VISIBLE);
		mLetterText.setText(letter);
		int position = getScrollIndex(letter);
		mListView.setSelection(position);// 滑动
	}

	// 获得滑动位置
	@SuppressLint("DefaultLocale")
	private int getScrollIndex(String letter) {
		for (int i = 0; i < ListCities.size(); i++) {
			String first = ListCities.get(i).getPinyin().toUpperCase().substring(0, 1);
			if (letter.equals(first)) {
				return i + mListView.getHeaderViewsCount();
			}
		}
		return 0;
	}

	@Override
	public void stopScroll() {
		mLetterText.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rela_recentfirst:
			String tv1 = tvrecent1.getText().toString();
			Intent data1 = new Intent();
			data1.putExtra("tv1", tv1);
			setResult(TV1, data1 );
			finish();
			break;
		case R.id.rela_recentsecond:
			String tv2 = tvrecent2.getText().toString();
			Intent data2 = new Intent();
			data2.putExtra("tv2", tv2);
			setResult(TV2, data2 );
			finish();
			break;
		case R.id.rela_recentthird:
			String tv3 = tvrecent3.getText().toString();
			Intent data3 = new Intent();
			data3.putExtra("tv3", tv3);
			setResult(TV3, data3 );
			finish();
			break;
		default:
			break;
		}
	}

}
