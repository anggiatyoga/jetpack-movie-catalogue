package com.anggiat.jetpackmoviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;

public class FavoriteMovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavoriteMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<PagedList<MovieEntity>> getFavoriteMovies() {
        return movieRepository.getFavoriteMovie();
    }


}
