package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.model.database.WeatherRepository;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    //TODO: DI
    private WeatherRepository weatherRepository;
    private DataReceiver dataReceiver;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository(getApplication());
        dataReceiver = DataReceiver.getInstance();
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
        //TODO: specify a city
        dataReceiver.onObserveCurrentCondition(locationKey);
    }

    public void downloadForecast(String locationKey) {
        //TODO: specify a city
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

}
