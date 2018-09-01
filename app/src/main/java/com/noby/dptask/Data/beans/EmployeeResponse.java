package com.noby.dptask.Data.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class EmployeeResponse {

    @SerializedName("data")
    private List<Employee> data;

    @SerializedName("links")
    private links links;

    @SerializedName("meta")
    private meta meta;

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

    public com.noby.dptask.Data.beans.links getLinks() {
        return links;
    }

    public void setLinks(com.noby.dptask.Data.beans.links links) {
        this.links = links;
    }

    public com.noby.dptask.Data.beans.meta getMeta() {
        return meta;
    }

    public void setMeta(com.noby.dptask.Data.beans.meta meta) {
        this.meta = meta;
    }
}
