package com.example.weatherapp.model.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private WeatherDatabase weatherDatabase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Location>> currentLocationsLiveData = new MutableLiveData();
    private LiveData<List<CurrentCondition>> currentConditionLiveData = new MutableLiveData();
    private LiveData<List<DailyForecast>> forecastLiveData = new MutableLiveData();

    public WeatherRepository(Context context) {
        weatherDatabase = WeatherDatabase.getInstance(context);
        onObserveLocationList();
    }

    public void insertForecast(List<DailyForecast> dailyForecastList) {
        weatherDatabase.weatherDao().insertForecast(dailyForecastList);
    }

    public void deleteForecast() {
        weatherDatabase.weatherDao().deleteForecast();
    }

    public void insertCurrentCondition(CurrentCondition currentCondition) {
        weatherDatabase.weatherDao().insertCurrentCondition(currentCondition);
    }

    public void deleteCurrentCondition() {
        weatherDatabase.weatherDao().deleteCurrentCondition();
    }

    public void addLocation(Location location) {
        Observable<Location> observable = Observable
                .just(location)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        observable.subscribeWith(new DisposableObserver<Location>() {
            @Override
            public void onNext(Location location) {
                weatherDatabase.weatherDao().insertLocation(location);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void deleteLocation(String mKey) {
        weatherDatabase.weatherDao().deleteLocation(mKey);
    }

    public void onObserveLocationList() {
        compositeDisposable.add(weatherDatabase.weatherDao().getLocations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<List<Location>>() {
                    @Override
                    public void accept(List<Location> locations) throws Exception {
                        currentLocationsLiveData.postValue(locations);
                    }
                }));
    }

    public MutableLiveData<List<Location>> getCurrentLocationsLiveData() {
        return currentLocationsLiveData;
    }

    public Flowable<Location> getLocationObservable(String localizedName) {
        Log.d("tag", "getLocationObservable: " + localizedName);
        return weatherDatabase.weatherDao().getLocationByName(localizedName)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void onDispose() {
        //TODO:implement
        compositeDisposable.dispose();
    }
}
