package com.example.guessmodel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

@Table(name = "cart")
public class Goodlist extends Model implements Parcelable {

    /**
	 * 
	 */
	@Column
	@SerializedName("goods_id")
    @Expose
    private String goodsId;
	@Column
    @Expose
    private String product;
	@Column
    @Expose
    private String title;
	@Column
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
	@Column
    @Expose
    private String value;
	@Column
    @Expose
    private String price;
	@Column
    @Expose
    private Integer bought;
	@Column
    @SerializedName("is_new")
    @Expose
    private String isNew;
	@Column
    @SerializedName("is_appointment")
    @Expose
    private Integer isAppointment;
	@Column
    @SerializedName("seven_refund")
    @Expose
    private String sevenRefund;
	@Column
    @SerializedName("time_refund")
    @Expose
    private Integer timeRefund;
	@Column
    @SerializedName("goods_type")
    @Expose
    private String goodsType;
	@Column
    @SerializedName("is_sell_up")
    @Expose
    private String isSellUp;
	@Column
    @SerializedName("new_cat")
    @Expose
    private String newCat;
	@Column
    @SerializedName("is_voucher")
    @Expose
    private String isVoucher;
	@Column
    @SerializedName("left_time")
    @Expose
    private Integer leftTime;
	@Column
    @Expose
    private String distance;
	@Column
    @Expose
    private List<Image> images = new ArrayList<Image>();
	@Column
    @SerializedName("l_display")
    @Expose
    private Integer lDisplay;
	@Column
    @SerializedName("l_text")
    @Expose
    private String lText;
	@Column
    @SerializedName("l_price")
    @Expose
    private String lPrice;
	@Column
    @SerializedName("l_content")
    @Expose
    private String lContent;
	@Column
    @Expose
    private String lat;
	@Column
    @Expose
    private String lng;
	@Column
    @Expose
	private int number;

	/**
	 *ORM需要默认构造方法 
	 */
	
	public Goodlist() {
	}
	
    public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	/**------------------------------pacelable----------------------------*/

    
    
    public int describeContents() {
        return 0;
    }

    
    @Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(goodsId);
		out.writeString(product);
		out.writeString(title);
		out.writeString(shortTitle);
		out.writeString(value);
		out.writeString(price);
		out.writeInt(bought);
		out.writeString(isNew);
		if(isAppointment == null){
			out.writeInt(0);
		}else{
			out.writeInt(isAppointment);
		}
		out.writeString(sevenRefund);
		if(timeRefund == null){
			out.writeInt(0);
		}else{
			out.writeInt(timeRefund);
		}
		out.writeString(goodsType);
		out.writeString(isSellUp);
		out.writeString(newCat);
		out.writeString(isVoucher);
		if(leftTime == null){
			out.writeInt(0);
		}else{
			out.writeInt(leftTime);
		}
		out.writeString(distance);
		out.writeList(images);
		if(lDisplay == null){
			out.writeInt(0);
		}else{
			out.writeInt(lDisplay);
		}
		out.writeString(lText);
		out.writeString(lPrice);
		out.writeString(lContent);
		out.writeString(lat);
		out.writeString(lng);
		
	}

    public Goodlist(String goodsId, String product, String title, String shortTitle, String value, String price,
			Integer bought, String isNew, Integer isAppointment, String sevenRefund, Integer timeRefund,
			String goodsType, String isSellUp, String newCat, String isVoucher, Integer leftTime, String distance,
			ArrayList<Image> images, Integer lDisplay, String lText, String lPrice, String lContent, String lat,
			String lng) {
		super();
		this.goodsId = goodsId;
		this.product = product;
		this.title = title;
		this.shortTitle = shortTitle;
		this.value = value;
		this.price = price;
		this.bought = bought;
		this.isNew = isNew;
		this.isAppointment = isAppointment;
		this.sevenRefund = sevenRefund;
		this.timeRefund = timeRefund;
		this.goodsType = goodsType;
		this.isSellUp = isSellUp;
		this.newCat = newCat;
		this.isVoucher = isVoucher;
		this.leftTime = leftTime;
		this.distance = distance;
		this.images = images;
		this.lDisplay = lDisplay;
		this.lText = lText;
		this.lPrice = lPrice;
		this.lContent = lContent;
		this.lat = lat;
		this.lng = lng;
	}


	public static final Goodlist.Creator<Goodlist> CREATOR
            = new Goodlist.Creator<Goodlist>() {
        public Goodlist createFromParcel(Parcel in) {
            return new Goodlist(in);
        }

        public Goodlist[] newArray(int size) {
            return new Goodlist[size];
        }
    };
    
    //TODO
    @SuppressWarnings("unchecked")
	private Goodlist(Parcel in) {
    	goodsId = in.readString();
    	product = in.readString();
    	title = in.readString();
    	shortTitle = in.readString();
    	value = in.readString();
    	price = in.readString();
    	bought=in.readInt();
    	isNew = in.readString();
    	isAppointment=in.readInt();
    	sevenRefund = in.readString();
    	timeRefund=in.readInt();
    	goodsType = in.readString();
    	isSellUp = in.readString();
    	newCat = in.readString();
    	isVoucher = in.readString();
    	leftTime=in.readInt();
    	distance = in.readString();
    	images = in.readArrayList(Image.class.getClassLoader());
    	lDisplay=in.readInt();
    	lText = in.readString();
    	lPrice = in.readString();
    	lContent = in.readString();
    	lat = in.readString();
    	lng = in.readString();
    	
    }

	 /**------------------------------pacelable----------------------------*/
    
    /**
     * 
     * @return
     *     The goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 
     * @param goodsId
     *     The goods_id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 
     * @return
     *     The product
     */
    public String getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *     The product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * 
     * @param shortTitle
     *     The short_title
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The bought
     */
    public Integer getBought() {
        return bought;
    }

    /**
     * 
     * @param bought
     *     The bought
     */
    public void setBought(Integer bought) {
        this.bought = bought;
    }

    /**
     * 
     * @return
     *     The isNew
     */
    public String getIsNew() {
        return isNew;
    }

    /**
     * 
     * @param isNew
     *     The is_new
     */
    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    /**
     * 
     * @return
     *     The isAppointment
     */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /**
     * 
     * @param isAppointment
     *     The is_appointment
     */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    /**
     * 
     * @return
     *     The sevenRefund
     */
    public String getSevenRefund() {
        return sevenRefund;
    }

    /**
     * 
     * @param sevenRefund
     *     The seven_refund
     */
    public void setSevenRefund(String sevenRefund) {
        this.sevenRefund = sevenRefund;
    }

    /**
     * 
     * @return
     *     The timeRefund
     */
    public Integer getTimeRefund() {
        return timeRefund;
    }

    /**
     * 
     * @param timeRefund
     *     The time_refund
     */
    public void setTimeRefund(Integer timeRefund) {
        this.timeRefund = timeRefund;
    }

    /**
     * 
     * @return
     *     The goodsType
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 
     * @param goodsType
     *     The goods_type
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 
     * @return
     *     The isSellUp
     */
    public String getIsSellUp() {
        return isSellUp;
    }

    /**
     * 
     * @param isSellUp
     *     The is_sell_up
     */
    public void setIsSellUp(String isSellUp) {
        this.isSellUp = isSellUp;
    }

    /**
     * 
     * @return
     *     The newCat
     */
    public String getNewCat() {
        return newCat;
    }

    /**
     * 
     * @param newCat
     *     The new_cat
     */
    public void setNewCat(String newCat) {
        this.newCat = newCat;
    }

    /**
     * 
     * @return
     *     The isVoucher
     */
    public String getIsVoucher() {
        return isVoucher;
    }

    /**
     * 
     * @param isVoucher
     *     The is_voucher
     */
    public void setIsVoucher(String isVoucher) {
        this.isVoucher = isVoucher;
    }

    /**
     * 
     * @return
     *     The leftTime
     */
    public Integer getLeftTime() {
        return leftTime;
    }

    /**
     * 
     * @param leftTime
     *     The left_time
     */
    public void setLeftTime(Integer leftTime) {
        this.leftTime = leftTime;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The lDisplay
     */
    public Integer getLDisplay() {
        return lDisplay;
    }

    /**
     * 
     * @param lDisplay
     *     The l_display
     */
    public void setLDisplay(Integer lDisplay) {
        this.lDisplay = lDisplay;
    }

    /**
     * 
     * @return
     *     The lText
     */
    public String getLText() {
        return lText;
    }

    /**
     * 
     * @param lText
     *     The l_text
     */
    public void setLText(String lText) {
        this.lText = lText;
    }

    /**
     * 
     * @return
     *     The lPrice
     */
    public String getLPrice() {
        return lPrice;
    }

    /**
     * 
     * @param lPrice
     *     The l_price
     */
    public void setLPrice(String lPrice) {
        this.lPrice = lPrice;
    }

    /**
     * 
     * @return
     *     The lContent
     */
    public String getLContent() {
        return lContent;
    }

    /**
     * 
     * @param lContent
     *     The l_content
     */
    public void setLContent(String lContent) {
        this.lContent = lContent;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

}
