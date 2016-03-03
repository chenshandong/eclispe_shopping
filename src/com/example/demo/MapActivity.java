package com.example.demo;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.sd01_day.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MapActivity extends Activity implements OnClickListener {

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private ImageView mImgback;

	// 定位相关
	public MyLocationListenner myListener = new MyLocationListenner();
	boolean isFirstLoc = true;// 是否首次定位
	LocationClient mLocClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		initUI();
		initOpenSDK();
		initLocation();
	}

	private void initLocation() {
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null) {
				return;
			}
			MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	private void initUI() {
		mImgback = (ImageView) findViewById(R.id.map_back);
		mImgback.setOnClickListener(this);
	}

	private void initOpenSDK() {

		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 设置比例尺
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);
		addOverlay(24.478849, 118.109605, R.drawable.icon_marka);
		addOverlay(24.488287, 118.105437, R.drawable.icon_markb);
		addOverlay(24.473291, 118.115893, R.drawable.icon_markc);
		addOverlay(24.469476, 118.108851, R.drawable.icon_markd);
		addOverlay(24.469344, 118.081937, R.drawable.icon_marke);
		addOverlay(24.49516, 118.049383, R.drawable.icon_markf);
		addOverlay(24.484834, 118.034507, R.drawable.icon_markg);

		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(final Marker marker) {
				Button button = new Button(MapActivity.this);
				button.setText("更改位置");
				button.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// 覆盖物菜单点击事件
						LatLng ll = marker.getPosition();
						LatLng llNew = new LatLng(ll.latitude + 0.005, ll.longitude + 0.005);
						marker.setPosition(llNew);
						mBaiduMap.hideInfoWindow();
					}
				});
				LatLng ll = marker.getPosition();
				InfoWindow mInfoWindow = new InfoWindow(button, ll, -47);
				mBaiduMap.showInfoWindow(mInfoWindow);
				return true;
			}
		});

	}

	private void addOverlay(double lat, double lng, int imgResID) {
		LatLng llA = new LatLng(lat, lng);
		BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(imgResID);
		MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(9).draggable(true);
		mBaiduMap.addOverlay(ooA);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.map_back:
			finish();
			break;

		default:
			break;
		}
	}
}
