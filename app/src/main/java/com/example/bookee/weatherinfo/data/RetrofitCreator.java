package com.example.bookee.weatherinfo.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 class RetrofitCreator {
    //BASE_URL_PRODUCTION might ne usefull further in development
    // private static final String BASE_URL_PRODUCTION = "http://samples.openweathermap.org/data/2.5/";
    private static final String BASE_URL_LIVE = "http://api.openweathermap.org/data/2.5/";

    private static final String API_KEY = "e201d03b0ded25f6ba5dc1fa35f016ea";

    static Retrofit createRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
