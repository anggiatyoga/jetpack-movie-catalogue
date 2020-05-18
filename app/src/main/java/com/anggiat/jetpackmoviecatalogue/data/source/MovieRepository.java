package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieDataSource{

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    public MovieRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllMovies() {
        MutableLiveData<List<MovieEntity>> movieResults = new MutableLiveData<>();

        remoteDataSource.getAllMovies(movieResponses -> {
            ArrayList<MovieEntity> movieList = new ArrayList<>();
            for (MovieResponse response : movieResponses) {
                MovieEntity movie = new MovieEntity(
                        response.getId(),
                        response.getPosterPath(),
                        response.getTitle(),
                        response.getGenreOne(),
                        response.getGenreTwo(),
                        response.getRuntime(),
                        response.getSpokenLanguages(),
                        response.getVoteAverage(),
                        response.getOverview(),
                        response.getBackdropPath(),
                        response.getReleaseDate(),
                        response.getKeyTrailer()
                );
                movieList.add(movie);
            }
            movieResults.postValue(movieList);
        });
        return movieResults;
    }

    @Override
    public LiveData<MovieEntity> getMoviesById(String moviesId) {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

        remoteDataSource.getAllMovies(movieResponses -> {
            MovieEntity movie = null;
            for (MovieResponse response : movieResponses) {
                if (response.getId().equals(moviesId)) {
                    movie = new MovieEntity(
                            response.getId(),
                            response.getPosterPath(),
                            response.getTitle(),
                            response.getGenreOne(),
                            response.getGenreTwo(),
                            response.getRuntime(),
                            response.getSpokenLanguages(),
                            response.getVoteAverage(),
                            response.getOverview(),
                            response.getBackdropPath(),
                            response.getReleaseDate(),
                            response.getKeyTrailer()
                    );
                }
            }
            movieResult.postValue(movie);
        });
        return movieResult;
    }

    @Override
    public LiveData<List<TvShowEntity>> getAllTvShow() {
        MutableLiveData<List<TvShowEntity>> tvShowResults = new MutableLiveData<>();
        remoteDataSource.getAllTvShow(tvShowResponses -> {
            ArrayList<TvShowEntity> tvShowList = new ArrayList<>();
            for (TvShowResponse response : tvShowResponses) {
                TvShowEntity tvShow = new TvShowEntity(
                        response.getId(),
                        response.getPosterPath(),
                        response.getName(),
                        response.getGenreOne(),
                        response.getGenreTwo(),
                        response.getNumberOfSeasons(),
                        response.getNumberOfEpisodes(),
                        response.getVoteAverage(),
                        response.getOverview(),
                        response.getBackdropPath(),
                        response.getFirstAirDate(),
                        response.getKeyTrailer()
                );
                tvShowList.add(tvShow);
            }
            tvShowResults.postValue(tvShowList);
        });
        return tvShowResults;
    }

    @Override
    public LiveData<TvShowEntity> getTvShowById(String tvShowId) {
        MutableLiveData<TvShowEntity> tvShowResult = new MutableLiveData<>();
        remoteDataSource.getAllTvShow(tvShowResponses -> {
            TvShowEntity tvShow = null;
            for (TvShowResponse response : tvShowResponses) {
                if (response.getId().equals(tvShowId)) {
                    tvShow = new TvShowEntity(
                            response.getId(),
                            response.getPosterPath(),
                            response.getName(),
                            response.getGenreOne(),
                            response.getGenreTwo(),
                            response.getNumberOfSeasons(),
                            response.getNumberOfEpisodes(),
                            response.getVoteAverage(),
                            response.getOverview(),
                            response.getBackdropPath(),
                            response.getFirstAirDate(),
                            response.getKeyTrailer()
                    );
                }
            }
            tvShowResult.postValue(tvShow);
        });
        return tvShowResult;
    }
}
