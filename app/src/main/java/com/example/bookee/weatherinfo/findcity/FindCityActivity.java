package com.example.bookee.weatherinfo.findcity;
import com.example.bookee.weatherinfo.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FindCityActivity extends AppCompatActivity  {
   private  Button getForecast;
   private  EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_city);
        getForecast=findViewById(R.id.get_forecast_button);
        cityName=findViewById(R.id.desired_city_name);
    }





}


