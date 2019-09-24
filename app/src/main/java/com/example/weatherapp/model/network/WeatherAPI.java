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
    Observable<List<Location>> getLocation(@Query("apiKey")String apiKey,
                                           @Query("queue")String queue);

    @GET("currentconditions/v1/295954/{locationKey}")
    Observable<CurrentCondition> getCurrentCondition(@Query("apiKey")String apiKey,
                                                     @Path ("locationKey")String locationKey,
                                                     @Query("details") @Nullable Boolean details);

    @GET("forecasts/v1/daily/5day/{locationKey}")
    Observable<Forecast> getForecast(@Query("apiKey")String apiKey,
                                     @Path ("locationKey")String locationKey,
                                     @Query("details") @Nullable Boolean details,
                                     @Query("metrics") @Nullable Boolean metrics);

    //TODO: add compression @Header("Accept-Encoding: gzip,deflate")
}
