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

import com.sylversky.fontreplacer.FontReplacer;
import com.sylversky.fontreplacer.Replacer;


public class AcMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cambiar fuente
        Replacer replacer = FontReplacer.Build(getApplicationContext());
        replacer.setDefaultFont("fonts/ProductSans-Regular.ttf");
        replacer.setBoldFont("fonts/ProductSans-Bold.ttf");
        replacer.setBoldItalicFont("fonts/ProductSans-Bold-Italic.ttf");
        replacer.setItalicFont("fonts/ProductSans-Italic.ttf");
        replacer.applyFont();

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
        // Mostrar colores originales de los iconos del menú lateral
        navigationView.setItemIconTintList(null);


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
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
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
        // Click en los elementos del menú
        int id = item.getItemId();

        // TODO: Animaciones de transición
//        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
//                R.anim.enter_from_left, R.anim.exit_to_right)


        if (id == R.id.nav_populares) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción (populares)", Snackbar.LENGTH_LONG).show();
            cambiarFragment(new FrPopulares());
        }
        else if (id == R.id.nav_aleatoria) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción (aleatoria)", Snackbar.LENGTH_LONG).show();
            cambiarFragment(new FrAleatoria());
        }
        else if (id == R.id.nav_buscador) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción (buscador)", Snackbar.LENGTH_LONG).show();
            cambiarFragment( new FrBuscador());
        }
        else if (id == R.id.nav_compartir) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción (compartir)", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.nav_contactar) {
            new FrContacto().show(getSupportFragmentManager(), "Contacto");
        } else if (id == R.id.nav_valorar) {
            Snackbar.make(findViewById(android.R.id.content), "En construcción (valorar)", Snackbar.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cambiarFragment(android.support.v4.app.Fragment nuevoFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, nuevoFragment)
                .addToBackStack(null)
                .commit();
    }
}
