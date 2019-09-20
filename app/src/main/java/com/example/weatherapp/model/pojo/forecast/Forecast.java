package com.example.weatherapp.model.pojo.forecast;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Forecast {

    @SerializedName("Headline")
    @Expose
    @Embedded
    private Headline headline;
    @SerializedName("DailyForecasts")
    @Expose
    @Embedded
    private List<DailyForecast> dailyForecasts = null;

    public Forecast() {
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

}