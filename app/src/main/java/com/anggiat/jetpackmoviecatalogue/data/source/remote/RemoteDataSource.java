package com.anggiat.jetpackmoviecatalogue.data.source.remote;

import android.os.Handler;

import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;
import com.anggiat.jetpackmoviecatalogue.utils.EspressoIdlingResource;
import com.anggiat.jetpackmoviecatalogue.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler();
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    public RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static  RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadMovieCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(()-> {
            callback.onAllMovieReceived(jsonHelper.loadMovies());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getAllTvShow(LoadTvShowCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(()-> {
            callback.onAllTvShowReceived(jsonHelper.loadTvShow());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMovieCallback {
        void onAllMovieReceived(List<MovieResponse> movieResponses);
    }

    public interface LoadTvShowCallback {
        void onAllTvShowReceived(List<TvShowResponse> tvShowResponses);
    }

}
