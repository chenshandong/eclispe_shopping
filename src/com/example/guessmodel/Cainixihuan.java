package com.example.guessmodel;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Cainixihuan  {

    /**
	 * 
	 */
	@Expose
    private Integer ret;
    @Expose
    private String msg;
    @Expose
    private Result result;
    @Expose
    private String token;

    /**
     * 
     * @return
     *     The ret
     */
    public Integer getRet() {
        return ret;
    }

    /**
     * 
     * @param ret
     *     The ret
     */
    public void setRet(Integer ret) {
        this.ret = ret;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The result
     */
    public Result getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
