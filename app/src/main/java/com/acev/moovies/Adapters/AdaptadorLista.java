package com.acev.moovies.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acev.moovies.Fragments.FrDetalle;
import com.acev.moovies.Objects.Movies;
import com.acev.moovies.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.acev.moovies.Config.Main.API_POSTER_URL;

/**
 * Created by Daniel on 14/05/2017.
 */

public class AdaptadorLista extends BaseAdapter {
    private Context context;
    private List<Movies> items;

    public AdaptadorLista(Context context, ArrayList<Movies> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_lista, parent, false);
        }

        // Obtener datos a insertar
        final Movies item = this.items.get(position);
        final String id = item.getId();
        final String average = item.getAverage();
        final String backdrop = item.getBackdrop();
        final String poster = item.getPoster();
        final String release_date = item.getRelease_date();
        final String title = item.getTitle();

        ImageView ivPoster = (ImageView) rowView.findViewById(R.id.ivPoster);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvAverage = (TextView) rowView.findViewById(R.id.tvAverage);
        TextView tvRelease = (TextView) rowView.findViewById(R.id.tvRelease);

        String formattedDate = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(release_date);
            formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTitle.setText(title);
        tvRelease.setText(formattedDate);
        tvAverage.setText(String.format("%1$,.1f", Double.parseDouble(average)));
        Picasso.with(context).load(API_POSTER_URL.replace("<IMG_PATH>", poster)).into(ivPoster);


        // Abrir vista detalle de cada película
        RelativeLayout rlElemento = (RelativeLayout) rowView.findViewById(R.id.rlElemento);
        rlElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Agregar animación entre fragments
                Bundle arguments = new Bundle();
                arguments.putString("id", id);
                FrDetalle fr = new FrDetalle();
                fr.setArguments(arguments);
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, fr)
                        .addToBackStack("fr_detalle")
                        .commit();
            }
        });

        return rowView;
    }
}
