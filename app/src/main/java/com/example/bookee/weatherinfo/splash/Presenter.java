package com.example.bookee.weatherinfo.splash;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    Presenter() {
        WeatherRepository repository = new RetrofitWeatherRepository();
        model = new Model(repository);
    }

    @Override
    public void fetchInitialWeather(final SplashActivity splashActivity) {
        model.fetchInitialData(new MvpContract.InitialCityForecastFetchCallback() {
            @Override
            public void onSuccess(TemperatureData initTemp, long fetchDuration) {
                if (view == null) return;
                {
                    ArrayList<TemperatureData> allCitiesTemperatureList= new ArrayList<>();
                    allCitiesTemperatureList.add(initTemp);

                    SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(splashActivity.getApplicationContext());
                    SharedPreferences.Editor sharedPrefEditor=sharedPreferences.edit();
                    Gson gson=new Gson();
                    String initialGlobaTemperaturelList=gson.toJson(allCitiesTemperatureList);
                    sharedPrefEditor.putString(String.valueOf(R.string.globalCityListName),initialGlobaTemperaturelList);
                    sharedPrefEditor.commit();
                    view.startMainWithInitialData(initTemp, fetchDuration);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                view.error("Initialization Error");
            }
        });
    }
    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }
}



