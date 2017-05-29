package com.acev.moovies.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acev.moovies.R;

import org.w3c.dom.Text;

/**
 * Created by Usuario on 29/05/2017.
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
    }
}
