package com.example.weatherapp.model.network;

import androidx.annotation.Nullable;

import com.example.weatherapp.model.pojo.citysearch.Location;
import com.example.weatherapp.model.pojo.currentcondition.CurrentCondition;
import com.example.weatherapp.model.pojo.forecast.Forecast;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("locations/v1/cities/autocomplete")
    Observable<List<Location>> getLocation(@Query("apikey")String apiKey,
                                           @Query("q")String queue);

    @GET("currentconditions/v1/{locationKey}")
    Observable<List<CurrentCondition>> getCurrentCondition(@Path ("locationKey")String apiKey ,
                                                     @Query("apikey")String locationKey,
                                                     @Query("details") @Nullable Boolean details);

    @GET("forecasts/v1/daily/5day/{locationKey}")
    Observable<Forecast> getForecast(@Path ("locationKey")String apiKey,
                                     @Query("apikey")String locationKey,
                                     @Query("details") @Nullable Boolean details,
                                     @Query("metric") @Nullable Boolean metric);

    //TODO: add compression @Header("Accept-Encoding: gzip,deflate")
}
