package com.anggiat.jetpackmoviecatalogue.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

@Dao
public interface MoviesDao {

    //Movies
    @Query("SELECT * FROM movie_entities")
    DataSource.Factory<Integer, MovieEntity> getMovies();

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    LiveData<MovieEntity> getMovieById(String id);

    @Query("SELECT * FROM movie_entities where status = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteMovie(List<MovieEntity> movie);

    @Update
    void updateFavoriteMovie(MovieEntity movie);

    //TvShow
    @Query("SELECT * FROM tvshow_entities")
    DataSource.Factory<Integer, TvShowEntity> getTvShow();

    @Query("SELECT * FROM tvshow_entities WHERE id = :id")
    LiveData<TvShowEntity> getTvShowById(String id);

    @Query("SELECT * FROM tvshow_entities where status = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteTvShow(List<TvShowEntity> tvShow);

    @Update
    void updateFavoriteTvShow(TvShowEntity tvShow);

}
