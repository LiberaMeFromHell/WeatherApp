package com.example.weatherapp.model.network;

import androidx.annotation.Nullable;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPI {

    @GET
    Call<List<Location>> getLocation(String apiKey,String queue);
    @GET
    Call<CurrentCondition> getCurrentCondition(String apiKey, @Nullable Boolean details);
    @GET
    Call<Forecast> getForecast(String apiKey, @Nullable Boolean details, @Nullable Boolean metrics);
}
