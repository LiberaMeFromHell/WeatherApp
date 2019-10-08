package com.example.weatherapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class CurrentConditionFragment extends Fragment {

    private TextView textCurrentTemperatureMax;
    private TextView textCurrentTemperatureMin;
    private TextView textCurrentTemperatureAverage;
    private TextView textWeather;
    private TextView textRealFeelTemperature;
    private TextView textHumidity;
    private TextView textWind;

    private ImageView iconWeather;
    private ImageView iconWind;
    private ImageView iconRelativeHumidity;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_condition, container, false);

        textCurrentTemperatureAverage = view.findViewById(R.id.textCurrentTemperatureAverage);
        textCurrentTemperatureMax = view.findViewById(R.id.textCurrentTemperatureMax);
        textCurrentTemperatureMin = view.findViewById(R.id.textCurrentTemperatureMin);
        textWeather = view.findViewById(R.id.textWeather);
        textRealFeelTemperature = view.findViewById(R.id.textRealFeelTemperature);
        textHumidity = view.findViewById(R.id.textHumidity);
        textWind = view.findViewById(R.id.textWind);

        iconWeather = view.findViewById(R.id.iconWeather);
        iconWind = view.findViewById(R.id.iconWind);
        iconRelativeHumidity = view.findViewById(R.id.iconRelativeHumidity2);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void updateCurrentCondition(CurrentCondition currentCondition){
        Log.d("tag", "RealFeel: "+ currentCondition.getRealFeelTemperature()
                + ", Weather: "+ currentCondition.getWeatherText()
                + ", Average temperature: "+ currentCondition.getTemperature().getMetric().getValue());

        textCurrentTemperatureMax.setText(currentCondition.getTemperatureSummary().getPast6HourRange().getMaximum().getMetric().getValue().toString());
        textCurrentTemperatureMin.setText(currentCondition.getTemperatureSummary().getPast6HourRange().getMinimum().getMetric().getValue().toString());
        textCurrentTemperatureAverage.setText(currentCondition.getTemperature().getMetric().getValue().toString());
        textWeather.setText(currentCondition.getWeatherText());
        textRealFeelTemperature.setText(currentCondition.getRealFeelTemperature().getMetric().getValue().toString());
        //textHumidity.setText(currentCondition.getRelativeHumidity());
        textWind.setText(currentCondition.getWind().getSpeed().getMetric().getValue().toString());

        /*iconWeather;
        iconWind;
        iconRelativeHumidity;*/
    }

    void updateData(String locationKey) {
        String apiKey = BuildConfig.ApiKey;
        DataReceiver dataReceiver = DataReceiver.getInstance();
        compositeDisposable.add(
                dataReceiver.getCurrentConditionObservable(locationKey,apiKey)
                        .subscribeWith(new DisposableObserver<List<CurrentCondition>>() {
                            @Override
                            public void onNext(List<CurrentCondition> currentCondition) {
                                Log.d("tag", "onViewCreated");
                                updateCurrentCondition(currentCondition.get(0));
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
}
