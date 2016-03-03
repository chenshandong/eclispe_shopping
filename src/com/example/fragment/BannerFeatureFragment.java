package com.example.fragment;

import com.example.demo.WebActivity;
import com.example.homepagemodel.Datum;
import com.example.sd01_day.R;
import com.xinbo.utils.UILUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BannerFeatureFragment extends Fragment {

	// 默认图片
	private int[] mBannerImgRes = { R.drawable.img_head01, R.drawable.img_head01, R.drawable.img_head01, R.drawable.img_head01,
			R.drawable.img_head01,R.drawable.img_head01, R.drawable.img_head01, R.drawable.img_head01, R.drawable.img_head01,
			R.drawable.img_head01 };
	private int index;
	private Datum datum;

	public BannerFeatureFragment() {
	}

	public BannerFeatureFragment(int index, Datum datum) {
		this.index = index;
		this.datum = datum;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_banner_feature, container, false);
		ImageView imgBanner = (ImageView) layout.findViewById(R.id.img_bannerfeature);
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), WebActivity.class);
				intent.putExtra("key", 4);
				startActivity(intent );
			}
		});
		
		if(datum == null){
			imgBanner.setImageResource(mBannerImgRes[index%mBannerImgRes.length]);
		}else{
			UILUtils.displayImage(datum.getPicUrl(), imgBanner);
		}
		return layout;
	}

}
