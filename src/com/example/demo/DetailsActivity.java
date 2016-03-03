package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alipay.sdk.app.PayTask;
import com.android.volley.VolleyError;
import com.example.api.APIClient;
import com.example.evaluamodel.DetailsEvaluate;
import com.example.evaluamodel.Result;
import com.example.evaluamodel.Uid;
import com.example.guessmodel.Goodlist;
import com.example.model.Detail1;
import com.example.sd01_day.R;
import com.example.sinaweibo.AccessTokenKeeper;
import com.example.sinaweibo.Constants;
import com.example.utils.DBUtils;
import com.example.view.MyScrollView;
import com.example.view.MyScrollView.OnMyscrollChangedlistener;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;
import com.xinbo.widget.ListView4ScrollView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class DetailsActivity extends Activity implements OnClickListener {

	private WebView mWebviewDetails;
	private WebView mWebViewNotice;

	private AuthInfo mAuthInfo;
	private SsoHandler mSsoHandler;
	private Oauth2AccessToken mAccessToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		initUI();
		initData();
		initWeiboSDK();
	}

	private void initWeiboSDK() {
		mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mSsoHandler = new SsoHandler(DetailsActivity.this, mAuthInfo);
		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
	}

	private void initData() {
		// 获得webview
		APIClient.getDetails(this, new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				Detail1 detail1 = GsonUtils.parseJSON(arg0, Detail1.class);
				String detailsData = detail1.getDetails();
				String noticeData = detail1.getNotice();
				mWebviewDetails.loadDataWithBaseURL(null, detailsData, "text/html", "utf-8", null);
				mWebViewNotice.loadDataWithBaseURL(null, noticeData, "text/html", "utf-8", null);
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});

		// 获得评价
		APIClient.getDetailsEvaluate(this, new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				DetailsEvaluate detailsEvaluate = GsonUtils.parseJSON(arg0, DetailsEvaluate.class);
				List<Result> resultslist = detailsEvaluate.getResults();
				evaluateList.addAll(resultslist);
				mEvaluateAdapter.notifyDataSetChanged();
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private void initUI() {
		findViewById(R.id.details_dingwei).setOnClickListener(this);
		//添加购物车
		findViewById(R.id.img_details_gouwuche).setOnClickListener(this);
		// 添加分享功能
		mImgShare = (ImageView) findViewById(R.id.groupon_detail_ab_left_icon);
		mImgShare.setOnClickListener(this);
		// 添加评价
		ListView4ScrollView mListViewEvaluate = (ListView4ScrollView) findViewById(R.id.listViewscrollView_details);
		mEvaluateAdapter = new EvaluateAdapter();
		mListViewEvaluate.setAdapter(mEvaluateAdapter);
		// 添加套餐提示信息
		mWebviewDetails = (WebView) findViewById(R.id.webView1);
		mWebViewNotice = (WebView) findViewById(R.id.webView2);
		setWeb();
		final View actionbar = findViewById(R.id.details_actionbar);
		actionbar.getBackground().setAlpha(0);
		final TextView title = (TextView) findViewById(R.id.groupon_detail_ab_title);
		title.setVisibility(View.INVISIBLE);
		final ImageView imgBg = (ImageView) findViewById(R.id.titleBGImageNew);
		MyScrollView myScrollview = (com.example.view.MyScrollView) findViewById(R.id.scrollView_details);
		myScrollview.setOnMyscrollChanged(new OnMyscrollChangedlistener() {

			@Override
			public void OnMyscrollChanged(int y) {
				int imgBgHeight = imgBg.getHeight();
				int actionbarHeight = actionbar.getHeight();
				int perc = y * 100 / (imgBgHeight - actionbarHeight);
				if (perc >= 100) {
					title.setVisibility(View.VISIBLE);
					actionbar.setBackgroundColor(Color.WHITE);
				} else {
					title.setVisibility(View.INVISIBLE);
					if (perc >= 0) {
						actionbar.getBackground().setAlpha(255 * perc / 100);
					} else {
						actionbar.getBackground().setAlpha(0);
					}
				}
			}
		});
		findViewById(R.id.groupon_detail_ab_back).setOnClickListener(this);
		// 支付宝立即购买
		findViewById(R.id.btn_details_purchase).setOnClickListener(this);

		guess = getIntent().getParcelableExtra("guess");
		ImageView ImgBig = (ImageView) findViewById(R.id.titleBGImageNew);
		TextView product = (TextView) findViewById(R.id.groupon_detail_text_view_title);
		TextView shorttitle = (TextView) findViewById(R.id.groupon_detail_text_view_subtitle);
		TextView price = (TextView) findViewById(R.id.txtMoneyCountsNew);
		TextView value = (TextView) findViewById(R.id.originalPriceNew);
		TextView shopname = (TextView) findViewById(R.id.sellerNameNew);
		TextView shortname = (TextView) findViewById(R.id.sellerAddressNew);
		TextView details = (TextView) findViewById(R.id.noticeTextNew);
		TextView salecount = (TextView) findViewById(R.id.tv_details_salecount);
		/**
		 * 设置内容
		 */
		UILUtils.displayImage(guess.getImages().get(0).getImage(), ImgBig);
		product.setText(guess.getProduct());
		shorttitle.setText(guess.getShortTitle());
		price.setText(guess.getPrice());
		value.setText("¥" + guess.getValue());
		value.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		shopname.setText(guess.getProduct());
		shortname.setText(guess.getShortTitle());
		details.setText(guess.getTitle());
		salecount.setText("已售" + guess.getBought());

		initpopup();
	}

	private void initdraw() {
		//添加购物车按钮
		contentView.findViewById(R.id.btn_details_gouwuche).setOnClickListener(this);
		ImageView img_gotopurchase = (ImageView) contentView.findViewById(R.id.img_gotopuchase);
		TextView tv_gotoprice = (TextView) contentView.findViewById(R.id.tv_gotoprice);
		TextView gotosalecount = (TextView) contentView.findViewById(R.id.tv_gotosalecount);
		contentView.findViewById(R.id.img_back).setOnClickListener(this);
		TextView gotodetails = (TextView) contentView.findViewById(R.id.tv_gotodetails);
		img_jian = (ImageView) contentView.findViewById(R.id.img_jian);
		img_jian.setOnClickListener(this);
		contentView.findViewById(R.id.img_jia).setOnClickListener(this);
		contentView.findViewById(R.id.btn_details_comfir).setOnClickListener(this);
		gotobuycount = (TextView) contentView.findViewById(R.id.tv_gotobuycount);
		tvgotototal = (TextView) contentView.findViewById(R.id.tv_gotototal);

		pricenormal = Float.parseFloat(guess.getPrice());
		countnormal = Integer.parseInt(gotobuycount.getText().toString());

		UILUtils.displayImage(guess.getImages().get(0).getImage(), img_gotopurchase);
		tv_gotoprice.setText("¥ " + guess.getPrice());
		gotosalecount.setText("已售：" + guess.getBought());
		gotodetails.setText(guess.getTitle());
		tvgotototal.setText("合计：¥ " + String.valueOf(pricenormal * countnormal));
	}

	private Float getPrice() {
		Float pricenormal = Float.parseFloat(guess.getPrice());
		Float countnormal = Float.parseFloat(gotobuycount.getText().toString());
		return pricenormal * countnormal;
	}

	private void setWeb() {
		WebSettings setWebDetails = mWebviewDetails.getSettings();
		WebSettings setWebNotice = mWebViewNotice.getSettings();
		setWebDetails.setJavaScriptEnabled(true);
		setWebNotice.setJavaScriptEnabled(true);
		setWebDetails.setSupportZoom(true);
		setWebNotice.setSupportZoom(true);
		setWebDetails.setAllowFileAccess(true);
		setWebNotice.setAllowFileAccess(true);
		mWebviewDetails.setWebViewClient(new WebViewClient());
		mWebviewDetails.setWebChromeClient(new WebChromeClient());
		mWebViewNotice.setWebViewClient(new WebViewClient());
		mWebViewNotice.setWebChromeClient(new WebChromeClient());
	}

	private ArrayList<Result> evaluateList = new ArrayList<>();

	// 添加评价适配器
	class EvaluateAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return evaluateList.size();
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
			View layout = getLayoutInflater().inflate(R.layout.evaluate_item, null);
			TextView nickname = (TextView) layout.findViewById(R.id.tv_nickname);
			TextView content = (TextView) layout.findViewById(R.id.tv_eva_content);
			TextView time = (TextView) layout.findViewById(R.id.tv_eva_time);
			ImageView avatar = (ImageView) layout.findViewById(R.id.img_avatar);
			Uid uid = evaluateList.get(position).getUid();
			Result result = evaluateList.get(position);
			nickname.setText(uid.getUsername());
			content.setText(result.getContent());
			String createtime = result.getCreatedAt().substring(0, 10);
			time.setText(createtime);
			UILUtils.displayImage(uid.getAvatar(), avatar);
			return layout;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.groupon_detail_ab_back:
			finish();
			break;
		case R.id.btn_details_purchase:
			// 弹出popupwindow
			mPopup.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
			// pay();
			break;
		case R.id.groupon_detail_ab_left_icon:
			weiboShare();
			break;
		case R.id.img_back:
			mPopup.dismiss();
			break;
		case R.id.img_jia:
			// int countnormal1 =
			// Integer.parseInt(gotobuycount.getText().toString());
			countnormal++;
			gotobuycount.setText(String.valueOf(countnormal));
			tvgotototal.setText("合计：¥ " + String.valueOf(pricenormal * countnormal));
			break;
		case R.id.img_jian:
			// int countnormal2 =
			// Integer.parseInt(gotobuycount.getText().toString());
			if(countnormal>1){
				countnormal--;
				gotobuycount.setText(String.valueOf(countnormal));
				tvgotototal.setText("合计：¥ " + String.valueOf(pricenormal * countnormal));
			}
			break;
		case R.id.btn_details_comfir:
			pay();
			break;
		case R.id.btn_details_gouwuche:
			//TODO 购物车
			boolean have = DBUtils.isHave(guess);
			if(!have){
				guess.setNumber(countnormal);
				DBUtils.saveCart(guess);
				Toast.makeText(this, "添加购物车成功！", Toast.LENGTH_SHORT).show();
				mPopup.dismiss();
			}else{
				Toast.makeText(this, "您已经添加过了！", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.img_details_gouwuche:
			Intent intentCart = new Intent(this, CartActivity.class);
			startActivity(intentCart );
			break;
		case R.id.details_dingwei:
			Intent intentMap = new Intent(this, MapActivity.class);
			startActivity(intentMap );
			break;
		default:
			break;
		}
	}

	private void initpopup() {
		contentView = getLayoutInflater().inflate(R.layout.details_layout_popup_purchase, null);
		mPopup = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		BitmapDrawable bg = new BitmapDrawable();
		mPopup.setBackgroundDrawable(bg);
		mPopup.setAnimationStyle(R.style.popupanime);
		initdraw();
	}

	private IWeiboShareAPI mWeiboShareAPI = null;

	// 微博分享
	private void weiboShare() {
		String token = "";
		Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
		if (accessToken != null) {
			token = accessToken.getToken();
		} else {
			Toast.makeText(this, "开始授权登录", Toast.LENGTH_LONG).show();
			mSsoHandler.authorize(new AuthListener());
			return;
		}
		// String token =mAccessToken.getToken();
		// String status = "Android 团购分享下载地址 小尾巴 2016.01.29 17:43";
		// String url = "https://api.weibo.com/2/statuses/update.json";
		// Map<String, String> params = new HashMap<>();
		// params.put("access_token", token);
		// params.put("status", status);
		// HTTPUtils.post(this, url, params, new VolleyListener() {
		//
		// @Override
		// public void onResponse(String arg0) {
		// Log.e("share", arg0);
		// }
		//
		// @Override
		// public void onErrorResponse(VolleyError arg0) {
		// Log.e("share", arg0.getMessage());
		// }
		// });

		WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		request.transaction = String.valueOf(System.currentTimeMillis());
		TextObject textObject = new TextObject();// 必须要new一个实例
		textObject.text = "我正在使用乐购APP，这里有很多的惊喜等着你，一起来下载吧（www.test.com）";
		weiboMessage.textObject = textObject;
		request.multiMessage = weiboMessage;
		AuthInfo authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mWeiboShareAPI.sendRequest(this, request, authInfo, token, new WeiboAuthListener() {

			@Override
			public void onWeiboException(WeiboException arg0) {
			}

			@Override
			public void onComplete(Bundle bundle) {
				// TODO Auto-generated method stub
				Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
				AccessTokenKeeper.writeAccessToken(getApplicationContext(), newToken);
				Toast.makeText(getApplicationContext(), "分享成功！", 0).show();
			}

			@Override
			public void onCancel() {
			}
		});
	}

	// 授权登录需要用到的类
	class AuthListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {
			// 从 Bundle 中解析 Token
			mAccessToken = Oauth2AccessToken.parseAccessToken(values);
			// 从这里获取用户输入的 电话号码信息
			String phoneNum = mAccessToken.getPhoneNum();
			if (mAccessToken.isSessionValid()) {
				// 显示 Token
				updateTokenView(false);

				// 保存 Token 到 SharedPreferences
				AccessTokenKeeper.writeAccessToken(DetailsActivity.this, mAccessToken);
				Toast.makeText(DetailsActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT)
						.show();
			} else {
				// 以下几种情况，您会收到 Code：
				// 1. 当您未在平台上注册的应用程序的包名与签名时；
				// 2. 当您注册的应用程序包名与签名不正确时；
				// 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
				String code = values.getString("code");
				String message = getString(R.string.weibosdk_demo_toast_auth_failed);
				if (!TextUtils.isEmpty(code)) {
					message = message + "\nObtained the code: " + code;
				}
				Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onCancel() {
			Toast.makeText(DetailsActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(DetailsActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 显示当前 Token 信息。
	 * 
	 * @param hasExisted
	 *            配置文件中是否已存在 token 信息并且合法
	 */
	private void updateTokenView(boolean hasExisted) {
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(new java.util.Date(mAccessToken.getExpiresTime()));
		String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
		// mTvToken.setText(String.format(format, mAccessToken.getToken(),
		// date));
		String message = String.format(format, mAccessToken.getToken(), date);
		if (hasExisted) {
			message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
		}
		// mTvToken.setText(message);
	}

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;

	private void pay() {
		// 测试用数据
		final String payInfo = "partner=\"2088101568358171\"&seller_id=\"xxx@alipay.com\"&out_trade_no=\"0819145412-6177\"&subject=\"测试\"&body=\"测试测试\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&sign=\"lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJIe2pbjm%2F3kb%2FuGkpG%2BwYQYI51%2BhA3YBbvZHVQBYveBqK%2Bh8mUyb7GM1HxWs9k4%3D\"&sign_type=\"RSA\"";

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(DetailsActivity.this);
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
					Toast.makeText(DetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(DetailsActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(DetailsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(DetailsActivity.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};
	private EvaluateAdapter mEvaluateAdapter;
	private ImageView mImgShare;
	private PopupWindow mPopup;
	private View contentView;
	private ImageView img_jian;
	private Goodlist guess;
	private TextView gotobuycount;
	private Float pricenormal;
	private int countnormal;
	private TextView tvgotototal;

}
