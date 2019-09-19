package com.example.weatherapp.model.network;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataReceiver {

    private static DataReceiver dataReceiver;
    private WeatherAPI weatherAPI;

    public DataReceiver() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    public static DataReceiver getInstance() {
        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
        }
        return dataReceiver;

    }

    public List<DailyForecast> downloadDailyForecast(String apiKey, String queue) {



        weatherAPI.getForecast(apiKey,true,true).enqueue(new Callback<Forecast>() {
             @Override
             public void onResponse(Call<Forecast> call, Response<Forecast> response) {

             }

             @Override
             public void onFailure(Call<Forecast> call, Throwable t) {

             }
         });
    }

    public CurrentCondition downloadCurrentCondition(String apiKey, String queue) {

    }

    public Forecast downloadForecast(String apiKey, String queue) {

    }
}
