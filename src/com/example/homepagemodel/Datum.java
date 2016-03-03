package com.example.homepagemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pic_url")
    @Expose
    private String picUrl;
    @SerializedName("pic_width")
    @Expose
    private String picWidth;
    @SerializedName("pic_height")
    @Expose
    private String picHeight;
    @Expose
    private String url;
    @Expose
    private String r;
    @Expose
    private String title;

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
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
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

}
