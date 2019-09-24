package com.example.weatherapp.model.network;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataReceiver {

    private static DataReceiver dataReceiver;
    private WeatherAPI weatherAPI;

    private DataReceiver() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    public static DataReceiver getInstance() {
        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
        }
        return dataReceiver;

    }

    public Observable<Forecast> getForecastObserver(String apiKey, String locationKey) {
        return weatherAPI.getForecast(apiKey, locationKey,false,true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CurrentCondition> getCurrentConditionObservable(String apiKey, String locationKey) {
        return weatherAPI.getCurrentCondition(apiKey, locationKey, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Location>> getCityList(String apiKey, String queue) {
        return weatherAPI.getLocation(apiKey,queue)
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()));

    }
}
