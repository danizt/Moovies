package com.acev.moovies.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.acev.moovies.Fragments.FrDetalle;
import com.acev.moovies.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.acev.moovies.Config.Main.API_KEY;
import static com.acev.moovies.Config.Main.API_URL_GET_DETALLES;
import static com.acev.moovies.Config.Main.TOKEN;

/**
 * Created by Daniel on 14/05/2017.
 */

public class TaskDetalles extends AsyncTask<Void, Integer, Boolean> {
    private Context context;
    public ProgressDialog pDialog;
    private String id_detalle;

    public TaskDetalles(Context context, String id_detalle) {
        this.context = context;
        this.id_detalle = id_detalle;
        pDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.pDialog.setMessage(context.getResources().getString(R.string.loading));
        this.pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // Formar URL para enviar mensaje
        String url = null;
        try {
            url = API_URL_GET_DETALLES.replace("<KEY>", API_KEY).replace("<ID>", id_detalle).replace("<LANG>", Locale.getDefault().getLanguage());

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonS = response.body().string();
            Log.d("TaskDetalles", jsonS);


            // Obtener datos principales
            JSONObject mainData = new JSONObject(jsonS);

            // Agregar datos al objeto detalle
            FrDetalle.det.setBackdrop(mainData.getString("backdrop_path"));
            FrDetalle.det.setAdult(mainData.getBoolean("adult"));
            FrDetalle.det.setOverview(mainData.getString("overview"));
            FrDetalle.det.setPoster(mainData.getString("poster_path"));
            FrDetalle.det.setRelease_date(mainData.getString("release_date"));
            FrDetalle.det.setTitle(mainData.getString("title"));
            FrDetalle.det.setVote_average(mainData.getString("vote_average"));
            FrDetalle.det.setOriginal_title(mainData.getString("original_title"));
            FrDetalle.det.setTagline(mainData.getString("tagline"));

            // Obtener géneros
            JSONArray genreArray = mainData.getJSONArray("genres");
            List<String> genres = new ArrayList<>();

            // Agregar géneros
            for (int i = 0; i < genreArray.length(); i++) {
                genres.add(genreArray.getJSONObject(i).getString("name"));
            }
            FrDetalle.det.setGenres(genres);

            // Obtener production_company
            JSONArray productionArray = mainData.getJSONArray("production_companies");
            List<String> production_companies = new ArrayList<>();

            // Agregar production_company
            for (int i = 0; i < productionArray.length(); i++) {
                production_companies.add(productionArray.getJSONObject(i).getString("name"));
            }
            FrDetalle.det.setProduction_company(production_companies);


            // Obtener trailer
            JSONObject trailerObject = mainData.getJSONObject("videos");
            JSONArray trailerArray = trailerObject.getJSONArray("results");
            String key = "blank";
            if(trailerArray.getJSONObject(0).has("key")){
                key = trailerArray.getJSONObject(0).getString("key");
            }
            FrDetalle.det.setTrailer(key);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean sem) {
        super.onPostExecute(sem);
    }
}
