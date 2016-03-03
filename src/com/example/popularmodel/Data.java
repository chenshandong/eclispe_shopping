package com.example.popularmodel;


import java.util.ArrayList;

import com.google.gson.annotations.Expose;

public class Data {

    @Expose
    private java.util.List<List> list = new ArrayList<List>();
    @Expose
    private String trace;

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    /**
     * 
     * @return
     *     The trace
     */
    public String getTrace() {
        return trace;
    }

    /**
     * 
     * @param trace
     *     The trace
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }

}
