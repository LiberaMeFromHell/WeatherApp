package com.example.weatherapp.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;

@Database(entities = {Location.class, DailyForecast.class, CurrentCondition.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

    private static WeatherDatabase weatherDB;

    static WeatherDatabase getInstance(Context context) {

        if (weatherDB == null) {
            weatherDB = Room.databaseBuilder(context, WeatherDatabase.class,"WeatherDatabase").build();
        }
        return weatherDB;
    }
}
