package com.anggiat.jetpackmoviecatalogue.ui.favorite.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

public class FavoriteTvShowViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavoriteTvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        return movieRepository.getFavoriteTvShow();
    }

}
