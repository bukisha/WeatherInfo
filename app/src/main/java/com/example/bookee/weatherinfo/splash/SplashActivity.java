package com.example.bookee.weatherinfo.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.details.DetailsActivity;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpContract.Presenter presenter=new Presenter();
        presenter.bindView(this);
        presenter.fetchInitialWeather();
    }

    public void startMainWithInitialData(TemperatureData initialTemperatureData, final long fetchDuration) {
        final Intent startApp=new Intent(this, DetailsActivity.class);


        Bundle initialData=new Bundle();
        initialData.putString("name",initialTemperatureData.getName());
        initialData.putString("temp",initialTemperatureData.getTemp());
        initialData.putString("wind",initialTemperatureData.getWindSpeed());
        initialData.putString("humid",initialTemperatureData.getHumidity());
        startApp.putExtras(initialData);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(fetchDuration<3000) {
                    try {
                        sleep(3000-fetchDuration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(startApp);
                    finish();
                } else {
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
    @Override
    public void error(String message) {
        Toast.makeText(this,"Aplikacija nije startovana",Toast.LENGTH_LONG).show();
    }

}
