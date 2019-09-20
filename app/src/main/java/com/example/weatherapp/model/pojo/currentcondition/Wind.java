package com.example.weatherapp.model.pojo.currentcondition;

import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("Direction")
    @Expose
    @Embedded
    private Direction direction;
    @SerializedName("Speed")
    @Expose
    @Embedded
    private Speed speed;

    public Wind() {}

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
}
