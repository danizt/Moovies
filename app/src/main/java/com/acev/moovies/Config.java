package com.acev.moovies;

/**
 * Created by Dani on 04/05/2017.
 */

public class Config {

    // Tiempo de espera para iniciar la actividad principal
    public static final long SPLASH_SCREEN_DELAY = 2000;

    // Key del API themoviedb
    public static final String API_KEY = SecretConfig.API_KEY;

    // Llamada del API de Telegram para mandar mensaje
    public static final String API_URL_SEND_TEXT = "https://api.telegram.org/bot<TOKEN>/sendMessage?chat_id=<CID>&text=<MESSAGE>";

    // Token del bot de Telegram
    public static final String TOKEN = SecretConfig.BOT_TOKEN;

    // CID del usuario de Telegram al que le llegarán los mensajes
    public static final String MI_CID = SecretConfig.MI_CID;

    // Formato del mensaje
    public static final String CONTACT_TEXT= "Asunto: <ASUNTO>\nEmail: <EMAIL>\n\nMensaje:\n<MESSAGE>";

}
