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
import com.example.weatherapp.model.pojo.forecast.DailyForecast;
import com.example.weatherapp.model.pojo.forecast.Forecast;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;

public class ForecastFragment extends Fragment {

    private TextView textForecast1;
    private TextView textForecast2;
    private TextView textForecast3;

    private TextView textForecastTemperatureDay1;
    private TextView textForecastTemperatureDay2;
    private TextView textForecastTemperatureDay3;

    private ImageView iconWeatherDay1;
    private ImageView iconWeatherDay2;
    private ImageView iconWeatherDay3;

    private ImageView iconWeatherNight1;
    private ImageView iconWeatherNight2;
    private ImageView iconWeatherNight3;


    private WeatherIcon weatherIcon = new WeatherIcon();

    private WeatherViewModel viewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        textForecast1 = view.findViewById(R.id.textForecast1);
        textForecast2 = view.findViewById(R.id.textForecast2);
        textForecast3 = view.findViewById(R.id.textForecast3);

        textForecastTemperatureDay1 = view.findViewById(R.id.textForecastTemperature1);
        textForecastTemperatureDay2 = view.findViewById(R.id.textForecastTemperature2);
        textForecastTemperatureDay3 = view.findViewById(R.id.textForecastTemperature3);

        iconWeatherDay1 = view.findViewById(R.id.iconWeatherDay1);
        iconWeatherDay2 = view.findViewById(R.id.iconWeatherDay2);
        iconWeatherDay3 = view.findViewById(R.id.iconWeatherDay3);

        iconWeatherNight1 = view.findViewById(R.id.iconWeatherNight1);
        iconWeatherNight2 = view.findViewById(R.id.iconWeatherNight2);
        iconWeatherNight3 = view.findViewById(R.id.iconWeatherNight3);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        viewModel.getForecast().observe(this, new Observer<Forecast>() {
            @Override
            public void onChanged(Forecast forecast) {
                updateForecast(forecast);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void updateForecast(Forecast forecast){
        Log.d("tag", "Forecast:" + forecast.getDailyForecasts().size());
        for(DailyForecast dailyForecast: forecast.getDailyForecasts()) {
            Log.d("tag", "Forecast:" + dailyForecast.getDay().getIconPhrase());
        }
        TimeZone timezone = TimeZone.getDefault();
        int milis = timezone.getOffset(Calendar.ZONE_OFFSET);
        Log.d("tag", "ZONE_OFFSET:" + milis);
        textForecast1.setText(new SimpleDateFormat("EEEE").format(new Date(forecast.getDailyForecasts().get(1).getEpochDate()*1000 - milis)));
        textForecast2.setText(new SimpleDateFormat("EEEE").format(new Date(forecast.getDailyForecasts().get(2).getEpochDate()*1000 - milis)));
        textForecast3.setText(new SimpleDateFormat("EEEE").format(new Date(forecast.getDailyForecasts().get(3).getEpochDate()*1000 - milis)));

        textForecastTemperatureDay1.setText(""+Math.round(forecast.getDailyForecasts().get(1).getTemperature().getMaximum().getValue()));
        textForecastTemperatureDay2.setText(""+Math.round(forecast.getDailyForecasts().get(2).getTemperature().getMaximum().getValue()));
        textForecastTemperatureDay3.setText(""+Math.round(forecast.getDailyForecasts().get(3).getTemperature().getMaximum().getValue()));

        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(1).getDay().getIcon())).into(iconWeatherDay1);
        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(2).getDay().getIcon())).into(iconWeatherDay2);
        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(3).getDay().getIcon())).into(iconWeatherDay3);

        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(1).getNight().getIcon())).into(iconWeatherNight1);
        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(2).getNight().getIcon())).into(iconWeatherNight2);
        Glide.with(this).load(weatherIcon.iconPicker(forecast.getDailyForecasts().get(3).getNight().getIcon())).into(iconWeatherNight3);
    }
}
