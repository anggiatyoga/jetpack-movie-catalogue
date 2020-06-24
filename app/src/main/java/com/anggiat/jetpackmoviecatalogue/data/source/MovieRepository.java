package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.NetworkBoundResource;
import com.anggiat.jetpackmoviecatalogue.data.source.local.LocalDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.ApiResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;
import com.anggiat.jetpackmoviecatalogue.utils.AppExecutors;
import com.anggiat.jetpackmoviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieDataSource{

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public MovieRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteDataSource, localDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getAllMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllMovies(), config).build();
//                return localDataSource.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteDataSource.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> movieResponses) {
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
                            response.getKeyTrailer(),
                            false
                    );
                    movieList.add(movie);
                }
                localDataSource.insertFavoriteMovies(movieList);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<MovieEntity>> getMoviesById(String moviesId) {
       return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

           @Override
           protected LiveData<MovieEntity> loadFromDB() {
               return localDataSource.getMoviesById(moviesId);
           }

           @Override
           protected Boolean shouldFetch(MovieEntity data) {
               return data == null;
           }

           @Override
           protected LiveData<ApiResponse<MovieResponse>> createCall() {
               return null;
           }

           @Override
           protected void saveCallResult(MovieResponse data) {

           }
       }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getAllTvShow() {
        return new NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTvShow(), config).build();
//                return localDataSource.getAllTvShow();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return remoteDataSource.getAllTvShow();
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TvShowResponse response : data) {
                    tvShowEntities.add(new TvShowEntity(
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
                            response.getKeyTrailer(),
                            false
                    ));
                    localDataSource.insertFavoriteTvShow(tvShowEntities);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvShowById(String tvShowId) {
        return new NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {

            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localDataSource.getTvShowById(tvShowId);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<TvShowResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(TvShowResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteMovie(), config).build();
//        return localDataSource.getFavoriteMovie();
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShow(), config).build();
//        return localDataSource.getFavoriteTvShow();
    }

    @Override
    public void setFavoriteMovie(MovieEntity movie, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieFavorite(movie, state));
    }

    @Override
    public void setFavoriteTvShow(TvShowEntity tvShow, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setTvShowFavorite(tvShow, state));
    }


}
