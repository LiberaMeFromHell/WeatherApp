package com.example.weatherapp.model.pojo.citysearch;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Location {

    @SerializedName("Version")
    @Expose
    private Integer version;
    @PrimaryKey
    @SerializedName("Key")
    @Expose
    @NonNull
    private String stringKey;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Rank")
    @Expose
    private Integer rank;
    @SerializedName("LocalizedName")
    @Expose
    @Embedded
    private String localizedName;
    @SerializedName("Country")
    @Expose
    @Embedded(prefix = "c_")
    private Country country;
    @SerializedName("AdministrativeArea")
    @Expose
    @Embedded(prefix = "a_")
    private AdministrativeArea administrativeArea;

    public Location() {
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        this.administrativeArea = administrativeArea;
    }
}