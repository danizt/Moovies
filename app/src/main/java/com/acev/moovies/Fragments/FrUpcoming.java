package com.acev.moovies.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acev.moovies.Adapters.AdaptadorLista;
import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskUpcoming;

import static com.acev.moovies.Config.Main.listaUpcoming;

/**
 * Created by Daniel on 28/05/2017.
 */

public class FrUpcoming extends Fragment {
    AdaptadorLista adaptador;
    ListView lvUpcoming;
    Snackbar sbError;

    public FrUpcoming() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ly_listado, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Título de la toolbar


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.upcoming));


        Log.e("FrUpcoming", "onViewCreated");
        // Obtener los datos del API
        if (listaUpcoming == null || listaUpcoming.isEmpty()) {
            obtenerDatos();
        } else {
            crearLista();
        }
    }

    // Obtener datos del API
    public void obtenerDatos() {
        sbError = Snackbar.make(getActivity().findViewById(android.R.id.content),
                getResources().getString(R.string.e_obtener_datos),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.reintentar, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        obtenerDatos();
                    }
                });
        if(sbError.isShown()){
            sbError.dismiss();
        }
        new TaskUpcoming(getActivity()) {
            @Override
            protected void onPostExecute(Boolean sem) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                if (sem) {
                    crearLista();
                } else {
                    sbError.show();
                }
            }
        }.execute();
    }

    // Generar la interfaz del listView
    private void crearLista() {
        Log.e("FrUpcoming", "crearLista");
        lvUpcoming = (ListView) getView().findViewById(R.id.lvListado);
        adaptador = new AdaptadorLista(getContext(), listaUpcoming);
        lvUpcoming.setAdapter(adaptador);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sbError.isShown()){
            sbError.dismiss();
        }
    }
}
