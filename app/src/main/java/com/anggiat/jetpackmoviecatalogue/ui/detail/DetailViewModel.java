package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

public class DetailViewModel extends ViewModel {
    private String movieId;
    private String tvShowId;
    private MovieRepository movieRepository;

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setSelectedMovie(String movieId) {
        this.movieId = movieId;
    }

    public LiveData<MovieEntity> getMovie() {
        return movieRepository.getMoviesById(movieId);
    }

    public void setSelectedTvShow(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    public LiveData<TvShowEntity> getTvShow() {
        return movieRepository.getTvShowById(tvShowId);
    }

}
