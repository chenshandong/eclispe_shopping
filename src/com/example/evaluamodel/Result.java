package com.example.evaluamodel;

import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private Integer vid;
    @Expose
    private String content;
    @Expose
    private Uid uid;
    @Expose
    private String createdAt;
    @Expose
    private String updatedAt;
    @Expose
    private String objectId;

    /**
     * 
     * @return
     *     The vid
     */
    public Integer getVid() {
        return vid;
    }

    /**
     * 
     * @param vid
     *     The vid
     */
    public void setVid(Integer vid) {
        this.vid = vid;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The uid
     */
    public Uid getUid() {
        return uid;
    }

    /**
     * 
     * @param uid
     *     The uid
     */
    public void setUid(Uid uid) {
        this.uid = uid;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 
     * @param objectId
     *     The objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
