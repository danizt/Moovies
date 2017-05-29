package com.acev.moovies.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.acev.moovies.Adapters.AdaptadorLista;
import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskBuscador;

import static com.acev.moovies.Config.Main.listaBuscador;

/**
 * Created by Daniel on 05/05/2017.
 */

public class FrBuscador extends Fragment {
    AdaptadorLista adaptador;
    Snackbar sbError;
    ListView lvBuscador;

    public FrBuscador() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fr_buscador, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Título de la toolbar
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.buscador));

        // Search View
        final SearchView svBuscador = (SearchView) getView().findViewById(R.id.svBuscador);

        // Listener SearchView
        svBuscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!(sbError == null)){
                    if(sbError.isShown()){
                        sbError.dismiss();
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("FrBuscador", "<QUERY> = "+query);
                obtenerDatos(query);
                return true;
            }
        });
    }

    // Obtener datos del API
    public void obtenerDatos(final String query) {
        sbError = Snackbar.make(getActivity().findViewById(android.R.id.content),
                getResources().getString(R.string.e_obtener_datos),
                Snackbar.LENGTH_INDEFINITE);
        if(sbError.isShown()){
            sbError.dismiss();
        }
        new TaskBuscador(getActivity(), query) {
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

    private void crearLista() {
        if (listaBuscador.isEmpty()){
            Toast.makeText(getActivity(), getContext().getResources().getString(R.string.e_buscar), Toast.LENGTH_SHORT).show();
        }
        lvBuscador = (ListView) getView().findViewById(R.id.lvBuscador);
        adaptador = new AdaptadorLista(getContext(), listaBuscador);
        lvBuscador.setAdapter(adaptador);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!(sbError == null)){
            if(sbError.isShown()){
                sbError.dismiss();
            }
        }
    }
}
