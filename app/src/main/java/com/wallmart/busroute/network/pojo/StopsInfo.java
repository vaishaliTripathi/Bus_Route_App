package com.wallmart.busroute.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class StopsInfo {
    @SerializedName("name")
    @Expose
    public String name;

    public String getName() {
        return name;
    }
}
