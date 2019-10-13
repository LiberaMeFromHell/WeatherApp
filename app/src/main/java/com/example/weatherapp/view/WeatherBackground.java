package com.example.weatherapp.view;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class WeatherBackground {

    private MutableLiveData<List<Integer>> backgroundLiveData = new MutableLiveData();
    private List<Integer> list = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public WeatherBackground() {
        //backgroundLiveData.postValue(R.drawable.bg_day);
    }

    public void onObserverBackground(boolean isDay) {

        compositeDisposable.add(Observable
                .just(isDay)
                .subscribeWith(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean isDay) {
                        if (isDay){
                            Log.d("background", "day");
                            list.clear();
                            list.add(R.drawable.bg_day);
                            backgroundLiveData.postValue(list);
                        }
                        else {
                            Log.d("background", "night");
                            list.clear();
                            list.add(R.drawable.bg_night);
                            backgroundLiveData.postValue(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public MutableLiveData<List<Integer>> getBackground() {
        return backgroundLiveData;
    }

    //TODO:
    public void onDispose(){
        compositeDisposable.dispose();
    }
}
