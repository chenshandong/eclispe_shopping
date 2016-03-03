package com.example.homepagemodel;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;

public class List {

    @Expose
    private Desc desc;
    @Expose
    private java.util.List<Object> data = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The desc
     */
    public Desc getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(Desc desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The data
     */
    public java.util.List<Object> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(java.util.List<Object> data) {
        this.data = data;
    }

}
