package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.citymodel.Allcity;
import com.example.guessmodel.Goodlist;
import com.example.guessmodel.Image;

import android.util.Log;

public class DBUtils {

	
	/**
	 * 城市数据操作
	 */
	public static void saveRecent(Allcity allcity) {
		if(isSave(allcity)){
			delRencent(allcity);
		}
		allcity = allcity.clone();
		allcity.save();
	}

	public static List<Allcity> queryRecent() {
		List<Allcity> allcitylist = new Select().from(Allcity.class).orderBy("Id DESC").limit(3).execute();
		return allcitylist;
	}

	public static boolean isSave(Allcity allcity) {

		Allcity allcity2 = new Select().from(Allcity.class)
				.where("cityId=?", allcity.getCityId()).executeSingle();
		return allcity2 != null;
	}
	
	public static void delRencent(Allcity allcity){
		new Delete().from(Allcity.class).where("cityId=?", allcity.getCityId()).executeSingle();
	}
	
	/**
	 * 购物车操作
	 */
	public static void saveCart(Goodlist goodlist){
		goodlist.save();
		List<Image> images = goodlist.getImages();
		String product = goodlist.getProduct();
		for (int i = 0; i < images.size(); i++) {
			images.get(i).setProduct(product);
			images.get(i).save();
		}
	}
	
	/**
	 * 查询购物车
	 */
	public static List<Goodlist> queryCart(){
		List<Goodlist> Goodlist = new Select().from(Goodlist.class).execute();
		for (int i = 0; i < Goodlist.size(); i++) {
			 Goodlist goodlist2 = Goodlist.get(i);
			 List<Image> images = new Select().from(Image.class).where("product=?",goodlist2.getProduct()).execute();
			 goodlist2.setImages((ArrayList<Image>) images);
		}
//		List<Image> images = new Select().from(Image.class).orderBy("Id DESC").execute();
//		for (int i = 1; i <= Goodlist.size(); i++) {
//			List<Image> images = Goodlist.get(i-1).getImages();
//			Image img = new Select().from(Image.class).where("RecNo=?",4*i-3).executeSingle();
//			images.add(img);
//			Image img2 = new Select().from(Image.class).where("RecNo=?",4*i-3+1).executeSingle();
//			images.add(img2);
//			Image img3 = new Select().from(Image.class).where("RecNo=?",4*i-3+2).executeSingle();
//			images.add(img3);
//			Image img4 = new Select().from(Image.class).where("RecNo=?",4*i-3+3).executeSingle();
//			images.add(img4);
//		}
		return Goodlist;
	}
	
	/**
	 * 查询是否有值
	 */
	public static boolean isHave(Goodlist goodlist){
		Goodlist guess = new Select().from(Goodlist.class).where("product=?",goodlist.getProduct()).executeSingle();
		return guess!=null;
	}
	/**
	 * 
	 */
	public static void deCart(Goodlist goodlist,int i){
		new Delete().from(Goodlist.class).where("product=?",goodlist.getProduct()).executeSingle();
		new Delete().from(Image.class).where("product=?",goodlist.getProduct()).execute();
//		for (int j = 0; j < 4; j++) {
//			new Delete().from(Image.class).where("RecNo=?",4*i-3+j).executeSingle();
//		}
//		new Delete().from(Image.class).where("Id=?",4*i-3+1).executeSingle();
//		new Delete().from(Image.class).where("Id=?",4*i-3+2).executeSingle();
//		new Delete().from(Image.class).where("Id=?",4*i-3+3).executeSingle();
	}
	
	/**
	 * 删除所有
	 */
	public static void delAllCart(){
		new Delete().from(Goodlist.class).execute();
		new Delete().from(Image.class).execute();
	}
	/**
	 * 
	 */
	public static boolean isCarting(){
		List<Goodlist> goodlist = new Select().from(Goodlist.class).execute();
		return goodlist.size() != 0;
	}
} 
