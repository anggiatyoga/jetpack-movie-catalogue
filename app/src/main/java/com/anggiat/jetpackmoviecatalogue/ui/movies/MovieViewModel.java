package com.anggiat.jetpackmoviecatalogue.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<MovieEntity>> getMovies() {
        return movieRepository.getAllMovies();
    }

}
