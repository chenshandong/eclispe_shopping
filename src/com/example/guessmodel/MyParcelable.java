package com.example.guessmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable {
     private int mData;

     public int describeContents() {
         return 0;
     }

     @Override
     public void writeToParcel(Parcel out, int flags) {
    	 out.writeInt(mData);
     }

     public static final MyParcelable.Creator<MyParcelable> CREATOR
             = new MyParcelable.Creator<MyParcelable>() {
         public MyParcelable createFromParcel(Parcel in) {
             return new MyParcelable(in);
         }

         public MyParcelable[] newArray(int size) {
             return new MyParcelable[size];
         }
     };
     
     private MyParcelable(Parcel in) {
         mData = in.readInt();
     }


 }