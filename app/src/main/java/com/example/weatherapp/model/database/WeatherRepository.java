package com.example.weatherapp.model.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private static WeatherDatabase weatherDatabase;
    private static WeatherRepository weatherRepository;

    private WeatherRepository(Context context) {
        weatherDatabase = WeatherDatabase.getInstance(context);
    }

    public static WeatherRepository getInstance(Context context) {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository(context);
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

    public static Flowable<List<Location>> getLocationListObservable() {

        return weatherDatabase.weatherDao().getLocations()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Flowable<Location> getLocationObservable(String localizedName) {
        Log.d("tag", "getLocationObservable: " + localizedName);
        return weatherDatabase.weatherDao().getLocationByName(localizedName)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
