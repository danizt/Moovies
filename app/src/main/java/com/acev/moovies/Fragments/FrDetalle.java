package com.acev.moovies.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.acev.moovies.Activities.AcMain;
import com.acev.moovies.Objects.Detalle;
import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskDetalles;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.acev.moovies.Config.Main.API_POSTER_URL;
import static com.acev.moovies.Config.Main.URL_YT_EMBED;

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

        // Eliminar datos de la anterior película vista
//        det = null;

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
                Log.d("adult", det.getAdult().toString());
                Log.d("poster", det.getPoster());

                // Título de la toolbar
                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarDetalle);
                toolbar.setTitle(det.getTitle());

                // Imágen superior
                ImageView ivTitulo = (ImageView) getView().findViewById(R.id.imageTitulo);
                ivTitulo.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(getActivity()).load(API_POSTER_URL.replace("<IMG_PATH>", det.getBackdrop())).into(ivTitulo);


                // ***************
                // ***** cv0 *****
                // ***************


                // Titulo Original

                if (!det.getTagline().equals("")){
                    TextView tvTagline = (TextView) getView().findViewById(R.id.tvdtTagline);
                    tvTagline.setText("\""+det.getTagline()+"\"");
                }else{
                    CardView cv0 = (CardView) getView().findViewById(R.id.cv0);
                    cv0.setVisibility(View.GONE);
                }


                // ***************
                // ***** cv1 *****
                // ***************


                // Titulo Original
                TextView tvTituloO = (TextView) getView().findViewById(R.id.tvdtOriginalT);
                tvTituloO.setText(det.getOriginal_title());


                // Fecha de salida
                String formattedDate = null;
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(det.getRelease_date());
                    formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                TextView tvRelease = (TextView) getView().findViewById(R.id.tvdtRelease);
                tvRelease.setText(formattedDate);


                // Género
                String genre = "";
                TextView tvGenre = (TextView) getView().findViewById(R.id.tvdtGenre);
                List<String> genres = det.getGenres();
                for (int e = 0; e < genres.size(); e++) {
                    if (e > 0) {
                        genre = genre + ", " + genres.get(e).toLowerCase();
                    } else {
                        genre = genres.get(e);
                    }
                }
                tvGenre.setText(genre);


                // Productoras
                String prod = "";
                TextView tvProd = (TextView) getView().findViewById(R.id.tvdtProd);
                List<String> production_companies = det.getProduction_company();
                for (int e = 0; e < production_companies.size(); e++) {
                    if (e > 0) {
                        prod = prod + ", " + production_companies.get(e);
                    } else {
                        prod = production_companies.get(e);
                    }
                }
                tvProd.setText(prod);


                // ***************
                // ***** cv2 *****
                // ***************

                // Rate en estrellas
                Float nota = Float.parseFloat(det.getVote_average())/2;
                RatingBar rbRate = (RatingBar) getView().findViewById(R.id.rbRate) ;
                rbRate.setRating(nota);

                // Rate numérico
                TextView tvRate = (TextView) getView().findViewById(R.id.tvdtRate);
                tvRate.setText("("+det.getVote_average()+")");


                // ***************
                // ***** cv3 *****
                // ***************

                // Sinopsis
                TextView tvOverview = (TextView) getView().findViewById(R.id.tvdtOverview);
                tvOverview.setText(det.getOverview());


                // ***************
                // ***** cv4 *****
                // ***************

                // Trailer
                if (!det.getTrailer().equals("blank")){
                    WebView myWebView = (WebView) getView().findViewById(R.id.wbTrailer);
                    WebSettings webSettings = myWebView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    myWebView.loadUrl(URL_YT_EMBED + det.getTrailer());
                }else{
                    CardView cv4 = (CardView) getView().findViewById(R.id.cv4);
                    cv4.setVisibility(View.GONE);
                }
            }
        }.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            WebView myWebView = (WebView) getView().findViewById(R.id.wbTrailer);
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(myWebView, (Object[]) null);
        } catch(Exception e) {
        }
    }
}