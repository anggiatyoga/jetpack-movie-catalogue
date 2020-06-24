package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.vo.Resource;

public interface MovieDataSource {

    LiveData<Resource<PagedList<MovieEntity>>> getAllMovies();

    LiveData<Resource<MovieEntity>> getMoviesById(String moviesId);

    LiveData<Resource<PagedList<TvShowEntity>>> getAllTvShow();

    LiveData<Resource<TvShowEntity>> getTvShowById(String tvShowId);

    LiveData<PagedList<MovieEntity>> getFavoriteMovie();

    LiveData<PagedList<TvShowEntity>> getFavoriteTvShow();

    void setFavoriteMovie(MovieEntity movie, boolean state);

    void setFavoriteTvShow(TvShowEntity tvShow, boolean state);

}
