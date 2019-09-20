package com.example.weatherapp.model.pojo.forecast;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("Minimum")
    @Expose
    @Embedded(prefix = "min_")
    private Minimum minimum;
    @SerializedName("Maximum")
    @Expose
    @Embedded(prefix = "max_")
    private Maximum maximum;

    public Temperature() {
    }

    public Minimum getMinimum() {
        return minimum;
    }

    public void setMinimum(Minimum minimum) {
        this.minimum = minimum;
    }

    public Maximum getMaximum() {
        return maximum;
    }

    public void setMaximum(Maximum maximum) {
        this.maximum = maximum;
    }

}