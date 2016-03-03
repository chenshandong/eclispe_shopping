package com.example.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class HeaderType {

    @Expose
    private List<Type> type = new ArrayList<Type>();

    /**
     * 
     * @return
     *     The type
     */
    public List<Type> getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(List<Type> type) {
        this.type = type;
    }

}
