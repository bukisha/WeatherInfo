package com.example.bookee.weatherinfo.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by WIN10 on 12/26/2017.
 */

public interface WeatherApi {

     String BASE_URL="http://samples.openweathermap.org/data/2.5/";


     @GET("weather?q=London&appid=b6907d289e10d714a6e88b30761fae22")
     Call<CityForecastInfo> getForecast();






}
