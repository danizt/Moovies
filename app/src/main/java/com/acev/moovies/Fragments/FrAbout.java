package com.acev.moovies.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acev.moovies.R;

import static com.acev.moovies.Config.Main.GITHUB_USER;
import static com.acev.moovies.Config.Main.LINKEDIN_USER;
import static com.acev.moovies.Config.Main.URL_GITHUB;
import static com.acev.moovies.Config.Main.URL_LINKEDIN;
import static com.acev.moovies.Config.Main.URL_PLAYSTORE;
import static com.acev.moovies.Config.Main.URL_TMDB;

/**
 * Created by Daniel on 29/05/2017.
 */

public class FrAbout extends DialogFragment {
    Context context;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_about, container, false);
        context = this.getContext();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvDeveloper = (TextView) view.findViewById(R.id.tvDeveloper);
        tvDeveloper.setText(getString(R.string.developed_by) + " " + getString(R.string.developer_name));


        // Enlace a mi perfil de GitHub
        ImageView ivGitHub = (ImageView) view.findViewById(R.id.ivGitHub);
        ivGitHub.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL_GITHUB.replace("<USER>", GITHUB_USER)));
                startActivity(intent);
            }
        });

        // Enlace a mi perfil de Linkedin
        ImageView ivLinkedin = (ImageView) view.findViewById(R.id.ivLinkedin);
        ivLinkedin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL_LINKEDIN.replace("<USER>", LINKEDIN_USER)));
                startActivity(intent);
            }
        });

        // Enlace a TMDb
        ImageView ivPowered = (ImageView) view.findViewById(R.id.ivPowered);
        ivPowered.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL_TMDB));
                startActivity(intent);
            }
        });

        // Enlace a PlayStore
        ImageView ivLogo = (ImageView) view.findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL_PLAYSTORE.replace("<PAQUETE>", getActivity().getApplicationContext().getPackageName())));
                startActivity(intent);
            }
        });
    }
}
