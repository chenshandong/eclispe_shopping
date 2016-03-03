package com.example.popularmodel;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("show_type")
    @Expose
    private Integer showType;
    @SerializedName("banner_type")
    @Expose
    private String bannerType;
    @Expose
    private String title;
    @SerializedName("direct_open_url")
    @Expose
    private String directOpenUrl;
    @SerializedName("twitter_id")
    @Expose
    private String twitterId;
    @SerializedName("twitter_goods_id")
    @Expose
    private String twitterGoodsId;
    @SerializedName("o_pic_url")
    @Expose
    private String oPicUrl;
    @SerializedName("pic_height")
    @Expose
    private String picHeight;
    @SerializedName("pic_width")
    @Expose
    private String picWidth;
    @SerializedName("alert_key")
    @Expose
    private String alertKey;
    @SerializedName("d_r")
    @Expose
    private String dR;
    @SerializedName("show_shelf_mark")
    @Expose
    private String showShelfMark;
    @Expose
    private String r;
    @SerializedName("is_doota")
    @Expose
    private String isDoota;
    @Expose
    private String shelf;
    @SerializedName("n_pic_file")
    @Expose
    private String nPicFile;
    @SerializedName("shop_id")
    @Expose
    private String shopId;
    @SerializedName("is_promote")
    @Expose
    private Integer isPromote;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("tags_num")
    @Expose
    private String tagsNum;
    @SerializedName("count_like")
    @Expose
    private String countLike;
    @SerializedName("sale_num")
    @Expose
    private String saleNum;
    @SerializedName("h_mark")
    @Expose
    private java.util.List<HMark> hMark = new ArrayList<HMark>();
    @SerializedName("buying_point")
    @Expose
    private String buyingPoint;
    @SerializedName("shop_points_trend")
    @Expose
    private java.util.List<ShopPointsTrend> shopPointsTrend = new ArrayList<ShopPointsTrend>();
    @SerializedName("shop_nick")
    @Expose
    private String shopNick;
    @Expose
    private String remark;
    @SerializedName("primary_key")
    @Expose
    private String primaryKey;
    @SerializedName("pic_url")
    @Expose
    private String picUrl;
    @SerializedName("j_pic_url")
    @Expose
    private String jPicUrl;
    @SerializedName("q_pic_url")
    @Expose
    private String qPicUrl;
    @SerializedName("z_pic_url")
    @Expose
    private String zPicUrl;
    @SerializedName("m_pic_url")
    @Expose
    private String mPicUrl;
    @SerializedName("d_pic_url")
    @Expose
    private String dPicUrl;
    @Expose
    private java.util.List<Recommendation> recommendation = new ArrayList<Recommendation>();
    @Expose
    private java.util.List<Object> rankInfo = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The showType
     */
    public Integer getShowType() {
        return showType;
    }

    /**
     * 
     * @param showType
     *     The show_type
     */
    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    /**
     * 
     * @return
     *     The bannerType
     */
    public String getBannerType() {
        return bannerType;
    }

    /**
     * 
     * @param bannerType
     *     The banner_type
     */
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
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
     *     The directOpenUrl
     */
    public String getDirectOpenUrl() {
        return directOpenUrl;
    }

    /**
     * 
     * @param directOpenUrl
     *     The direct_open_url
     */
    public void setDirectOpenUrl(String directOpenUrl) {
        this.directOpenUrl = directOpenUrl;
    }

    /**
     * 
     * @return
     *     The twitterId
     */
    public String getTwitterId() {
        return twitterId;
    }

    /**
     * 
     * @param twitterId
     *     The twitter_id
     */
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    /**
     * 
     * @return
     *     The twitterGoodsId
     */
    public String getTwitterGoodsId() {
        return twitterGoodsId;
    }

    /**
     * 
     * @param twitterGoodsId
     *     The twitter_goods_id
     */
    public void setTwitterGoodsId(String twitterGoodsId) {
        this.twitterGoodsId = twitterGoodsId;
    }

    /**
     * 
     * @return
     *     The oPicUrl
     */
    public String getOPicUrl() {
        return oPicUrl;
    }

    /**
     * 
     * @param oPicUrl
     *     The o_pic_url
     */
    public void setOPicUrl(String oPicUrl) {
        this.oPicUrl = oPicUrl;
    }

    /**
     * 
     * @return
     *     The picHeight
     */
    public String getPicHeight() {
        return picHeight;
    }

    /**
     * 
     * @param picHeight
     *     The pic_height
     */
    public void setPicHeight(String picHeight) {
        this.picHeight = picHeight;
    }

    /**
     * 
     * @return
     *     The picWidth
     */
    public String getPicWidth() {
        return picWidth;
    }

    /**
     * 
     * @param picWidth
     *     The pic_width
     */
    public void setPicWidth(String picWidth) {
        this.picWidth = picWidth;
    }

    /**
     * 
     * @return
     *     The alertKey
     */
    public String getAlertKey() {
        return alertKey;
    }

    /**
     * 
     * @param alertKey
     *     The alert_key
     */
    public void setAlertKey(String alertKey) {
        this.alertKey = alertKey;
    }

    /**
     * 
     * @return
     *     The dR
     */
    public String getDR() {
        return dR;
    }

    /**
     * 
     * @param dR
     *     The d_r
     */
    public void setDR(String dR) {
        this.dR = dR;
    }

    /**
     * 
     * @return
     *     The showShelfMark
     */
    public String getShowShelfMark() {
        return showShelfMark;
    }

    /**
     * 
     * @param showShelfMark
     *     The show_shelf_mark
     */
    public void setShowShelfMark(String showShelfMark) {
        this.showShelfMark = showShelfMark;
    }

    /**
     * 
     * @return
     *     The r
     */
    public String getR() {
        return r;
    }

    /**
     * 
     * @param r
     *     The r
     */
    public void setR(String r) {
        this.r = r;
    }

    /**
     * 
     * @return
     *     The isDoota
     */
    public String getIsDoota() {
        return isDoota;
    }

    /**
     * 
     * @param isDoota
     *     The is_doota
     */
    public void setIsDoota(String isDoota) {
        this.isDoota = isDoota;
    }

    /**
     * 
     * @return
     *     The shelf
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * 
     * @param shelf
     *     The shelf
     */
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    /**
     * 
     * @return
     *     The nPicFile
     */
    public String getNPicFile() {
        return nPicFile;
    }

    /**
     * 
     * @param nPicFile
     *     The n_pic_file
     */
    public void setNPicFile(String nPicFile) {
        this.nPicFile = nPicFile;
    }

    /**
     * 
     * @return
     *     The shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 
     * @param shopId
     *     The shop_id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 
     * @return
     *     The isPromote
     */
    public Integer getIsPromote() {
        return isPromote;
    }

    /**
     * 
     * @param isPromote
     *     The is_promote
     */
    public void setIsPromote(Integer isPromote) {
        this.isPromote = isPromote;
    }

    /**
     * 
     * @return
     *     The serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 
     * @param serviceType
     *     The service_type
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 
     * @return
     *     The serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 
     * @param serviceId
     *     The service_id
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 
     * @return
     *     The tagsNum
     */
    public String getTagsNum() {
        return tagsNum;
    }

    /**
     * 
     * @param tagsNum
     *     The tags_num
     */
    public void setTagsNum(String tagsNum) {
        this.tagsNum = tagsNum;
    }

    /**
     * 
     * @return
     *     The countLike
     */
    public String getCountLike() {
        return countLike;
    }

    /**
     * 
     * @param countLike
     *     The count_like
     */
    public void setCountLike(String countLike) {
        this.countLike = countLike;
    }

    /**
     * 
     * @return
     *     The saleNum
     */
    public String getSaleNum() {
        return saleNum;
    }

    /**
     * 
     * @param saleNum
     *     The sale_num
     */
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * 
     * @return
     *     The hMark
     */
    public java.util.List<HMark> getHMark() {
        return hMark;
    }

    /**
     * 
     * @param hMark
     *     The h_mark
     */
    public void setHMark(java.util.List<HMark> hMark) {
        this.hMark = hMark;
    }

    /**
     * 
     * @return
     *     The buyingPoint
     */
    public String getBuyingPoint() {
        return buyingPoint;
    }

    /**
     * 
     * @param buyingPoint
     *     The buying_point
     */
    public void setBuyingPoint(String buyingPoint) {
        this.buyingPoint = buyingPoint;
    }

    /**
     * 
     * @return
     *     The shopPointsTrend
     */
    public java.util.List<ShopPointsTrend> getShopPointsTrend() {
        return shopPointsTrend;
    }

    /**
     * 
     * @param shopPointsTrend
     *     The shop_points_trend
     */
    public void setShopPointsTrend(java.util.List<ShopPointsTrend> shopPointsTrend) {
        this.shopPointsTrend = shopPointsTrend;
    }

    /**
     * 
     * @return
     *     The shopNick
     */
    public String getShopNick() {
        return shopNick;
    }

    /**
     * 
     * @param shopNick
     *     The shop_nick
     */
    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    /**
     * 
     * @return
     *     The remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark
     *     The remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * @return
     *     The primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * 
     * @param primaryKey
     *     The primary_key
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * 
     * @return
     *     The picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 
     * @param picUrl
     *     The pic_url
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 
     * @return
     *     The jPicUrl
     */
    public String getJPicUrl() {
        return jPicUrl;
    }

    /**
     * 
     * @param jPicUrl
     *     The j_pic_url
     */
    public void setJPicUrl(String jPicUrl) {
        this.jPicUrl = jPicUrl;
    }

    /**
     * 
     * @return
     *     The qPicUrl
     */
    public String getQPicUrl() {
        return qPicUrl;
    }

    /**
     * 
     * @param qPicUrl
     *     The q_pic_url
     */
    public void setQPicUrl(String qPicUrl) {
        this.qPicUrl = qPicUrl;
    }

    /**
     * 
     * @return
     *     The zPicUrl
     */
    public String getZPicUrl() {
        return zPicUrl;
    }

    /**
     * 
     * @param zPicUrl
     *     The z_pic_url
     */
    public void setZPicUrl(String zPicUrl) {
        this.zPicUrl = zPicUrl;
    }

    /**
     * 
     * @return
     *     The mPicUrl
     */
    public String getMPicUrl() {
        return mPicUrl;
    }

    /**
     * 
     * @param mPicUrl
     *     The m_pic_url
     */
    public void setMPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    /**
     * 
     * @return
     *     The dPicUrl
     */
    public String getDPicUrl() {
        return dPicUrl;
    }

    /**
     * 
     * @param dPicUrl
     *     The d_pic_url
     */
    public void setDPicUrl(String dPicUrl) {
        this.dPicUrl = dPicUrl;
    }

    /**
     * 
     * @return
     *     The recommendation
     */
    public java.util.List<Recommendation> getRecommendation() {
        return recommendation;
    }

    /**
     * 
     * @param recommendation
     *     The recommendation
     */
    public void setRecommendation(java.util.List<Recommendation> recommendation) {
        this.recommendation = recommendation;
    }

    /**
     * 
     * @return
     *     The rankInfo
     */
    public java.util.List<Object> getRankInfo() {
        return rankInfo;
    }

    /**
     * 
     * @param rankInfo
     *     The rankInfo
     */
    public void setRankInfo(java.util.List<Object> rankInfo) {
        this.rankInfo = rankInfo;
    }

}
