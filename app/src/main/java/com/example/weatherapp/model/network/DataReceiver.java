package com.example.weatherapp.model.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataReceiver {

    String apiKey;
    private static DataReceiver dataReceiver;
    private WeatherAPI weatherAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Location>> foundLocationsLiveData = new MutableLiveData();
    private MutableLiveData<Forecast> forecastLiveData = new MutableLiveData();
    private MutableLiveData<CurrentCondition> currentConditionLiveData = new MutableLiveData();

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
        apiKey =  BuildConfig.ApiKey;
        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    public static DataReceiver getInstance() {
        if (dataReceiver == null) {
            dataReceiver = new DataReceiver();
        }
        return dataReceiver;

    }

    public void onObserveForecast(String locationKey) {
        compositeDisposable.add(
                weatherAPI.getForecast(locationKey, apiKey, false, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Forecast>() {
                    @Override
                    public void onNext(Forecast forecasts) {
                        forecastLiveData.postValue(forecasts);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void onObserveCurrentCondition(String locationKey) {
        Log.d("tag", "getCurrentConditionObservable");
        compositeDisposable.add(
                weatherAPI.getCurrentCondition(locationKey, apiKey, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<CurrentCondition>>() {
                    @Override
                    public void onNext(List<CurrentCondition> currentCondition) {
                        currentConditionLiveData.postValue(currentCondition.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void onObserveLocation(String queue) {
        compositeDisposable.add(
                weatherAPI.getLocation(apiKey, queue)
                        .subscribeOn(Schedulers.io())
                        .observeOn((AndroidSchedulers.mainThread()))
                        .subscribeWith(new DisposableObserver<List<Location>>() {
                            @Override
                            public void onNext(List<Location> locations) {
                                foundLocationsLiveData.postValue(locations);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("tag", "onError" + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));
    }

    public MutableLiveData<List<Location>> getFoundLocations() {
        return foundLocationsLiveData;
    }

    public MutableLiveData<CurrentCondition> getCurrentCondition() {
        return currentConditionLiveData;
    }

    public MutableLiveData<Forecast> getForecast() {
        return forecastLiveData;
    }

    public void onDispose() {
        //TODO: implement
        compositeDisposable.dispose();
    }
}
