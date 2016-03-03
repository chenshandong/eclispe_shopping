package com.example.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.constants.Constants;
import com.example.demo.CartActivity;
import com.example.demo.LoginActivity;
import com.example.demo.RegisterActivity;
import com.example.sd01_day.R;
import com.xinbo.utils.UILUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

public class MineFragment extends Fragment implements OnClickListener {

	private View layout;
	private LayoutInflater inflater;
	private LinearLayout mLoginLayout;
	private TextView user_name;
	private Button btn_quit;
	private ImageView img_avatar;

	public MineFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			this.inflater = inflater;
			layout = inflater.inflate(R.layout.fragment_mine, null);
			initUI();
		}
		return layout;
	}

	private void initUI() {
		
		layout.findViewById(R.id.icon_shoppingcart).setOnClickListener(this);
		Button btn_login = (Button) layout.findViewById(R.id.login_button);
		TextView register_button = (TextView) layout.findViewById(R.id.register_button);
		btn_login.setOnClickListener(this);
		register_button.setOnClickListener(this);
		// 稍后登录之后隐藏这个界面
		mLoginLayout = (LinearLayout) layout.findViewById(R.id.not_logged_in_container);
		// 登录之后显示这些控件
		user_name = (TextView) layout.findViewById(R.id.user_name);
		btn_quit = (Button) layout.findViewById(R.id.quitLogin_button);
		btn_quit.setOnClickListener(this);
		// 找到头像
		img_avatar = (ImageView) layout.findViewById(R.id.avatar);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			Intent loginIntent = new Intent(getContext(), LoginActivity.class);
			startActivityForResult(loginIntent, Constants.code.LOGIN_REQUEST);
			break;
		case R.id.register_button:
			Intent registerIntent = new Intent(getContext(), RegisterActivity.class);
			startActivity(registerIntent);
			break;
		case R.id.quitLogin_button:
			// TODO quit
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setTitle("是否注销？").setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					BmobUser.logOut(getContext());
					mLoginLayout.setVisibility(View.VISIBLE);
					user_name.setVisibility(View.GONE);
					btn_quit.setVisibility(View.GONE);
					img_avatar.setImageResource(R.drawable.mine_avatar);
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();
			break;
		case R.id.icon_shoppingcart:
			Intent intentCart = new Intent(getContext(), CartActivity.class);
			startActivity(intentCart );
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {

			if (requestCode == Constants.code.LOGIN_REQUEST) {
				if (resultCode == Constants.code.LOGINBOMB_RESUST) {
					String username = data.getStringExtra("username");
					mLoginLayout.setVisibility(View.GONE);
					user_name.setVisibility(View.VISIBLE);
					btn_quit.setVisibility(View.VISIBLE);
					user_name.setText(username);
					img_avatar.setImageResource(R.drawable.img_avatar_login);
				} else if (resultCode == Constants.code.LOGINWEIBO_RESUST) {
					String json = data.getStringExtra("json");
					parseJson(json);
				}
			}
		}

	}

	protected void parseJson(String json) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			String name = (String) jsonObject.get("screen_name");
			String imgUrl = (String) jsonObject.get("profile_image_url");
			mLoginLayout.setVisibility(View.GONE);
			user_name.setVisibility(View.VISIBLE);
			btn_quit.setVisibility(View.VISIBLE);
			user_name.setText(name);
			UILUtils.displayImage(imgUrl, img_avatar);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
