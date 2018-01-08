package com.example.bookee.weatherinfo.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.findcity.FindCityActivity;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public class HomeWeatherDetailsActivity extends AppCompatActivity implements BaseView {
    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private FloatingActionButton floatingActionButton;
    private  static Bundle extras;
     private WeatherPresenter weatherPresenter;


    protected void onCreate(Bundle savedInstanceState) {


           super.onCreate(savedInstanceState);
           setContentView(R.layout.main_activity);
           city = findViewById(R.id.current_city);
           temperature = findViewById(R.id.current_temp);
           windSpeed = findViewById(R.id.wind_info);
           humidity = findViewById(R.id.humidity_info);
           floatingActionButton = findViewById(R.id.floatingActionButton);





           extras=getIntent().getExtras();
           if(extras!=null) {
                Log.i("DEBUG","pre vadjenja iz extras");
                displayNewValues();
                weatherPresenter=new WeatherPresenter();
                weatherPresenter.bindView(this);
           } else {
               weatherPresenter = new WeatherPresenter();
               weatherPresenter.bindView(this);
               weatherPresenter.getData();
           }

    }

         public void displayNewValues() {
        String s=extras.getString("name");
        city.setText(s);
       Float f=extras.getFloat("temp");
       temperature.setText(String.valueOf(Math.round(f)-273));

       Float f1=extras.getFloat("wind");
        windSpeed.setText(String.valueOf(Math.round(f1)));
         Float f2=extras.getFloat("humid");
         humidity.setText(String.valueOf(Math.round(f2)));
    }




    @Override
    public void errorHappened() {
        Toast.makeText(this,"Doslo je do greske!",Toast.LENGTH_LONG).show();
    }
    //this method is attached to FAB in xml
    public void findCityForecast(View v) {
       weatherPresenter.ActionSomethingIsClicked();


    }

    @Override
    public void startNewActivity() {
        Intent i=new Intent(this, FindCityActivity.class);
        startActivity(i);
    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.weatherPresenter= (WeatherPresenter) presenter;
    }

    @Override
    public void recieveDataFromPresenter(CityForecastInfo info) {
        int tempToDisplay;
        city.setText(info.getName());
        tempToDisplay= (int) (info.getMain().getTemp()-273.15 );
        temperature.setText(String.valueOf(tempToDisplay));
        windSpeed.setText(String.valueOf((int) info.getWind().getSpeed()));
        humidity.setText(String.valueOf(info.getMain().getHumidity()));

    }

}
