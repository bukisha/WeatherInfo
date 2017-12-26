package com.example.bookee.weatherinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.model.CityForecastInfo;
import com.example.bookee.weatherinfo.model.Main;
import com.example.bookee.weatherinfo.model.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tw = findViewById(R.id.forecast_details);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi myApi = retrofit.create(WeatherApi.class);
        Call<CityForecastInfo> call = myApi.getForecast();

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {

                showData(response.body());
            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showData(CityForecastInfo body) {
        Log.i("debug", "showData metod se izvrsava!!!!");
        CityForecastInfo infoFromServer = body;

        tw.setText("U gradu" + infoFromServer.getName() + " je " + infoFromServer.getMain().getTemp() + " stepeni farenhajta!");

    }
}
