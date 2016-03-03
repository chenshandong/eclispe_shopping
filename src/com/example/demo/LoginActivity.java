package com.example.demo;

import java.text.SimpleDateFormat;

import com.android.volley.VolleyError;
import com.example.constants.Constants;
import com.example.sd01_day.R;
import com.example.sinaweibo.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.RegexValidateUtil;
import com.xinbo.utils.VolleyListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText mEditName;
	private EditText mEditPsw;
	private String username;
	private String password;

	// bomb的登录id
	public static String APPID = "185dd34f1336764dd8a4bf33a4028bfb";
	private ImageView mImageWeibo;

	//增加微博一键登录
	private AuthInfo mAuthInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_user_password);
		// 初始化bomb
		Bmob.initialize(getApplicationContext(), APPID);
		initUI();
		initWeiBoSDK();
		initData();
	}

	private void initData() {
		mAccessToken = AccessTokenKeeper.readAccessToken(this);
		if (mAccessToken.isSessionValid()) {
			updateTokenView(true);
		}
	}

	private void initWeiBoSDK() {
		mAuthInfo = new AuthInfo(this, com.example.sinaweibo.Constants.APP_KEY, com.example.sinaweibo.Constants.REDIRECT_URL, com.example.sinaweibo.Constants.SCOPE);
		mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
	}

	private void initUI() {
		mEditName = (EditText) findViewById(R.id.et_username);
		mEditPsw = (EditText) findViewById(R.id.et_password);
		mImageWeibo = (ImageView) findViewById(R.id.img_login_weibo);
		mImageWeibo.setOnClickListener(this);
	}

	// 登录Bomb
	public void btnLogin(View v) {
		login();
	}

	private void login() {
		if (!valid()) {
			return;
		}
		final BmobUser bu2 = new BmobUser();
		bu2.setUsername(username);
		bu2.setPassword(password);
		bu2.login(this, new SaveListener() {

			@Override
			public void onSuccess() {
				toast(bu2.getUsername() + "登陆成功");
				Intent intent = new Intent();
				intent.putExtra("username", username);
				setResult(Constants.code.LOGINBOMB_RESUST, intent);
				finish();
			}

			@Override
			public void onFailure(int code, String msg) {
				toast("登陆失败:" + "账号或者密码输入错误！");
			}
		});

	}

	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	//验证格式
	private boolean valid() {
		username = mEditName.getText().toString().trim();
		password = mEditPsw.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			mEditName.setError("请输入手机号");
			return false;
		}
		if (!RegexValidateUtil.checkMobileNumber(username)) {
			mEditName.setError("请输入正确的手机号！");
			return false;
		}
		if (TextUtils.isEmpty(password)) {
			mEditPsw.setError("密码不能为空！");
			return false;
		}
		if (!RegexValidateUtil.checkCharacter(password)) {
			mEditPsw.setError("请输入6~14位的密码！");
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// 一键登录
		switch (v.getId()) {
		case R.id.img_login_weibo:
			quickLoginWeibo();
			break;

		default:
			break;
		}
	}

	private SsoHandler mSsoHandler;
	private Oauth2AccessToken mAccessToken;

	private void quickLoginWeibo() {
		
		Toast.makeText(this, "开始授权登录", Toast.LENGTH_LONG).show();
		mSsoHandler.authorize(new AuthListener());
		
//		String token = "";
//		Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
//		if (accessToken != null) {
//			token = accessToken.getToken();
//		} else {
//			Toast.makeText(this, "开始授权登录", Toast.LENGTH_LONG).show();
//			mSsoHandler.authorize(new AuthListener());
//			return;
//		}
//		String token = mAccessToken.getToken();
//		String uid = mAccessToken.getUid();
//		String url = "https://api.weibo.com/2/users/show.json?access_token="+token+"&uid="+uid;
//		HTTPUtils.get(this, url, new VolleyListener() {
//			
//			@Override
//			public void onResponse(String json) {
//				Intent intent = new Intent();
//				intent.putExtra("json", json);
//				setResult(Constants.code.LOGINWEIBO_RESUST, intent);
//				finish();
//			}
//			
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				
//			}
//		});
		
	}

	// 授权登录需要用到的类
	class AuthListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {
			// 从 Bundle 中解析 Token
			mAccessToken = Oauth2AccessToken.parseAccessToken(values);
			// 从这里获取用户输入的 电话号码信息
			String phoneNum = mAccessToken.getPhoneNum();
			//
			backtoMine();
			if (mAccessToken.isSessionValid()) {
				// 显示 Token
				updateTokenView(false);

				// 保存 Token 到 SharedPreferences
				AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
				Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT)
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
				Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onCancel() {
			Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(LoginActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
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

	public void backtoMine() {
		String token = mAccessToken.getToken();
		String uid = mAccessToken.getUid();
		String url = "https://api.weibo.com/2/users/show.json?access_token="+token+"&uid="+uid;
		HTTPUtils.get(this, url, new VolleyListener() {
			
			@Override
			public void onResponse(String json) {
				Intent intent = new Intent();
				intent.putExtra("json", json);
				setResult(Constants.code.LOGINWEIBO_RESUST, intent);
				finish();
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				
			}
		});
	}
}
