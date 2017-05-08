package com.acev.moovies;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.acev.moovies.Config.API_URL_SEND_TEXT;
import static com.acev.moovies.Config.MI_CID;
import static com.acev.moovies.Config.TOKEN;

/**
 * Created by Daniel on 08/05/2017.
 */

public class TaskSendText extends AsyncTask<Void, Integer, Boolean> {

    private String mensaje;
    private Context context;


    public TaskSendText(Context context, String mensaje) {
        this.context = context;
        this.mensaje = mensaje;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // Formar URL para enviar mensaje
        String url = null;
        try {
            url = API_URL_SEND_TEXT.replace("<TOKEN>", TOKEN).replace("<CID>", MI_CID).replace("<MESSAGE>", URLEncoder.encode(mensaje, "UTF-8"));

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonS = response.body().string();
            Log.d("TaskSendText", jsonS);

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

