package com.example.bookee.weatherinfo.details;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIUS_FAHRENHEIT_DIFFERENCE;

public class Model implements MvpContract.Model {
    private static final String BELGRADE = "Belgrade";
    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository = repository;
    }
    @Override
    public void fetchInitialData(final MvpContract.InitialCityForecastFetchCallback callback) {

        repository.fetchWeatherForCity(BELGRADE, new WeatherRepository.ForecastCallback() {
            @Override
            public void onSuccess(CityForecastInfo info) {
                String temperature=String.valueOf(prepareTempForDisplay(info));
                String windSpeed=String.valueOf(info.getWind().getSpeed());
                String humidity=String.valueOf(info.getMain().getHumidity());

                TemperatureData initialTemperatureData=new TemperatureData(info.getName(),temperature,windSpeed,humidity);
                callback.onSuccess(initialTemperatureData);
            }
            @Override
            public void onError(Throwable t) {
                callback.error(t);
            }
        });
    }
    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIUS_FAHRENHEIT_DIFFERENCE);
    }
}
