package com.example.weatherapp.model.pojo.currentcondition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("Direction")
    @Expose
    private Direction direction;
    @SerializedName("Speed")
    @Expose
    private Speed speed;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
