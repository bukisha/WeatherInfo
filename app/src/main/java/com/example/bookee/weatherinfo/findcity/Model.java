package com.example.bookee.weatherinfo.findcity;




import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;

//todo empty lines
public class Model implements MvpContract.Model {


    private RetrofitWeatherRepository repository;

    Model(RetrofitWeatherRepository repository) {
        this.repository=  repository;

    }

    @Override
   public void fetchData(final String city, final MvpContract.FetchNewDataCallback callback) {
        repository.fetchForecastForTheCity(city, new RetrofitWeatherRepository.ForecastCallback() {
            @Override
            public void onSuccess(CityForecastInfo cityForecastInfo) {
                callback.fetchNewData(city, cityForecastInfo);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.error();//samo ovde treba promeniti signature ove metode i proslediti throwable
            }
        });

        //todo kao sto vidis, na ovaj nacin smo potpuno odvojili retrofit logiku i nas model radi sa repository koji moze interno da koristi retrofit, neku trevu biblioteku, mozda bazu.... nas model ne zna za to i ne zanimaga. To se zove SpearationOfConcerns





//       Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());
//        Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());
//
//        call.enqueue(new Callback<CityForecastInfo>() {
//            @Override
//            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
//                callback.fetchNewData(city,response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable t) {
//                callback.error();
//
//            }
//        });

    }


}
