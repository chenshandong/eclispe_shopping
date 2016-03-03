package com.example.homepagemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Desc {

    @Expose
    private String type;
    @SerializedName("padding_top")
    @Expose
    private String paddingTop;
    @SerializedName("padding_left")
    @Expose
    private String paddingLeft;
    @SerializedName("padding_right")
    @Expose
    private String paddingRight;
    @SerializedName("padding_bottom")
    @Expose
    private String paddingBottom;
    @SerializedName("bg_color")
    @Expose
    private String bgColor;
    @Expose
    private String height;
    @SerializedName("item_spacing")
    @Expose
    private String itemSpacing;
    @SerializedName("item_width")
    @Expose
    private String itemWidth;
    @Expose
    private String column;

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
     *     The paddingTop
     */
    public String getPaddingTop() {
        return paddingTop;
    }

    /**
     * 
     * @param paddingTop
     *     The padding_top
     */
    public void setPaddingTop(String paddingTop) {
        this.paddingTop = paddingTop;
    }

    /**
     * 
     * @return
     *     The paddingLeft
     */
    public String getPaddingLeft() {
        return paddingLeft;
    }

    /**
     * 
     * @param paddingLeft
     *     The padding_left
     */
    public void setPaddingLeft(String paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    /**
     * 
     * @return
     *     The paddingRight
     */
    public String getPaddingRight() {
        return paddingRight;
    }

    /**
     * 
     * @param paddingRight
     *     The padding_right
     */
    public void setPaddingRight(String paddingRight) {
        this.paddingRight = paddingRight;
    }

    /**
     * 
     * @return
     *     The paddingBottom
     */
    public String getPaddingBottom() {
        return paddingBottom;
    }

    /**
     * 
     * @param paddingBottom
     *     The padding_bottom
     */
    public void setPaddingBottom(String paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    /**
     * 
     * @return
     *     The bgColor
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * 
     * @param bgColor
     *     The bg_color
     */
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * 
     * @return
     *     The height
     */
    public String getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The itemSpacing
     */
    public String getItemSpacing() {
        return itemSpacing;
    }

    /**
     * 
     * @param itemSpacing
     *     The item_spacing
     */
    public void setItemSpacing(String itemSpacing) {
        this.itemSpacing = itemSpacing;
    }

    /**
     * 
     * @return
     *     The itemWidth
     */
    public String getItemWidth() {
        return itemWidth;
    }

    /**
     * 
     * @param itemWidth
     *     The item_width
     */
    public void setItemWidth(String itemWidth) {
        this.itemWidth = itemWidth;
    }

    /**
     * 
     * @return
     *     The column
     */
    public String getColumn() {
        return column;
    }

    /**
     * 
     * @param column
     *     The column
     */
    public void setColumn(String column) {
        this.column = column;
    }

}
