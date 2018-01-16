package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;

//todo generalno, svaki interface je contract. Tako i ovaj MvpContract je implicitno contract sa MVP pattern. Kao takvog ga mozemo nazvati samo Mvp.
//todo Ovde imas qualifier public. Ovaj interface je lokalan samo za findcity package i ne sme biti vidljiv van ovog package. Hajmo ga onda ucitni privatnim za package.
//public interface MvpContract {
interface MvpContract {

   interface View  {
       void startNewActivity();//todo ovu metodu koristis samo jednom i to ne iz prezentera vec iz view. Tako da nema potrebe da ona u opste postoji u View. Takodje, da li je startovanje activity nesto sto ide zajedno sa "ispisi ovu labelu na dugmetu"?

       void reciveDataFromPresenter(String name,String temp,String windSpeed,String humidity);

       void errorHappened(String message);
   }

   interface Presenter  {

       void bindView(View v);

       void unbindView();

       void getData(String desiredCity);
   }

   interface Model {

       void fetchData(String city,FetchNewDataCallback callback);
   }

    //todo sta je Data? Da li su Data jabuke? Ili Kruske? Ili CityForecastInfo? Korektno ime bi bilo, recimo FetchCityForecastInfoCallback
   interface FetchNewDataCallback {

       void fetchNewData(String cityName,CityForecastInfo info);
       void error();

   }
}
