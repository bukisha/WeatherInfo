package com.example.bookee.weatherinfo.splash;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.details.DetailsActivity;
import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    private MvpContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new Presenter();
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
        presenter.fetchInitialWeather();
    }

     public void startMainWithInitialData(TemperatureData initialTemperatureData, final long fetchDuration) {
        final Intent startApp=new Intent(this, DetailsActivity.class);

        startApp.putExtra("initialData",initialTemperatureData);

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
        AlertDialog.Builder builder=new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogTheme));
        builder.setTitle(R.string.errorTitle)
                .setMessage(message)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent reset=getIntent();
                        finish();
                        startActivity(reset);
                    }
                })
                .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
