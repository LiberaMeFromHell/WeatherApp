package com.example.weatherapp.model.database;

import android.content.Context;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;

import java.util.List;

public class WeatherRepository {

    private static WeatherDatabase weatherDatabase;
    private static WeatherRepository weatherRepository;

    public WeatherRepository getInstance(Context context) {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository();
        }
        return weatherRepository;
    }

    public static void insertForecast(List<DailyForecast> dailyForecastList) {
        weatherDatabase.weatherDao().insertForecast(dailyForecastList);
    }

    public static void deleteForecast() {
        weatherDatabase.weatherDao().deleteForecast();
    }

    public static void insertCurrentCondition(CurrentCondition currentCondition) {
        weatherDatabase.weatherDao().insertCurrentCondition(currentCondition);
    }

    public static void deleteCurrentCondition() {
        weatherDatabase.weatherDao().deleteCurrentCondition();
    }

    public static void insertLocation(Location location) {
        weatherDatabase.weatherDao().insertLocation(location);
    }

    public static void deleteLocation(String mKey) {
        weatherDatabase.weatherDao().deleteLocation(mKey);
    }
}
