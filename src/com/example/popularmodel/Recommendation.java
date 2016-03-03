package com.example.popularmodel;

import com.google.gson.annotations.Expose;

public class Recommendation {

    @Expose
    private String wording;
    @Expose
    private String url;
    @Expose
    private String clickable;

    /**
     * 
     * @return
     *     The wording
     */
    public String getWording() {
        return wording;
    }

    /**
     * 
     * @param wording
     *     The wording
     */
    public void setWording(String wording) {
        this.wording = wording;
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
     *     The clickable
     */
    public String getClickable() {
        return clickable;
    }

    /**
     * 
     * @param clickable
     *     The clickable
     */
    public void setClickable(String clickable) {
        this.clickable = clickable;
    }

}
