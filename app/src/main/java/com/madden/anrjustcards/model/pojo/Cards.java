package com.madden.anrjustcards.model.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cards {

    @SerializedName("imageUrlTemplate")
    @Expose
    public String imageUrlTemplate;
    @SerializedName("data")
    @Expose
    public List<Datum> data = new ArrayList<Datum>();
    @SerializedName("total")
    @Expose
    public int total;
    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("version_number")
    @Expose
    public String versionNumber;
    @SerializedName("last_updated")
    @Expose
    public String lastUpdated;
}