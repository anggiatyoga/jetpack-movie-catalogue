package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

public interface MovieDataSource {

    LiveData<List<MovieEntity>> getAllMovies();

    LiveData<MovieEntity> getMoviesById(String moviesId);

    LiveData<List<TvShowEntity>> getAllTvShow();

    LiveData<TvShowEntity> getTvShowById(String tvShowId);

}
