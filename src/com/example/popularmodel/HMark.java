package com.example.popularmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HMark {

    @SerializedName("mark_id")
    @Expose
    private String markId;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("img_width")
    @Expose
    private String imgWidth;
    @SerializedName("img_height")
    @Expose
    private String imgHeight;
    @SerializedName("campaign_price")
    @Expose
    private String campaignPrice;
    @SerializedName("activity_mark_order")
    @Expose
    private Object activityMarkOrder;

    /**
     * 
     * @return
     *     The markId
     */
    public String getMarkId() {
        return markId;
    }

    /**
     * 
     * @param markId
     *     The mark_id
     */
    public void setMarkId(String markId) {
        this.markId = markId;
    }

    /**
     * 
     * @return
     *     The imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 
     * @param imgUrl
     *     The img_url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 
     * @return
     *     The imgWidth
     */
    public String getImgWidth() {
        return imgWidth;
    }

    /**
     * 
     * @param imgWidth
     *     The img_width
     */
    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * 
     * @return
     *     The imgHeight
     */
    public String getImgHeight() {
        return imgHeight;
    }

    /**
     * 
     * @param imgHeight
     *     The img_height
     */
    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * 
     * @return
     *     The campaignPrice
     */
    public String getCampaignPrice() {
        return campaignPrice;
    }

    /**
     * 
     * @param campaignPrice
     *     The campaign_price
     */
    public void setCampaignPrice(String campaignPrice) {
        this.campaignPrice = campaignPrice;
    }

    /**
     * 
     * @return
     *     The activityMarkOrder
     */
    public Object getActivityMarkOrder() {
        return activityMarkOrder;
    }

    /**
     * 
     * @param activityMarkOrder
     *     The activity_mark_order
     */
    public void setActivityMarkOrder(Object activityMarkOrder) {
        this.activityMarkOrder = activityMarkOrder;
    }

}
