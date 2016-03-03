package com.example.fragment;

import java.util.List;

import com.example.adapter.MyAbstractAdapter;
import com.example.model.Type;
import com.example.sd01_day.R;
import com.example.sd01_day.R.id;
import com.example.sd01_day.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeBannerFragment extends Fragment {

	private LayoutInflater inflater;
	private int index;
	
	private int[] imgType1 = {R.drawable.img_meishi,R.drawable.img_dujia,R.drawable.img_hotel,R.drawable.img_jipiao,R.drawable.img_juhui,R.drawable.img_ktv,R.drawable.img_movie,R.drawable.img_qinzi};
	private int[] imgType2 = {R.drawable.img_sheying,R.drawable.img_travel,R.drawable.img_dujia,R.drawable.img_xiuxian,R.drawable.img_yuehui,R.drawable.img_yundong,R.drawable.img_zizhucan,R.drawable.img_quanbu};
	
	private String[] typeStr1 = {"美食","度假","旅馆","机票","聚会","KTV","电影","亲子"};
	private String[] typeStr2 = {"摄影","旅行","度假","休闲","约会","运动","自助餐","全部"};
	
	public HomeBannerFragment() {
		
	}

	public HomeBannerFragment(int index) {
		this.index = index;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		View layout = inflater.inflate(R.layout.fragment_home_banner, null);
		initUI(layout);
		return layout ;
	}

	private void initUI(View layout) {
		GridView gridView = (GridView) layout.findViewById(R.id.gridview_homepager);
		gridView.setAdapter(new myGridAdapter());
	}

	class myGridAdapter extends MyAbstractAdapter{

		@Override
		public int getCount() {
			return 8;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.grid_homebanner_item, null);
			ImageView imgBanner = (ImageView) convertView.findViewById(R.id.img_banner);
			TextView textBanner = (TextView) convertView.findViewById(R.id.tv_banner);
			if(index == 0){
				imgBanner.setImageResource(imgType1[position]);
				textBanner.setText(typeStr1[position]);
			}else{
				imgBanner.setImageResource(imgType2[position]);
				textBanner.setText(typeStr2[position]);
			}
			
			return convertView;
		}

		@Override
		public void setData(List list) {
			
		}
		
	}
}
