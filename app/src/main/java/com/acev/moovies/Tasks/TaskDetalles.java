package com.acev.moovies.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.acev.moovies.R;

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
            url = API_URL_GET_DETALLES.replace("<KEY>", API_KEY).replace("<ID>", id_detalle);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonS = response.body().string();
            Log.d("TaskDetalles", jsonS);

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
