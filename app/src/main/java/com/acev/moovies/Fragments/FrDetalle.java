package com.acev.moovies.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskDetalles;
import com.acev.moovies.Tasks.TaskSendText;

/**
 * Created by Daniel on 20/05/2017.
 */

public class FrDetalle extends Fragment {

    public FrDetalle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fr_detalle, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener id de la película a mostrar
        String id_detalle = getArguments().getString("id");

        // Obtener datos de la película
        new TaskDetalles(getActivity(), id_detalle) {
            @Override
            protected void onPostExecute(Boolean sem) {
                super.onPostExecute(sem);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                // Diálogo confirmado
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        getResources().getString(R.string.mensaje_enviado),
                        Snackbar.LENGTH_SHORT).show();

            }
        }.execute();

        // Título de la toolbar
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(id_detalle);
    }
}