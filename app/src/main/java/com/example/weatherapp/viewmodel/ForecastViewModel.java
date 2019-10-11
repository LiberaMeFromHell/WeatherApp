package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

public class ForecastViewModel extends AndroidViewModel {

    public ForecastViewModel(@NonNull Application application) {
        super(application);
    }


}
