package com.example.bookee.weatherinfo.findcity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.home.HomeWeatherDetailsActivity;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class FindCityActivity extends AppCompatActivity implements BaseView {

    private Button getForecast;
    private  EditText cityName;
    private FindCityPresenter presenter;

    private String name;
    private float temperature;
    private float windSpeed;
    private int humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_city);
        getForecast=findViewById(R.id.get_forecast_button);
        cityName=findViewById(R.id.desired_city_name);
        presenter=new FindCityPresenter();
        presenter.bindView(this);

    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.presenter= (FindCityPresenter) presenter;
    }

    @Override
    public void recieveDataFromPresenter(CityForecastInfo info) {
        name=info.getName();
        temperature= info.getMain().getTemp();
        windSpeed=info.getWind().getSpeed();
        humidity=info.getMain().getHumidity();
        //todo setujes ove vrednosti i startujes novi aktiviti?
        /*
        sta mislis da promenis signature metode "startNewActivity" pa da mozes da uradis:

        Intent intent=new Intent(this, HomeWeatherDetailsActivity.class);
        Bundle extraData=new Bundle();
        extraData.putString("name",name);
        extraData.putFloat("temp",temperature);
        extraData.putFloat("wind",windSpeed);
        extraData.putFloat("humid",humidity);
        intent.putExtras(extraData);

        startNewActivity(intent)
         */
        startNewActivity();
    }

    @Override
    public void errorHappened() {
        Toast.makeText(getApplicationContext(),"DOSLO JE DO GRESKE!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewActivity() {
        Intent i=new Intent(this, HomeWeatherDetailsActivity.class);
        Bundle extraData=new Bundle();
        extraData.putString("name",name);
        extraData.putFloat("temp",temperature);
        extraData.putFloat("wind",windSpeed);
        extraData.putFloat("humid",humidity);
        i.putExtras(extraData);


        startActivity(i);
    }
    //todo ista prica kao i za FAB u HomeWeatherDetailsActivity
    //sta se desava kad pritisnem dugme
    public void getCityForecastClicked(View view) {
        String desiredCity;


        desiredCity=cityName.getText().toString();
       // Log.i("Grad",desiredCity);
       presenter.getData(desiredCity);
    }
}


