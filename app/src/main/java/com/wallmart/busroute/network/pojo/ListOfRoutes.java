package com.wallmart.busroute.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class ListOfRoutes {
    @SerializedName("routes")
    @Expose
    public List<RouteInfo> routesList = new ArrayList<>();
}
