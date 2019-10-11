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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.model.network.DataReceiver;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.DailyForecast;
import com.example.weatherapp.model.pojo.forecast.Forecast;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class ForecastFragment extends Fragment {

    private TextView textForecast1;
    private TextView textForecast2;
    private TextView textForecast3;

    private TextView textForecastTemperature1;
    private TextView textForecastTemperature2;
    private TextView textForecastTemperature3;

    private ImageView iconRelativeHumidity1;
    private ImageView iconRelativeHumidity2;
    private ImageView iconRelativeHumidity3;

    private WeatherViewModel viewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        textForecast1 = view.findViewById(R.id.textForecast1);
        textForecast2 = view.findViewById(R.id.textForecast2);
        textForecast3 = view.findViewById(R.id.textForecast3);

        textForecastTemperature1 = view.findViewById(R.id.textForecastTemperature1);
        textForecastTemperature2 = view.findViewById(R.id.textForecastTemperature2);
        textForecastTemperature3 = view.findViewById(R.id.textForecastTemperature3);

        iconRelativeHumidity1 = view.findViewById(R.id.iconRelativeHumidity1);
        iconRelativeHumidity2 = view.findViewById(R.id.iconRelativeHumidity2);
        iconRelativeHumidity3 = view.findViewById(R.id.iconRelativeHumidity3);

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*        DataReceiver dataReceiver = DataReceiver.getInstance();
        dataReceiver.getForecastObserver("295954",apiKey).
                subscribeWith(new DisposableObserver<Forecast>() {
                    @Override
                    public void onNext(Forecast forecast) {
                        updateCurrentCondition(forecast);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag", "Forecast:" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
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

        textForecastTemperature1.setText(""+Math.round(forecast.getDailyForecasts().get(1).getTemperature().getMaximum().getValue()));
        textForecastTemperature2.setText(""+Math.round(forecast.getDailyForecasts().get(2).getTemperature().getMaximum().getValue()));
        textForecastTemperature3.setText(""+Math.round(forecast.getDailyForecasts().get(3).getTemperature().getMaximum().getValue()));
    }
}
