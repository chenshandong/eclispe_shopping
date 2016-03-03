package com.example.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("cate_id")
    @Expose
    private String cateId;
    @SerializedName("cate_type")
    @Expose
    private Integer cateType;
    @SerializedName("re_name")
    @Expose
    private String reName;
    @SerializedName("cate_name")
    @Expose
    private String cateName;
    @SerializedName("sub_cate")
    @Expose
    private List<SubCate> subCate = new ArrayList<SubCate>();

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
     *     The cateId
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * 
     * @param cateId
     *     The cate_id
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    /**
     * 
     * @return
     *     The cateType
     */
    public Integer getCateType() {
        return cateType;
    }

    /**
     * 
     * @param cateType
     *     The cate_type
     */
    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    /**
     * 
     * @return
     *     The reName
     */
    public String getReName() {
        return reName;
    }

    /**
     * 
     * @param reName
     *     The re_name
     */
    public void setReName(String reName) {
        this.reName = reName;
    }

    /**
     * 
     * @return
     *     The cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * 
     * @param cateName
     *     The cate_name
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    /**
     * 
     * @return
     *     The subCate
     */
    public List<SubCate> getSubCate() {
        return subCate;
    }

    /**
     * 
     * @param subCate
     *     The sub_cate
     */
    public void setSubCate(List<SubCate> subCate) {
        this.subCate = subCate;
    }

}
