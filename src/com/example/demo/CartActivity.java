package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.alipay.sdk.app.PayTask;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.example.adapter.MyAbstractAdapter;
import com.example.guessmodel.Goodlist;
import com.example.sd01_day.R;
import com.example.utils.DBUtils;
import com.xinbo.utils.UILUtils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends Activity implements OnClickListener {

	private SwipeMenuListView mCartlist;
	private ArrayList<Goodlist> guesslist = new ArrayList<>();
	private CartListadapter mCartAdapter;
	private TextView title;
	private Button mBtndel;
	private Button mBtnpurchase;
	private TextView mTvmax;
	private float max;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		initUI();
		initData();
	}

	private void initData() {
		List<Goodlist> queryCart = DBUtils.queryCart();
		mCartAdapter.setData(queryCart);
	}

	@SuppressLint("NewApi")
	private void initUI() {
		// 设置合计、清空、结算
		mTvmax = (TextView) findViewById(R.id.tv_max);
		mBtnpurchase = (Button) findViewById(R.id.btn_purchase);
		rela_bg = (RelativeLayout) findViewById(R.id.cart_bg);
		mCartlist = (SwipeMenuListView) findViewById(R.id.cartlist);
		findViewById(R.id.cart_back).setOnClickListener(this);
		mBtndel = (Button) findViewById(R.id.btn_delAll);
		mBtnpurchase.setOnClickListener(this);
		// 设置标题
		title = (TextView) findViewById(R.id.tv_carttitle);
		boolean carting = DBUtils.isCarting();
		if(!carting){
			mBtnpurchase.setClickable(false);
			mBtnpurchase.setBackground(getResources().getDrawable(R.drawable.shape_cart_purchase_pressed));
			rela_bg.setVisibility(View.VISIBLE);
			mCartlist.setVisibility(View.GONE);
		}else {
			rela_bg.setVisibility(View.GONE);
			mCartlist.setVisibility(View.VISIBLE);
		}
		mBtndel.setOnClickListener(this);
		mCartAdapter = new CartListadapter();
		mCartlist.setAdapter(mCartAdapter);
		mCartlist.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
		SwipeMenuCreator menuCreator = new MyListMenuCreator();
		mCartlist.setMenuCreator(menuCreator);
		mCartlist.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu arg1, int index) {
				switch (index) {
				case 0:
					DBUtils.deCart(guesslist.get(position), position + 1);
					max = 0;
					guesslist.clear();
					List<Goodlist> queryCart = DBUtils.queryCart();
					mCartAdapter.setData(queryCart);
					break;

				default:
					break;
				}
				return false;
			}
		});
		mCartlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Goodlist goodlist = guesslist.get(position);
				Intent intent = new Intent(CartActivity.this, DetailsActivity.class);
				intent.putExtra("guess", goodlist);
				startActivity(intent);
			}
		});
		fresh();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息

				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(CartActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(CartActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(CartActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(CartActivity.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};
	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;
	private RelativeLayout rela_bg;
	
	//跳转支付宝付款
	private void pay() {
		// 测试用数据
		final String payInfo = "partner=\"2088101568358171\"&seller_id=\"xxx@alipay.com\"&out_trade_no=\"0819145412-6177\"&subject=\"测试\"&body=\"测试测试\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&sign=\"lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJIe2pbjm%2F3kb%2FuGkpG%2BwYQYI51%2BhA3YBbvZHVQBYveBqK%2Bh8mUyb7GM1HxWs9k4%3D\"&sign_type=\"RSA\"";

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(CartActivity.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
	
	@SuppressLint("NewApi")
	private void fresh() {
		for (int i = 0; i < guesslist.size(); i++) {
			float price = Float.parseFloat(guesslist.get(i).getPrice())*guesslist.get(i).getNumber();
			max += price;
		}
		mTvmax.setText("合计：¥ "+String.valueOf(max));
		boolean carting = DBUtils.isCarting();
		if(!carting){
			mBtnpurchase.setClickable(false);
			mBtnpurchase.setBackground(getResources().getDrawable(R.drawable.shape_cart_purchase_pressed));
			rela_bg.setVisibility(View.VISIBLE);
			mCartlist.setVisibility(View.GONE);
		}else {
			rela_bg.setVisibility(View.GONE);
			mCartlist.setVisibility(View.VISIBLE);
		}
	}

	class CartListadapter extends MyAbstractAdapter {

		@Override
		public int getCount() {
			return guesslist.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_item_cart, null);
			}
			TextView shopname = (TextView) convertView.findViewById(R.id.tv_cartshopname);
			TextView subtitle = (TextView) convertView.findViewById(R.id.tv_cartsubtitle);
			TextView cartpice = (TextView) convertView.findViewById(R.id.tv_cartprice);
			TextView cartcount = (TextView) convertView.findViewById(R.id.tv_cartcount);
			ImageView imgCart = (ImageView) convertView.findViewById(R.id.img_cartshop);

			Goodlist guess = guesslist.get(position);
			shopname.setText(guess.getProduct());
			subtitle.setText(guess.getShortTitle());
			cartpice.setText("¥" + Float.parseFloat(guess.getPrice()) * guess.getNumber());
			cartcount.setText("x" + guess.getNumber());
			// Log.e("getImages", guess.getImages().size()+"");
			UILUtils.displayImage(guess.getImages().get(3).getImage(), imgCart);
			return convertView;
		}

		@Override
		public void setData(List list) {
			guesslist.addAll(list);
			mCartAdapter.notifyDataSetChanged();
			title.setText("购物车(" + list.size() + ")");
			mBtnpurchase.setText("结算(" + list.size() + ")");
			fresh();
		}

	}

	/**
	 * ----------------------------增加listview侧滑菜单start--------------------------
	 * -----------
	 */
	// 创建
	private final class MyListMenuCreator implements SwipeMenuCreator {
		@Override
		public void create(SwipeMenu menu) {
			// // 创建open选项
			//// SwipeMenuItem openItem = new
			// SwipeMenuItem(getApplicationContext());
			// // 设置item背景颜色
			// openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
			// 0xCE)));
			// // 设置item的宽度
			// openItem.setWidth(dp2px(90));
			// // 设置item的标题
			// openItem.setTitle("打开");
			// // 设置标题的大小
			// openItem.setTitleSize(18);
			// // 设置标题字体颜色
			// openItem.setTitleColor(Color.WHITE);
			// // 增加到菜单中
			// menu.addMenuItem(openItem);

			// 创建删除item
			SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
			// 设置删除的背景
			deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
			// 设置item的宽度
			deleteItem.setWidth(dp2px(90));
			// 设置图标
			deleteItem.setIcon(R.drawable.ic_delete);
			// 增加到菜单中
			menu.addMenuItem(deleteItem);
		}
	}

	// 换算dp转换成px的方法
	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	/**
	 * ----------------------------增加listview侧滑菜单end----------------------------
	 * ---------
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_delAll:
			boolean have = DBUtils.isCarting();
			if (have) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("是否清空？").setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						DBUtils.delAllCart();
						guesslist.clear();
						max = 0;
						mCartAdapter.setData(guesslist);
					}
				}).show();
			} else {
				Toast.makeText(this, "这里空空如也！", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_purchase:
			boolean has = DBUtils.isCarting();
			if(has){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("是否跳转到支付宝付款？").setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						pay();
					}
				}).show();
			}
			break;
		case R.id.cart_back:
			finish();
			break;
		default:
			break;
		}
	}
}
