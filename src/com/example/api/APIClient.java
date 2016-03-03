package com.example.api;

import com.example.constants.Constants;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import android.content.Context;

public class APIClient {
	public static void getDetails(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.DETAIL, listener);
	}
	
	public static void getDetailsEvaluate(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.DETAIL_EVALUATE, listener);
	}
	
	/**--------------------------------首页----------------------------*/
//	public static void getTypes(Context context, VolleyListener listener){
//		HTTPUtils.get(context, Constants.URL.TYPES, listener);
//	}
	public static void getGuess(Context context, VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.GUESS, listener);
	}
	
	/**--------------------------------首页----------------------------*/
	
	
	/**--------------------------------美丽说----------------------------*/
	public static void getHomepage(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.HOMEPAGE, listener);
	}
	
	public static void getPopular(Context context, VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.POPULAR, listener);
	}
	
	
	public static void getBanner(Context context, VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.BANNER, listener);
	}
	/**--------------------------------美丽说----------------------------*/
}
