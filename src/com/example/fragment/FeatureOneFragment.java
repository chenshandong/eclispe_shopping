package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.example.adapter.MyAbstractAdapter;
import com.example.api.APIClient;
import com.example.demo.DetailsActivity;
import com.example.demo.WebActivity;
import com.example.popularmodel.Popular;
import com.example.sd01_day.R;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import me.relex.seamlessviewpagerheader.delegate.AbsListViewDelegate;
import me.relex.seamlessviewpagerheader.fragment.BaseViewPagerFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FeatureOneFragment extends BaseViewPagerFragment implements AbsListView.OnItemClickListener {

	private GridView mGridview;
	private LayoutInflater inflater;
	private AbsListViewDelegate mAbsListViewDelegate = new AbsListViewDelegate();
	private ArrayList<com.example.popularmodel.List> popularList = new ArrayList<>();
	private MyPopularAdapter myPopularAdapter;
	

	public static FeatureOneFragment newInstance(int index) {
        FeatureOneFragment featureOneFragment = new FeatureOneFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_FRAGMENT_INDEX, index);
        featureOneFragment.setArguments(args);
        return featureOneFragment;
    }
	
	public FeatureOneFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		View layout = inflater.inflate(R.layout.fragment_feature_one, container, false);
		initUI(layout);
		initData();
		return layout;
	}

	private void initData() {
		APIClient.getPopular(getContext(), new VolleyListener() {
			
			@Override
			public void onResponse(String json) {
				Popular popular = GsonUtils.parseJSON(json, Popular.class);
				List<com.example.popularmodel.List> list = popular.getData().getList();
				myPopularAdapter.setData(list);
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				
			}
		});
	}

	private void initUI(View layout) {
		mGridview = (GridView) layout.findViewById(R.id.gridview_popular);
		mGridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getContext(), WebActivity.class);
				intent.putExtra("key", 4);
				startActivity(intent );
			}
		});
		myPopularAdapter = new MyPopularAdapter();
		mGridview.setAdapter(myPopularAdapter);
	}


	class MyPopularAdapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return popularList.size();
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = inflater.inflate(R.layout.grid_item_popular, null);
			ImageView imgtitle = (ImageView) layout.findViewById(R.id.img_popular);
			TextView price = (TextView) layout.findViewById(R.id.tv_price_popular);
			TextView sale = (TextView) layout.findViewById(R.id.tv_sale_popular);
			TextView like = (TextView) layout.findViewById(R.id.tv_like_popular);
			ImageView imgSmall = (ImageView) layout.findViewById(R.id.img_small_popular);
			com.example.popularmodel.List list = popularList.get(position);
			price.setText("¥"+list.getHMark().get(0).getCampaignPrice());
			UILUtils.displayImage(list.getPicUrl(), imgtitle);
			UILUtils.displayImage(list.getHMark().get(0).getImgUrl(), imgSmall);
			sale.setText("已售"+list.getSaleNum()+"件");
			like.setText(list.getCountLike());
			return layout;
		}


		@Override
		public void setData(List list) {
			popularList.clear();
			popularList.addAll(list);
			myPopularAdapter.notifyDataSetChanged();
		}

		
		
	}

	@Override
	public boolean isViewBeingDragged(MotionEvent event) {
		return mAbsListViewDelegate.isViewBeingDragged(event, mGridview);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}
}
