package com.example.demo;

import com.example.sd01_day.R;
import com.xinbo.utils.RegexValidateUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity implements OnClickListener {

	private String username;
	private String password;
	private EditText mEditName;
	private EditText mEditPsw;
	//bomb注册id
	public static String APPID = "185dd34f1336764dd8a4bf33a4028bfb";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Bmob.initialize(getApplicationContext(),APPID);
		initUI();
	}

	private void initUI() {
		mEditName = (EditText) findViewById(R.id.et_username_register);
		mEditPsw = (EditText) findViewById(R.id.et_password_register);
		findViewById(R.id.btn_register_big).setOnClickListener(this);
	}

	private void regist() {
		if (!valid()) {
			return;
		}
		final MyUser myUser = new MyUser();
		myUser.setUsername(username);
		myUser.setPassword(password);
		myUser.setMobilePhoneNumber(username);
//		myUser.setEmail("253757782@qq.com");
		myUser.signUp(this, new SaveListener() {

			@Override
			public void onSuccess() {
//				toast("注册成功:" + myUser.getUsername() + "-" + myUser.getObjectId() + "-" + myUser.getCreatedAt() + "-"
//						+ myUser.getSessionToken() + ",是否验证：" + myUser.getEmailVerified());
				toast("注册成功,请登录！");
				finish();
			}

			@Override
			public void onFailure(int code, String msg) {
				toast("注册失败:" + msg);
			}
		});

	}
	
	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
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
		switch (v.getId()) {
		case R.id.btn_register_big:
			regist();
			break;

		default:
			break;
		}
	}
}
