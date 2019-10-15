package com.example.weatherapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.database.WeatherRepository;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;
import com.example.weatherapp.view.WeatherBackground;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    //TODO: DI
    private WeatherRepository weatherRepository;
    private DataReceiver dataReceiver;
    private WeatherBackground weatherBackground;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository(getApplication());
        dataReceiver = DataReceiver.getInstance();
        weatherBackground = WeatherBackground.getInstance();
    }

    public LiveData<List<Location>> getLocations() {
        return weatherRepository.getCurrentLocationsLiveData();
    }

    public LiveData<List<Location>> getFoundLocations() {
        return dataReceiver.getFoundLocations();
    }

    public void findLocations(String queue) {
        dataReceiver.onObserveLocation(queue);
    }

    public void downloadCurrentCondition(String locationKey) {
        dataReceiver.onObserveCurrentCondition(locationKey);
    }

    public void downloadForecast(String locationKey) {
        dataReceiver.onObserveForecast(locationKey);
    }

    public void addLocation(Location location) {
        weatherRepository.addLocation(location);
    }

    public LiveData<CurrentCondition> getCurrentCondition (){
        return dataReceiver.getCurrentCondition();
    }

    public LiveData<Forecast> getForecast(){
        return dataReceiver.getForecast();
    }

    public LiveData<List<Integer>> getBackground() {
        Log.d("Background", "getBackground");
        return weatherBackground.getBackground();
    }

    public void setBackground(Boolean isDay) {
        weatherBackground.onObserverBackground(isDay);
    }
}
