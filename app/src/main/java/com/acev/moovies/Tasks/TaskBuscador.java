package com.acev.moovies.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.acev.moovies.Objects.Movies;
import com.acev.moovies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.acev.moovies.Config.Main.API_KEY;
import static com.acev.moovies.Config.Main.API_URL_GET_SEARCH;
import static com.acev.moovies.Config.Main.TAG_AVERAGE;
import static com.acev.moovies.Config.Main.TAG_BACKDROP;
import static com.acev.moovies.Config.Main.TAG_ID;
import static com.acev.moovies.Config.Main.TAG_POSTER;
import static com.acev.moovies.Config.Main.TAG_RELEASE_DATE;
import static com.acev.moovies.Config.Main.TAG_TITLE;
import static com.acev.moovies.Config.Main.listaBuscador;

/**
 * Created by Daniel on 29/05/2017.
 */

public class TaskBuscador extends AsyncTask<Void, Integer, Boolean> {
    private Context context;
    public ProgressDialog pDialog;
    private String query;

    public TaskBuscador(Context context, String query) {
        this.query = query;
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

        String url = API_URL_GET_SEARCH.replace("<KEY>", API_KEY).replace("<LANG>", Locale.getDefault().getLanguage()).replace("<QUERY>", query);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            final JSONObject jsonObject = new JSONObject(response.body().string());

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            listaBuscador = obtenerListado(jsonArray);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private ArrayList<Movies> obtenerListado(JSONArray JSONarray) {
        ArrayList<Movies> array = new ArrayList<>();
        for (int i = 0; i < JSONarray.length(); i++) {
            try {
                String titulo = JSONarray.getJSONObject(i).getString(TAG_TITLE);
                String id = JSONarray.getJSONObject(i).getString(TAG_ID);
                String average = JSONarray.getJSONObject(i).getString(TAG_AVERAGE);
                String backdrop = JSONarray.getJSONObject(i).getString(TAG_BACKDROP);
                String poster = JSONarray.getJSONObject(i).getString(TAG_POSTER);
                String salida = JSONarray.getJSONObject(i).getString(TAG_RELEASE_DATE);

                Movies pelicula = new Movies();
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
