package com.anggiat.jetpackmoviecatalogue.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;
import com.anggiat.jetpackmoviecatalogue.utils.EspressoIdlingResource;
import com.anggiat.jetpackmoviecatalogue.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private Handler handler = new Handler();


    public RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static  RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> movieResponse = new MutableLiveData<>();
        handler.postDelayed(()-> {
            movieResponse.setValue(ApiResponse.success(jsonHelper.loadMovies()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return movieResponse;
    }

    public LiveData<ApiResponse<List<TvShowResponse>>> getAllTvShow() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvShowResponse>>> tvShowResponse = new MutableLiveData<>();
        handler.postDelayed(()-> {
            tvShowResponse.setValue(ApiResponse.success(jsonHelper.loadTvShow()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return tvShowResponse;
    }

}
