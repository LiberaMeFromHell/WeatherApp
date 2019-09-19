package com.example.weatherapp.model.pojo.currentcondition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speed {
    @SerializedName("Metric")
    @Expose
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
