package com.example.bookee.weatherinfo.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WeatherApi {


     @GET("weather")
     Call<CityForecastInfo> getForecast(@Query("q") String city,@Query("appid") String apiKey);



}
