package com.anggiat.jetpackmoviecatalogue.utils;

import android.content.Context;

import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> loadMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("Movie.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject movie = listArray.getJSONObject(i);

                    String id = movie.getString("id");
                    String posterPath = movie.getString("poster_path");
                    String title = movie.getString("title");
                    String genreOne = movie.getString("genre_one");
                    String genreTwo = movie.getString("genre_two");
                    String runtime = movie.getString("runtime");
                    String spokenLanguages = movie.getString("spoken_languages");
                    String voteAverage = movie.getString("vote_average");
                    String overview = movie.getString("overview");
                    String backdropPath = movie.getString("backdrop_path");
                    String releaseDate = movie.getString("release_date");
                    String keyTrailer = movie.getString("key_trailer");

                    MovieResponse movieResponse = new MovieResponse(id, posterPath, title, genreOne, genreTwo, runtime, spokenLanguages, voteAverage, overview, backdropPath, releaseDate, keyTrailer);
                    list.add(movieResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TvShowResponse> loadTvShow() {
        ArrayList<TvShowResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("TvShow.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject tvShow = listArray.getJSONObject(i);

                    String id = tvShow.getString("id");
                    String posterPath = tvShow.getString("poster_path");
                    String title = tvShow.getString("title");
                    String genreOne = tvShow.getString("genre_one");
                    String genreTwo = tvShow.getString("genre_two");
                    String numberOfSeasons = tvShow.getString("number_of_seasons");
                    String numberOfEpisodes = tvShow.getString("number_of_episodes");
                    String voteAverage = tvShow.getString("vote_average");
                    String overview = tvShow.getString("overview");
                    String backdropPath = tvShow.getString("backdrop_path");
                    String firstAirDate = tvShow.getString("first_air_date");
                    String keyTrailer = tvShow.getString("key_trailer");

                    TvShowResponse tvShowResponse = new TvShowResponse(id, posterPath, title, genreOne, genreTwo, numberOfSeasons, numberOfEpisodes, voteAverage, overview, backdropPath, firstAirDate, keyTrailer);
                    list.add(tvShowResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
