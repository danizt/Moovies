package com.acev.moovies.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acev.moovies.Activities.AcMain;
import com.acev.moovies.Objects.Detalle;
import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskDetalles;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.acev.moovies.Config.Main.API_POSTER_URL;

/**
 * Created by Daniel on 20/05/2017.
 */

public class FrDetalle extends Fragment {

    public static Detalle det = new Detalle();

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

                // Datos recibidos
                Log.d("backdrop", det.getBackdrop());
                Log.d("adult", det.getAdult().toString());
                Log.d("overview", det.getOverview());
                Log.d("poster", det.getPoster());
                Log.d("release_date", det.getRelease_date());
                Log.d("title", det.getTitle());
                Log.d("vote_average", det.getVote_average());

                List<String> genres = det.getGenres();
                for(int e = 0; e < genres.size(); e++){
                    Log.d("genre " + (e+1), genres.get(e));
                }

                List<String> production_companies = det.getProduction_company();
                for(int f = 0; f < production_companies.size(); f++){
                    Log.d("production_companies " + (f+1), production_companies.get(f));
                }

                Log.d("trailer", det.getTrailer());

                // Título de la toolbar
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarDetalle);
                toolbar.setTitle(det.getTitle());

                // Imágen superior
                ImageView ivTitulo = (ImageView) getView().findViewById(R.id.imageTitulo);
                ivTitulo.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(getActivity()).load(API_POSTER_URL.replace("<IMG_PATH>", det.getBackdrop())).into(ivTitulo);

            }
        }.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}