package com.acev.moovies;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.acev.moovies.Config.API_KEY;
import static com.acev.moovies.Config.API_URL_GET_POPULARES;
import static com.acev.moovies.Config.API_URL_SEND_TEXT;
import static com.acev.moovies.Config.MI_CID;
import static com.acev.moovies.Config.TAG_AVERAGE;
import static com.acev.moovies.Config.TAG_BACKDROP;
import static com.acev.moovies.Config.TAG_ID;
import static com.acev.moovies.Config.TAG_POSTER;
import static com.acev.moovies.Config.TAG_RELEASE_DATE;
import static com.acev.moovies.Config.TAG_TITLE;
import static com.acev.moovies.Config.TOKEN;
import static com.acev.moovies.Config.listaPopulares;

/**
 * Created by Daniel on 14/05/2017.
 */

public class TaskPopulares extends AsyncTask<Void, Integer, Boolean> {
    private String jsonS;
    private Context context;
    ProgressDialog pDialog;

    public TaskPopulares(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.pDialog.setMessage(context.getResources().getString(R.string.loading));
        this.pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        String url = API_URL_GET_POPULARES.replace("<KEY>", API_KEY);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            final JSONObject jsonObject = new JSONObject(response.body().string());

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            listaPopulares = obtenerListado(jsonArray);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private ArrayList<MovGen> obtenerListado(JSONArray JSONarray) {
        ArrayList<MovGen> array = new ArrayList<>();
        for (int i = 0; i < JSONarray.length(); i++) {
            try {
                String titulo = JSONarray.getJSONObject(i).getString(TAG_TITLE);
                String id = JSONarray.getJSONObject(i).getString(TAG_ID);
                String average = JSONarray.getJSONObject(i).getString(TAG_AVERAGE);
                String backdrop = JSONarray.getJSONObject(i).getString(TAG_BACKDROP);
                String poster = JSONarray.getJSONObject(i).getString(TAG_POSTER);
                String salida = JSONarray.getJSONObject(i).getString(TAG_RELEASE_DATE);

                MovGen pelicula = new MovGen();
                pelicula.setTitle(titulo);
                pelicula.setId(id);
                pelicula.setAverage(average);
                pelicula.setBackdrop(backdrop);
                pelicula.setPoster(poster);
                pelicula.setRelease_date(salida);
                array.add(pelicula);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}
