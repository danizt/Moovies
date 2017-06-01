package com.acev.moovies.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acev.moovies.R;
import com.acev.moovies.Tasks.TaskSendText;

import static com.acev.moovies.Config.Main.CONTACT_TEXT;

/**
 * Created by Daniel on 08/05/2017.
 */

public class FrContacto extends DialogFragment {

    Context context;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_contacto, container, false);
        this.getDialog().setCanceledOnTouchOutside(false);
        context = this.getContext();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btCancelar = (Button) getView().findViewById(R.id.btCancelar);
        Button btEnviar = (Button) getView().findViewById(R.id.btEnviar);
        final EditText etEmail = (EditText) getView().findViewById(R.id.input_email);
        final EditText etAsunto = (EditText) getView().findViewById(R.id.input_asunto);
        final EditText etMensaje = (EditText) getView().findViewById(R.id.input_mensaje);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Diálogo de confirmación para salir de la aplicación
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Añadir los botones
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Diálogo cancelado
                    }
                });

                // Propiedades del diálogo
                builder.setTitle(R.string.dialog_exit_dialog_message);

                // Mostrar diálogo
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String asunto = etAsunto.getText().toString();
                String mensaje = etMensaje.getText().toString();
                if (!validarTextoBlanco(asunto)) {
                    // Asunto en blanco
                    Toast.makeText(context,
                            getResources().getString(R.string.e_asunto_blanco),
                            Toast.LENGTH_SHORT).show();
                }
                else if (!validarTextoBlanco(email)) {
                    // Email en blanco
                    Toast.makeText(context,
                            getResources().getString(R.string.e_email_blanco),
                            Toast.LENGTH_SHORT).show();
                }
                else if (!validarEmail(email)) {
                    // Formato del email
                    Toast.makeText(context,
                            getResources().getString(R.string.e_email_formato),
                            Toast.LENGTH_SHORT).show();
                }
                else if (!validarTextoBlanco(mensaje)) {
                    // Mensaje en blanco
                    Toast.makeText(context,
                            getResources().getString(R.string.e_mensaje_blanco),
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    // Enviar mensaje

                    final String mensajeCompleto= CONTACT_TEXT.replace("<ASUNTO>", asunto).replace("<EMAIL>", email).replace("<MESSAGE>", mensaje);

                    // Diálogo de confirmación para enviar mensaje
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Añadir los botones
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            new TaskSendText(getActivity(), mensajeCompleto) {
                                @Override
                                protected void onPostExecute(Boolean sem) {
                                    super.onPostExecute(sem);
                                    if (pDialog.isShowing()) {
                                        pDialog.dismiss();
                                    }
                                    // Diálogo confirmado
                                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                                            getResources().getString(R.string.mensaje_enviado),
                                            Snackbar.LENGTH_SHORT).show();

                                    // Cerar dialog
                                    dismiss();
                                }
                            }.execute();
                        }
                    });
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Diálogo cancelado
                        }
                    });

                    // Propiedades del diálogo
                    builder.setTitle(R.string.dialog_enviar_message);
                    builder.setMessage(mensaje);

                    // Mostrar diálogo
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    // Validar si está en blanco
    private boolean validarTextoBlanco(String txt) {
        return txt.matches("^(\\s|\\S)*(\\S)+(\\s|\\S)*$");
        // Match everything but NOT BLANK string
        // Blank string is those containing empty characters only (tabs, spaces etc.).
    }

    // Validar email
    private boolean validarEmail(String txt) {
        return txt.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    }
}
