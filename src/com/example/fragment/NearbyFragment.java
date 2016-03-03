package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.example.adapter.MyAbstractAdapter;
import com.example.api.APIClient;
import com.example.demo.DetailsActivity;
import com.example.demo.MainActivity;
import com.example.fragment.HomeFragment.guessAdapter;
import com.example.guessmodel.Cainixihuan;
import com.example.guessmodel.Goodlist;
import com.example.sd01_day.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NearbyFragment extends Fragment implements OnClickListener {

	private View layout;
	private LayoutInflater inflater;
	private MainActivity mActivity;
	private CheckBox mCheckCategory;
	private CheckBox mCheckArea;
	private CheckBox mCheckSort;
	private CheckBox mCheckAdvance;
	private PopupWindow mPopup;
	private ArrayList<Goodlist> nearList = new ArrayList<>();
	private guessAdapter guessAdapter;
	private View contentView;
	private ListView mDrawlist;
	
	public NearbyFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if (layout == null) {
			layout = inflater.inflate(R.layout.fragment_nearby, container, false);
			mActivity = (MainActivity) getActivity();
			initUI();
			initData();
		}

		return layout;
	}

	private void initData() {
		APIClient.getGuess(getContext(), new VolleyListener() {
			

			@Override
			public void onResponse(String json) {
				Cainixihuan cainixihuan = GsonUtils.parseJSON(json, Cainixihuan.class);
				List<Goodlist> goodlist = cainixihuan.getResult().getGoodlist();
				guessAdapter.setData(goodlist);
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	class guessAdapter extends MyAbstractAdapter{

		@Override
		public int getCount() {
			return nearList.size();
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
			Goodlist goodlist = nearList.get(position);
			title_big.setText(goodlist.getProduct());
			title_small.setText(goodlist.getShortTitle());
			price.setText("¥"+goodlist.getPrice());
			value.setText("¥"+goodlist.getValue());
			value.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			salecount.setText("已售"+goodlist.getBought());
			UILUtils.displayImage(goodlist.getImages().get(3).getImage(), icon);
			return layout;
		}

		@Override
		public void setData(List list) {
			nearList.addAll(list);
			this.notifyDataSetChanged();
		}
		
		
	}
	
	private void initUI() {
		layout.findViewById(R.id.nearby_titlebar_home).setOnClickListener(this);
		mCheckCategory = (CheckBox) layout.findViewById(R.id.tuanlist_filter_category);
		mCheckArea = (CheckBox) layout.findViewById(R.id.tuanlist_filter_area);
		mCheckSort = (CheckBox) layout.findViewById(R.id.tuanlist_filter_sort);
		mCheckAdvance = (CheckBox) layout.findViewById(R.id.tuanlist_filter_advance);
		mCheckCategory.setOnClickListener(this);
		mCheckArea.setOnClickListener(this);
		mCheckSort.setOnClickListener(this);
		mCheckAdvance.setOnClickListener(this);
		//添加listview数据
		PullToRefreshListView pullListview = (PullToRefreshListView) layout.findViewById(R.id.listview_nearby);
		mListview = pullListview.getRefreshableView();
		guessAdapter = new guessAdapter();
		mListview.setAdapter(guessAdapter);
		//添加listview行点击事件
		mListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getContext(), DetailsActivity.class);
				Goodlist value = nearList.get(position-mListview.getHeaderViewsCount());
				intent.putExtra("guess", value);
				startActivity(intent );
			}
		});
		initPopu();
	}

	@SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	private void initPopu() {
		contentView = inflater.inflate(R.layout.tuanlist_filter_popup_list_two_level, null);
		mPopup = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		BitmapDrawable bg = new BitmapDrawable();
		mPopup.setBackgroundDrawable(bg);
		mPopup.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				mCheckCategory.setChecked(false);
				mCheckArea.setChecked(false);
				mCheckSort.setChecked(false);
				mCheckAdvance.setChecked(false);
			}
		});

		contentView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mPopup.dismiss();
			}
		});
		//在弹出的popuwindow中添加数据
		initDraw();
	}
	
	

	private void initDraw() {
		SimpleAdapter mDrawerAapter = new SimpleAdapter(getContext(), getDrawerItems(), R.layout.simplelist_item_nearby,
				new String[] { "img", "title" }, new int[] { R.id.img_simplelist, R.id.tv_simplelist });
		mDrawlist = (ListView) contentView.findViewById(R.id.tuanlist_filter_popup_list_1);
		mDrawlist.setAdapter(mDrawerAapter);
		mDrawlist2 = (ListView) contentView.findViewById(R.id.tuanlist_filter_popup_list_2);
		mDrawlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//点击之后再设置适配器
				mDrawlist2.setAdapter(new MysimpleAdapter());
			}
		});
		mDrawlist2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//点击右边的listview后 popuwindow消失
				mPopup.dismiss();
				mDrawlist2.setAdapter(null);
			}
		});
	}

	class MysimpleAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return str.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView text = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, null);
			text.setTextSize(14);
			text.setText(str[position]);
			return text;
		}
		
	}
	
	private String[] str = {"全部","自助餐","火锅","烧烤","烤鱼","干锅","西餐","海鲜","北京菜","川菜","粤菜","台湾菜","福建菜","湘菜","江浙菜","东北菜","内蒙菜","湘北菜","鲁菜"};
	private ListView mDrawlist2;
	private ListView mListview;
	
	
	//TODO 简单实现listview
	private List<Map<String, Object>> getDrawerItems() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_quanbu);
		map.put("title", "全部分类");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_hotel);
		map.put("title", "旅馆");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_meishi);
		map.put("title", "美食");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_xiuxian);
		map.put("title", "休闲");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_movie);
		map.put("title", "电影");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_travel);
		map.put("title", "旅行");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_hotel);
		map.put("title", "旅馆");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_ktv);
		map.put("title", "KTV");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_qinzi);
		map.put("title", "亲子");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_dujia);
		map.put("title", "度假");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_sheying);
		map.put("title", "摄影");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.img_yuehui);
		map.put("title", "约会");
		list.add(map);
		return list;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nearby_titlebar_home:
			// 激活首页
			mActivity.activeHome();
			break;
		case R.id.tuanlist_filter_category:
			mPopup.showAsDropDown(v);
			break;
		case R.id.tuanlist_filter_area:
			mPopup.showAsDropDown(v);
			break;
		case R.id.tuanlist_filter_sort:
			mPopup.showAsDropDown(v);
			break;
		case R.id.tuanlist_filter_advance:
			mPopup.showAsDropDown(v);
			break;
		default:
			break;
		}
	}

}
