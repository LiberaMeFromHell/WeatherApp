package com.example.weatherapp.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp.model.pojo.forecast.DailyForecast;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForecast(List<DailyForecast> dailyForecastList);

    @Query("DELETE FROM DailyForecast")
    void deleteAll();


}
