package com.example.guessmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Result  {

    /**
	 * 
	 */
	@Expose
    private List<Goodlist> goodlist = new ArrayList<Goodlist>();
    @Expose
    private String topid;

    /**
     * 
     * @return
     *     The goodlist
     */
    public List<Goodlist> getGoodlist() {
        return goodlist;
    }

    /**
     * 
     * @param goodlist
     *     The goodlist
     */
    public void setGoodlist(List<Goodlist> goodlist) {
        this.goodlist = goodlist;
    }

    /**
     * 
     * @return
     *     The topid
     */
    public String getTopid() {
        return topid;
    }

    /**
     * 
     * @param topid
     *     The topid
     */
    public void setTopid(String topid) {
        this.topid = topid;
    }

}
