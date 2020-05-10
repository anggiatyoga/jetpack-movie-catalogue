package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {
    private String movieId;
    private String tvShowId;

    public void setSelectedMovie(String movieId) {
        this.movieId = movieId;
    }

    public void setSelectedTvShow(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    public MovieEntity getMovie() {
        MovieEntity movie = null;
        ArrayList<MovieEntity> movieEntities = DataDummy.generateDataMovies();
        for (MovieEntity movieEntity : movieEntities) {
            if (movieEntity.getId().equals(movieId)) {
                movie = movieEntity;
            }
        }
        return movie;
    }

    public TvShowEntity getTvShow() {
        TvShowEntity tvShow = null;
        ArrayList<TvShowEntity> tvShowEntities = DataDummy.generateDataTvShows();
        for (TvShowEntity tvShowEntity : tvShowEntities) {
            if (tvShowEntity.getId().equals(tvShowId)) {
                tvShow = tvShowEntity;
            }
        }
        return tvShow;
    }


}
