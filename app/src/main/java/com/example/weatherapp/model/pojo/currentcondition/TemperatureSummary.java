package com.example.weatherapp.model.pojo.currentcondition;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemperatureSummary {
    @SerializedName("Past6HourRange")
    @Expose
    @Embedded
    private Past6HourRange past6HourRange;

    public TemperatureSummary() {
    }

    public Past6HourRange getPast6HourRange() {
        return past6HourRange;
    }

    public void setPast6HourRange(Past6HourRange past6HourRange) {
        this.past6HourRange = past6HourRange;
    }
}
