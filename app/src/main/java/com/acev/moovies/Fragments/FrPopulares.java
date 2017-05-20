package com.acev.moovies.Fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.acev.moovies.Adapters.AdaptadorLista;
import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskPopulares;

import static com.acev.moovies.Config.Main.listaPopulares;

/**
 * Created by Daniel on 05/05/2017.
 */

public class FrPopulares extends Fragment {
    AdaptadorLista adaptador;
    ListView lvPopulares;

    public FrPopulares() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fr_populares, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Título de la toolbar
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.populares));

        // Obtener los datos del API
        if (listaPopulares == null || listaPopulares.isEmpty()){
            obtenerDatos();
        } else{
            crearLista();
        }
    }

    // Obtener datos del API
    public void obtenerDatos(){
        new TaskPopulares(getActivity()) {
            @Override
            protected void onPostExecute(Boolean sem) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                if (sem) {
                    crearLista();
                } else {
                    Snackbar.make(getView().findViewById(android.R.id.content), getResources().getString(R.string.e_obtener_datos), Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        }.execute();
    }
    // Generar la interfaz del listView
    private void crearLista(){
        lvPopulares = (ListView) getView().findViewById(R.id.listaPopulares);
        adaptador = new AdaptadorLista(getContext(), listaPopulares);
        lvPopulares.setAdapter(adaptador);
    }
}