package com.example.popularmodel;

import com.google.gson.annotations.Expose;

public class ShopPointsTrend {

    @Expose
    private String text;
    @Expose
    private String avg;
    @Expose
    private String ratio;
    @Expose
    private String mark;
    @Expose
    private String desc;

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The avg
     */
    public String getAvg() {
        return avg;
    }

    /**
     * 
     * @param avg
     *     The avg
     */
    public void setAvg(String avg) {
        this.avg = avg;
    }

    /**
     * 
     * @return
     *     The ratio
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * 
     * @param ratio
     *     The ratio
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    /**
     * 
     * @return
     *     The mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * 
     * @param mark
     *     The mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 
     * @return
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
