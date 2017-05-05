package com.acev.moovies;

/**
 * Created by Dani on 04/05/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityMain extends AppCompatActivity {

    private static Context context;

    public static Context getAppContext() {
        return ActivityMain.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asignar layout
        setContentView(R.layout.activity_main);

        // Set contexto de la aplicación
        ActivityMain.context = getApplicationContext();

        // Forzar orientación vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Seleccionar como contenido el Fragment del listado de notas
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new FragmentPopulares())
                .addToBackStack("FrPopulares")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            // Diálogo de confirmación para salir de la aplicación
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Añadir los botones
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Diálogo aceptado
                    finish();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Diálogo cancelado
                }
            });

            // Propiedades del diálogo
            builder.setMessage(R.string.dialog_exit_message);

            // Mostrar diálogo
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
