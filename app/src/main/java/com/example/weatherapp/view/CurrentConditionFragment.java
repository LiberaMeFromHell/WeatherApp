package com.example.weatherapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import io.reactivex.disposables.CompositeDisposable;

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

    private WeatherViewModel viewModel;

    private WeatherIcon weatherIcon = new WeatherIcon();

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
        iconRelativeHumidity = view.findViewById(R.id.iconWeatherDay2);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        viewModel.getCurrentCondition().observe(this, new Observer<CurrentCondition>() {
            @Override
            public void onChanged(CurrentCondition currentCondition) {
                updateCurrentCondition(currentCondition);
            }
        });

        return view;
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

        textCurrentTemperatureMax.setText(""+Math.round(currentCondition.getTemperatureSummary().getPast6HourRange().getMaximum().getMetric().getValue()));
        textCurrentTemperatureMin.setText(""+Math.round(currentCondition.getTemperatureSummary().getPast6HourRange().getMinimum().getMetric().getValue()));
        textCurrentTemperatureAverage.setText(""+Math.round(currentCondition.getTemperature().getMetric().getValue()));
        textWeather.setText(currentCondition.getWeatherText());
        textRealFeelTemperature.setText(""+Math.round(currentCondition.getRealFeelTemperature().getMetric().getValue()));
        textHumidity.setText(currentCondition.getRelativeHumidity() + "%");
        textWind.setText(Math.round(currentCondition.getWind().getSpeed().getMetric().getValue()) + " m/s");
        Glide.with(this).load(weatherIcon.iconPicker(currentCondition.getWeatherIcon())).into(iconWeather);
        Glide.with(this).load(R.drawable.ic_windy).into(iconWind);
        Glide.with(this).load(R.drawable.ic_umbrella).into(iconRelativeHumidity);

        viewModel.setBackground(currentCondition.getDayTime());
    }

}
