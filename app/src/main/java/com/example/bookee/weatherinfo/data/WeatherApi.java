package com.example.bookee.weatherinfo.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WeatherApi {

     String BASE_URL_PRODUCTION="http://samples.openweathermap.org/data/2.5/";
     String BASE_URL_LIVE="http://api.openweathermap.org/data/2.5/";

     String API_KEY ="e201d03b0ded25f6ba5dc1fa35f016ea";
     String API_KEY_2="b10b5cef962cf5f44e3a4880429d9c45";


     @GET("weather")
     Call<CityForecastInfo> getForecast(@Query("q") String city,@Query("appid") String apiKey);






}
