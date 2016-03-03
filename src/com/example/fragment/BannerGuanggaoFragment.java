package com.example.fragment;

import com.example.demo.MainActivity;
import com.example.sd01_day.R;
import com.example.sd01_day.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BannerGuanggaoFragment extends Fragment {

	private int index;
	public BannerGuanggaoFragment() {
	}

	public BannerGuanggaoFragment(int index) {
		this.index = index;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_banner_guanggao, container, false);
		ImageView imgHead = (ImageView) layout.findViewById(R.id.img_head_banner);
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				activity.activeFeature();
			}
		});
		
		if(index == 0){
			imgHead.setImageResource(R.drawable.img_head01);
		}else{
			imgHead.setImageResource(R.drawable.img_head02);
		}
		return layout;
	}

}
