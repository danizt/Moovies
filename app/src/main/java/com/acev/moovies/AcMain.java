package com.acev.moovies;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class AcMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Seleccionar como contenido el Fragment del listado de notas
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new FrPopulares())
                .addToBackStack("FrPopulares")
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_gallery) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción", Snackbar.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
