package com.example.bookee.weatherinfo.search;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIUS_FAHRENHEIT_DIFFERENCE;

public class Model implements MvpContract.Model {
    private static final String ERROR_FETCH_DATA = "nisu primljeni novi podaci";
    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository=  repository;
    }

    @Override
    public void fetchData(String city, final MvpContract.FetchNewCityWeatherInfoCallback callback) {

        repository.fetchWeatherForCity(city, new WeatherRepository.ForecastCallback() {
            @Override
            public void onSuccess(CityForecastInfo info) {
               if(info!=null) {
                String temperature=String.valueOf(prepareTempForDisplay(info));
                String windSpeed=String.valueOf(info.getWind().getSpeed());
                String humidity=String.valueOf(info.getMain().getHumidity());

                TemperatureData newTemperatureData=new TemperatureData(info.getName(),temperature,windSpeed,humidity);
                callback.fetchNewWeather(newTemperatureData);
            } else {
                   callback.fetchNewWeather(null);
               }
            }
            @Override
            public void onError(Throwable t) {
                callback.error(ERROR_FETCH_DATA);
            }
        });
    }
    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIUS_FAHRENHEIT_DIFFERENCE);
    }
}
