package com.example.adapter;

import java.util.List;

import android.widget.BaseAdapter;

public abstract class MyAbstractAdapter extends BaseAdapter {

	public abstract void setData(List list);

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
