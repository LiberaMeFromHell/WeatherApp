package com.example.weatherapp.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface WeatherDao {

    //5 days forecast
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForecast(List<DailyForecast> dailyForecastList);

    @Query("DELETE FROM DailyForecast")
    void deleteForecast();

    //Current weather
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrentCondition(CurrentCondition currentCondition);

    @Query("DELETE FROM CurrentCondition")
    void deleteCurrentCondition();

    //Location list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocation(Location location);

    @Query("DELETE FROM Location WHERE stringKey = :mKey")
    void deleteLocation(String mKey);

    @Query("SELECT * FROM Location")
    Flowable<List<Location>> getLocations();

    @Query("SELECT * FROM Location WHERE localizedName LIKE :localizedName")
    Flowable<Location> getLocationByName(String localizedName);
}
