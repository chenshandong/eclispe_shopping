package com.example.demo;


import com.example.sd01_day.R;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

	private ProgressBar mProgress;
	private WebView mWebview;
	private TextView mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		initActionBar();
		initUI();
		initData();
	}

	private void initActionBar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWebview.destroy();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if(mWebview.canGoBack()){
				mWebview.goBack();
			}else{
				finish();
			}
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initData() {
		Intent intent = getIntent();
		int key = intent.getIntExtra("key", 0);
		if(key == 1){
			mWebview.loadUrl("http://d2.lashouimg.com/wirelessimg/activity/spa_fj_APP160119114659/index.html");
			mTitle.setText("迎新春，送福利！");
		}else if(key == 2){
			mTitle.setText("夺宝进行时！");
			mWebview.loadUrl("http://1.lashou.com/duobao/index.php?fr=15301");
		}else if(key == 3){
			mTitle.setText("厦门随心游！");
			mWebview.loadUrl("http://m.lashou.com/zhuanti/banner_zt.php?kid=151211135901");
		}else{
			mTitle.setText("每日新款");
			mWebview.loadUrl("http://pages.w.meilishuo.com/page/56a8bf6b72079e0a33ea1984?scroll=0&height=6783&remember=1&frame=0&hdtrc=wanwucircle&r=welcome-main%3A_page_code%3Dwelcome-main%3Amodule%3Dnote%3Aaction%3DNote_welcome_debut_banner");
		}
	}

	private WebView initUI() {
		mWebview = (WebView) findViewById(R.id.webView1);
		mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		mTitle = (TextView) findViewById(R.id.tv_title_web);
		mProgress.setVisibility(View.INVISIBLE);
		WebSettings settings = mWebview.getSettings();
		settings.setJavaScriptEnabled(true);
		mWebview.setWebViewClient(new WebViewClient());
		mWebview.setWebChromeClient(new WebChromeClient(){

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if(newProgress<100){
					mProgress.setVisibility(View.VISIBLE);
					mProgress.setProgress(newProgress);
				}else{
					mProgress.setVisibility(View.INVISIBLE);
				}
			}
		});
		return mWebview;
	}

}
