package com.example.weatherapp.model.pojo.currentcondition;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DewPoint {

    @SerializedName("Metric")
    @Expose
    @Embedded
    private Metric metric;

    public DewPoint() {
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
