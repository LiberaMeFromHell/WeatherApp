package com.example.weatherapp.model.network;

import android.util.Log;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataReceiver {

    private static DataReceiver dataReceiver;
    private WeatherAPI weatherAPI;

    private DataReceiver() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Log.d("tag", "DataReceiver");
        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    public static DataReceiver getInstance() {
        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
        }
        return dataReceiver;

    }

    public Observable<Forecast> getForecastObserver(String locationKey, String apiKey) {
        return weatherAPI.getForecast(locationKey,apiKey,false,true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<CurrentCondition>> getCurrentConditionObservable(String locationKey, String apiKey) {
        Log.d("tag", "getCurrentConditionObservable");
        return weatherAPI.getCurrentCondition(locationKey,apiKey,true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Location>> getCityList(String apiKey, String queue) {
        return weatherAPI.getLocation(apiKey,queue)
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()));

    }
}
