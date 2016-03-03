package com.example.homepagemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homepage {

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @Expose
    private String message;
    @Expose
    private Data data;
    @Expose
    private String r;

    /**
     * 
     * @return
     *     The errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *     The error_code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
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

}
