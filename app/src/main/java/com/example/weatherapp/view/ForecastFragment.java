package com.example.weatherapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ForecastFragment extends Fragment {

    TextView textForecast1;
    TextView textForecast2;
    TextView textForecast3;
    TextView textForecast4;

    TextView textForecastTemperature1;
    TextView textForecastTemperature2;
    TextView textForecastTemperature3;
    TextView textForecastTemperature4;

    ImageView iconRelativeHumidity1;
    ImageView iconRelativeHumidity2;
    ImageView iconRelativeHumidity3;
    ImageView iconRelativeHumidity4;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        textForecast1 = view.findViewById(R.id.textForecast1);
        textForecast2 = view.findViewById(R.id.textForecast2);
        textForecast3 = view.findViewById(R.id.textForecast3);
        textForecast4 = view.findViewById(R.id.textForecast4);

        textForecastTemperature1 = view.findViewById(R.id.textForecastTemperature1);
        textForecastTemperature2 = view.findViewById(R.id.textForecastTemperature2);
        textForecastTemperature3 = view.findViewById(R.id.textForecastTemperature3);
        textForecastTemperature4 = view.findViewById(R.id.textForecastTemperature4);

        iconRelativeHumidity1 = view.findViewById(R.id.iconRelativeHumidity1);
        iconRelativeHumidity2 = view.findViewById(R.id.iconRelativeHumidity2);
        iconRelativeHumidity3 = view.findViewById(R.id.iconRelativeHumidity3);
        iconRelativeHumidity4 = view.findViewById(R.id.iconRelativeHumidity4);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String apiKey = BuildConfig.ApiKey;
        DataReceiver dataReceiver = DataReceiver.getInstance();
        dataReceiver.getForecastObserver(apiKey,"295954").
                subscribeWith(new DisposableObserver<Forecast>() {
                    @Override
                    public void onNext(Forecast forecast) {
                        updateCurrentCondition(forecast);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void updateCurrentCondition(Forecast forecast){
        Log.d("tag", "Forecast:" + forecast.getDailyForecasts().size());
        for(DailyForecast dailyForecast: forecast.getDailyForecasts()) {
            Log.d("tag", "Forecast:" + dailyForecast.getDay());
        }
    }
}
