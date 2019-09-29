package com.example.weatherapp.model.pojo.currentcondition;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Past6HourRange {
    @SerializedName("Minimum")
    @Expose
    @Embedded(prefix = "pmin_")
    private Minimum minimum;
    @SerializedName("Maximum")
    @Expose
    @Embedded(prefix = "pmax_")
    private Maximum maximum;

    public Past6HourRange() {
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
