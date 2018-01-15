package com.example.bookee.weatherinfo.findcity;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model attachedDataInstance;
    private MvpContract.View attachedView;

    public Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);

    }




    @Override
    public void bindView(BaseView view) {
       this.attachedView= (MvpContract.View) view;
    }



   @Override
   public void getData(String desiredCity) {
        attachedDataInstance.fetchData(desiredCity, new MvpContract.FetchNewDataCallback() {


            @Override
            public void fetchNewData(String cityName, CityForecastInfo info) {
                attachedView.recieveDataFromPresenter(info);
            }

            @Override
            public void error(String message) {

                attachedView.errorHappened("GRESKA");
            }
        });

    }



    @Override
    public void unbindView() {
        attachedView=null;
    }



}
