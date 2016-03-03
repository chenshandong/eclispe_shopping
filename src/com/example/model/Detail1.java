package com.example.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail1 {

    @SerializedName("lashou_price")
    @Expose
    private LashouPrice lashouPrice;
    @SerializedName("left_time")
    @Expose
    private Integer leftTime;
    @Expose
    private String type;
    @SerializedName("is_new")
    @Expose
    private Integer isNew;
    @Expose
    private String title;
    @Expose
    private String details;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("group_recommend")
    @Expose
    private List<Object> groupRecommend = new ArrayList<Object>();
    @SerializedName("is_collected")
    @Expose
    private Integer isCollected;
    @Expose
    private String value;
    @SerializedName("goods_type")
    @Expose
    private String goodsType;
    @Expose
    private String notice;
    @SerializedName("btn_disabled")
    @Expose
    private Integer btnDisabled;
    @SerializedName("pay_mobile")
    @Expose
    private Integer payMobile;
    @SerializedName("if_join")
    @Expose
    private Integer ifJoin;
    @Expose
    private Signiture signiture;
    @SerializedName("is_self")
    @Expose
    private String isSelf;
    @Expose
    private String product;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @Expose
    private String price;
    @SerializedName("detail_imags")
    @Expose
    private List<String> detailImags = new ArrayList<String>();
    @SerializedName("goods_id")
    @Expose
    private String goodsId;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @Expose
    private Comment comment;
    @SerializedName("goods_show_type")
    @Expose
    private Integer goodsShowType;
    @SerializedName("is_appointment")
    @Expose
    private Integer isAppointment;

    /**
     * 
     * @return
     *     The lashouPrice
     */
    public LashouPrice getLashouPrice() {
        return lashouPrice;
    }

    /**
     * 
     * @param lashouPrice
     *     The lashou_price
     */
    public void setLashouPrice(LashouPrice lashouPrice) {
        this.lashouPrice = lashouPrice;
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
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The isNew
     */
    public Integer getIsNew() {
        return isNew;
    }

    /**
     * 
     * @param isNew
     *     The is_new
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
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
     *     The details
     */
    public String getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 
     * @return
     *     The addressId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 
     * @param addressId
     *     The address_id
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 
     * @return
     *     The groupRecommend
     */
    public List<Object> getGroupRecommend() {
        return groupRecommend;
    }

    /**
     * 
     * @param groupRecommend
     *     The group_recommend
     */
    public void setGroupRecommend(List<Object> groupRecommend) {
        this.groupRecommend = groupRecommend;
    }

    /**
     * 
     * @return
     *     The isCollected
     */
    public Integer getIsCollected() {
        return isCollected;
    }

    /**
     * 
     * @param isCollected
     *     The is_collected
     */
    public void setIsCollected(Integer isCollected) {
        this.isCollected = isCollected;
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
     *     The notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 
     * @param notice
     *     The notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * 
     * @return
     *     The btnDisabled
     */
    public Integer getBtnDisabled() {
        return btnDisabled;
    }

    /**
     * 
     * @param btnDisabled
     *     The btn_disabled
     */
    public void setBtnDisabled(Integer btnDisabled) {
        this.btnDisabled = btnDisabled;
    }

    /**
     * 
     * @return
     *     The payMobile
     */
    public Integer getPayMobile() {
        return payMobile;
    }

    /**
     * 
     * @param payMobile
     *     The pay_mobile
     */
    public void setPayMobile(Integer payMobile) {
        this.payMobile = payMobile;
    }

    /**
     * 
     * @return
     *     The ifJoin
     */
    public Integer getIfJoin() {
        return ifJoin;
    }

    /**
     * 
     * @param ifJoin
     *     The if_join
     */
    public void setIfJoin(Integer ifJoin) {
        this.ifJoin = ifJoin;
    }

    /**
     * 
     * @return
     *     The signiture
     */
    public Signiture getSigniture() {
        return signiture;
    }

    /**
     * 
     * @param signiture
     *     The signiture
     */
    public void setSigniture(Signiture signiture) {
        this.signiture = signiture;
    }

    /**
     * 
     * @return
     *     The isSelf
     */
    public String getIsSelf() {
        return isSelf;
    }

    /**
     * 
     * @param isSelf
     *     The is_self
     */
    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
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
     *     The detailImags
     */
    public List<String> getDetailImags() {
        return detailImags;
    }

    /**
     * 
     * @param detailImags
     *     The detail_imags
     */
    public void setDetailImags(List<String> detailImags) {
        this.detailImags = detailImags;
    }

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
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The comment
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * 
     * @return
     *     The goodsShowType
     */
    public Integer getGoodsShowType() {
        return goodsShowType;
    }

    /**
     * 
     * @param goodsShowType
     *     The goods_show_type
     */
    public void setGoodsShowType(Integer goodsShowType) {
        this.goodsShowType = goodsShowType;
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

}
