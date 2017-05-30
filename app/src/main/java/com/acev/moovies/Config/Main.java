package com.acev.moovies.Config;

import com.acev.moovies.Objects.Movies;

import java.util.ArrayList;

/**
 * Created by Dani on 04/05/2017.
 */

public class Main {

    // Tiempo de espera para iniciar la actividad principal
    public static final long SPLASH_SCREEN_DELAY = 500;

    // Key del API themoviedb
    public static final String API_KEY = SecretConfig.API_KEY;

    // Llamada del API de Telegram para mandar mensaje
    public static final String API_URL_SEND_TEXT = "https://api.telegram.org/bot<TOKEN>/sendMessage?chat_id=<CID>&text=<MESSAGE>";

    // Token del bot de Telegram
    public static final String TOKEN = SecretConfig.BOT_TOKEN;

    // CID del usuario de Telegram al que le llegarán los mensajes
    public static final String MI_CID = SecretConfig.MI_CID;

    // Formato del mensaje
    public static final String CONTACT_TEXT = "Asunto: <ASUNTO>\nEmail: <EMAIL>\n\nMensaje:\n<MESSAGE>";

    // Llamada al API de themoviedb para obtener peliculas populares
    public static final String API_URL_GET_POPULARES = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=<KEY>&language=<LANG>";

    // Llamada al API de themoviedb para obtener próximos lanzamientos
    public static final String API_URL_GET_UPCOMING = "http://api.themoviedb.org/3/discover/movie?api_key=<KEY>&language=<LANG>&primary_release_date.gte=2017-5-29&sort_by=popularity.desc";

    // Llamada al API de themoviedb para obtener detalles de una palícula
    public static final String API_URL_GET_DETALLES = "http://api.themoviedb.org/3/movie/<ID>?append_to_response=videos&api_key=<KEY>&language=<LANG>";

    // Llamada al API de themoviedb para obtener datos de búsquedas
    public static final String API_URL_GET_SEARCH = "http://api.themoviedb.org/3/search/movie?query=<QUERY>&api_key=<KEY>&language=<LANG>&sort_by=popularity.desc";

    // Ruta para obtener las imágenes del API
    public static final String API_POSTER_URL = "https://image.tmdb.org/t/p/w640<IMG_PATH>";

    // Array con el listado de películas a mostrar
    public static ArrayList<Movies> listaPopulares = new ArrayList<>();
    public static ArrayList<Movies> listaUpcoming = new ArrayList<>();
    public static ArrayList<Movies> listaBuscador = new ArrayList<>();

    // Path para embed de YouTube
    public static final String URL_YT_EMBED = "https://www.youtube.com/embed/";

    // Path para enlace a GitHub
    public static final String URL_GITHUB = "https://github.com/<USER>";

    // Usuario de GitHub
    public static final String GITHUB_USER = SecretConfig.GITHUB_USER;

    // Path para enlace a Linkedin
    public static final String URL_LINKEDIN = "https://www.linkedin.com/in/<USER>";

    // Usuario de Linkedin
    public static final String LINKEDIN_USER = SecretConfig.LINKEDIN_USER;

    // Path para enlace a TMDb
    public static final String URL_TMDB = "https://www.themoviedb.org/documentation/api";

    // Path para enlace a PlayStore
    public static final String URL_PLAYSTORE = "https://play.google.com/store/apps/details?id=<PAQUETE>";


    // TAGs listado de películas
    public static final String TAG_ID = "id";
    public static final String TAG_TITLE = "title";
    public static final String TAG_RELEASE_DATE = "release_date";
    public static final String TAG_POSTER = "poster_path";
    public static final String TAG_BACKDROP = "backdrop_path";
    public static final String TAG_AVERAGE = "vote_average";
}
