package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<TvShowEntity>> getTvShows() {
        return movieRepository.getAllTvShow();
    }

}
