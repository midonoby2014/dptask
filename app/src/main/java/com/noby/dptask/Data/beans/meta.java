package com.noby.dptask.Data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class meta {
    @SerializedName("current_page")
    public int current_page;
    @SerializedName("from")
    public int from;
    @SerializedName("last_page")
    public int last_page;
    @SerializedName("path")
    public String path;
    @SerializedName("per_page")
    public int per_page;
    @SerializedName("to")
    public int to;
    @SerializedName("total")
    public int total;
}
