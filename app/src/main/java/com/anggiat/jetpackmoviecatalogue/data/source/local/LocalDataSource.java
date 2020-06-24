package com.anggiat.jetpackmoviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.room.MoviesDao;

import java.util.List;

public class LocalDataSource {
    private static LocalDataSource INSTANCE;
    private MoviesDao moviesDao;

    public LocalDataSource(MoviesDao movieDao) {
        this.moviesDao = movieDao;
    }

    public static LocalDataSource getInstance(MoviesDao moviesDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(moviesDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, MovieEntity> getAllMovies() {
        return moviesDao.getMovies();
    }

    public LiveData<MovieEntity> getMoviesById(final String movieId) {
        return moviesDao.getMovieById(movieId);
    }


    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovie() {
        return moviesDao.getFavoriteMovie();
    }

    public void setMovieFavorite(MovieEntity movie, boolean newState) {
        movie.setStatus(newState);
        moviesDao.updateFavoriteMovie(movie);
    }

    public void insertFavoriteMovies(List<MovieEntity> movies) {
        moviesDao.insertFavoriteMovie(movies);
    }

    public DataSource.Factory<Integer, TvShowEntity> getAllTvShow() {
        return moviesDao.getTvShow();
    }

    public LiveData<TvShowEntity> getTvShowById(final String tvShowId) {
        return moviesDao.getTvShowById(tvShowId);
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow() {
        return moviesDao.getFavoriteTvShow();
    }

    public void setTvShowFavorite(TvShowEntity tvShow, boolean newState) {
        tvShow.setStatus(newState);
        moviesDao.updateFavoriteTvShow(tvShow);
    }

    public void insertFavoriteTvShow(List<TvShowEntity> tvShow) {
        moviesDao.insertFavoriteTvShow(tvShow);
    }

}
