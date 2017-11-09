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

public class RouteInfo {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("accessible")
    @Expose
    public String accessible;

    @SerializedName("image")
    @Expose
    public String image;

    @SerializedName("stops")
    public List<StopsInfo> stops = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public List<StopsInfo> getStops() {
        return stops;
    }

    public void setStops(List<StopsInfo> stops) {
        this.stops = stops;
    }
}
