package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.vo.Resource;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<String> movieId = new MutableLiveData<>();
    private MutableLiveData<String> tvShowId = new MutableLiveData<>();
    private MovieRepository movieRepository;

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<MovieEntity>> movieDetail = Transformations.switchMap(
            movieId, mMovieId -> movieRepository.getMoviesById(mMovieId)
    );

    public void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    LiveData<Resource<TvShowEntity>> tvShowDetail = Transformations.switchMap(
            tvShowId, mTvShowId -> movieRepository.getTvShowById(mTvShowId)
    );

    public void setTvShowId(String tvShowId) {
        this.tvShowId.setValue(tvShowId);
    }

    public void setFavorite() {
        if (movieDetail.getValue() != null) {
            MovieEntity movieEntity = movieDetail.getValue().data;
            final boolean newState = !movieEntity.isStatus();
            movieRepository.setFavoriteMovie(movieEntity, newState);
        } else if (tvShowDetail.getValue() != null) {
            TvShowEntity tvShowEntity = tvShowDetail.getValue().data;
            final boolean newState = !tvShowEntity.isStatus();
            movieRepository.setFavoriteTvShow(tvShowEntity, newState);
        }
    }

}
