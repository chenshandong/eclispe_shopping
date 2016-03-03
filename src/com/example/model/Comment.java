package com.example.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @Expose
    private Tags tags;
    @Expose
    private Integer count;
    @Expose
    private List<Object> items = new ArrayList<Object>();
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;

    /**
     * 
     * @return
     *     The tags
     */
    public Tags getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Object> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Object> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The totalScore
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     * 
     * @param totalScore
     *     The total_score
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

}
