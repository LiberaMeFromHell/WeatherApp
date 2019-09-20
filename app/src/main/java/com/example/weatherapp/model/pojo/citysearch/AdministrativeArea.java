package com.example.weatherapp.model.pojo.citysearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdministrativeArea {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;

    public AdministrativeArea() {
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }


}
