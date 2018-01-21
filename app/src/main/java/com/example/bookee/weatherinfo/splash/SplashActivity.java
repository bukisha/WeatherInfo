package com.example.bookee.weatherinfo.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        //todo ne moras da razmotavas svaki put neki objekat. Da bi se ne sto prenelo kroz Bundle to nestom mora biti ili primitivni tip, ili bilo kja klasa koja implementira interface Parcelable.
        //Ovde imas laki tutorial: http://www.vogella.com/tutorials/AndroidParcelable/article.html
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
                    startActivity(startApp);
                    finish();
                    }
            }
        }).start();
    }
    @Override
    public void error(String message) {
        Toast.makeText(this,"Aplikacija nije startovana",Toast.LENGTH_LONG).show();//todo vreme je da pocnes da izvlacis stringove u local fajlove
    }

}
