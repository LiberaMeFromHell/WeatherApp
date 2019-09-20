package com.example.weatherapp.model.pojo.currentcondition;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CurrentCondition {

    @PrimaryKey(autoGenerate = true)
    private int primaryKey;
    @SerializedName("LocalObservationDateTime")
    @Expose
    private String localObservationDateTime;
    @SerializedName("EpochTime")
    @Expose
    private Integer epochTime;
    @SerializedName("WeatherText")
    @Expose
    private String weatherText;
    @SerializedName("WeatherIcon")
    @Expose
    private Integer weatherIcon;
    @SerializedName("HasPrecipitation")
    @Expose
    private Boolean hasPrecipitation;
    @SerializedName("IsDayTime")
    @Expose
    private Boolean isDayTime;
    @SerializedName("Temperature")
    @Expose
    @Embedded(prefix = "t_")
    private Temperature temperature;
    @SerializedName("RealFeelTemperature")
    @Expose
    @Embedded(prefix = "rft_")
    private RealFeelTemperature realFeelTemperature;
    @SerializedName("RelativeHumidity")
    @Expose
    private Integer relativeHumidity;
    @SerializedName("DewPoint")
    @Expose
    @Embedded(prefix = "dp_")
    private DewPoint dewPoint;
    @SerializedName("Wind")
    @Expose
    @Embedded(prefix = "w_")
    private Wind wind;
    @SerializedName("UVIndex")
    @Expose
    private Integer uVIndex;
    @SerializedName("UVIndexText")
    @Expose
    private String uVIndexText;
    @SerializedName("ObstructionsToVisibility")
    @Expose
    private String obstructionsToVisibility;
    @SerializedName("CloudCover")
    @Expose
    private Integer cloudCover;
    @SerializedName("ApparentTemperature")
    @Expose
    @Embedded(prefix = "aT_")
    private ApparentTemperature apparentTemperature;
    @SerializedName("MobileLink")
    @Expose
    private String mobileLink;
    @SerializedName("Link")
    @Expose
    private String link;

    public CurrentCondition() {}

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getLocalObservationDateTime() {
        return localObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        this.localObservationDateTime = localObservationDateTime;
    }

    public Integer getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Integer epochTime) {
        this.epochTime = epochTime;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public Integer getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(Integer weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Boolean getHasPrecipitation() {
        return hasPrecipitation;
    }

    public void setHasPrecipitation(Boolean hasPrecipitation) {
        this.hasPrecipitation = hasPrecipitation;
    }

    public Boolean getDayTime() {
        return isDayTime;
    }

    public void setDayTime(Boolean dayTime) {
        isDayTime = dayTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public RealFeelTemperature getRealFeelTemperature() {
        return realFeelTemperature;
    }

    public void setRealFeelTemperature(RealFeelTemperature realFeelTemperature) {
        this.realFeelTemperature = realFeelTemperature;
    }

    public Integer getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Integer relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public DewPoint getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(DewPoint dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Integer getuVIndex() {
        return uVIndex;
    }

    public void setuVIndex(Integer uVIndex) {
        this.uVIndex = uVIndex;
    }

    public String getuVIndexText() {
        return uVIndexText;
    }

    public void setuVIndexText(String uVIndexText) {
        this.uVIndexText = uVIndexText;
    }

    public String getObstructionsToVisibility() {
        return obstructionsToVisibility;
    }

    public void setObstructionsToVisibility(String obstructionsToVisibility) {
        this.obstructionsToVisibility = obstructionsToVisibility;
    }

    public Integer getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Integer cloudCover) {
        this.cloudCover = cloudCover;
    }

    public ApparentTemperature getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(ApparentTemperature apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}