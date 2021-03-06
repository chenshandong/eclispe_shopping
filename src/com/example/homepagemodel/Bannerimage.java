package com.example.homepagemodel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Bannerimage {

    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     * 
     * @return
     *     The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

}
