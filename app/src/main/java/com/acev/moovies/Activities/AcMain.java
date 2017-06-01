package com.acev.moovies.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.acev.moovies.Fragments.FrAbout;
import com.acev.moovies.Fragments.FrBuscador;
import com.acev.moovies.Fragments.FrContacto;
import com.acev.moovies.Fragments.FrDonar;
import com.acev.moovies.Fragments.FrInfantiles;
import com.acev.moovies.Fragments.FrPopulares;
import com.acev.moovies.Fragments.FrUpcoming;
import com.acev.moovies.R;
import com.sylversky.fontreplacer.FontReplacer;
import com.sylversky.fontreplacer.Replacer;

import static com.acev.moovies.Config.Main.URL_PLAYSTORE;

/**
 * Created by Daniel on 04/05/2017.
 */

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

        // Seleccionar como contenido "Populares"
        navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new FrPopulares())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                Fragment myFragment = getSupportFragmentManager().findFragmentByTag("fr_detalle");
                if (myFragment != null && myFragment.isVisible()) {
                    getSupportFragmentManager().beginTransaction().remove(myFragment);

                }
                getSupportFragmentManager().popBackStack();
            } else {
                // Diálogo de confirmación para salir de la aplicación
                new AlertDialog.Builder(this)
                    // Añadir los botones
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Diálogo aceptado
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    // Propiedades del diálogo
                    .setMessage(R.string.dialog_exit_message)
                    // Mostrar diálogo
                    .show();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Click en los elementos del menú
        int id = item.getItemId();

        if (id == R.id.nav_populares) {
            cambiarFragment(new FrPopulares());
        }
        else if (id == R.id.nav_infantiles) {
            cambiarFragment(new FrInfantiles());
        }
        else if (id == R.id.nav_upcoming) {
            cambiarFragment(new FrUpcoming());
        }
        else if (id == R.id.nav_buscador) {
            cambiarFragment( new FrBuscador());
        }
        else if (id == R.id.nav_donar) {
            new FrDonar().show(getSupportFragmentManager(), "Donar");
        }
        else if (id == R.id.nav_compartir) {
            compartir();
        }
        else if (id == R.id.nav_contactar) {
            new FrContacto().show(getSupportFragmentManager(), "Contacto");
        }
        else if (id == R.id.nav_valorar) {
            valorar();
        }
        else if (id == R.id.nav_about) {
            new FrAbout().show(getSupportFragmentManager(), "About");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cambiarFragment(android.support.v4.app.Fragment nuevoFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.content, nuevoFragment)
                .commit();
    }

    public void compartir(){
        String shareBody = getResources().getString(R.string.share_message) + "\n" + URL_PLAYSTORE.replace("<PAQUETE>", getApplicationContext().getPackageName());
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
    }

    public void valorar() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(URL_PLAYSTORE.replace("<PAQUETE>", getApplicationContext().getPackageName())));
        startActivity(intent);
    }
}
