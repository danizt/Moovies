package com.acev.moovies;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import static com.acev.moovies.Config.SPLASH_SCREEN_DELAY;

/**
 * Created by Dani on 04/05/2017.
 */

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Forzar orientación vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Eliminar ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Asignar layout a la actividad
        setContentView(R.layout.activity_splash_screen);

        // Tarea que iniciará la siguiente actuvidad
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        SplashScreen.this, ActivityMain.class);
                startActivity(mainIntent);
                finish();
            }
        };

        // Ejecutar tarea anterior
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

        //TODO: Comprobar conexión a internet para iniciar el app.
    }

}
