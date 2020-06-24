package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.source.local.LocalDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;
import com.anggiat.jetpackmoviecatalogue.utils.AppExecutors;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;
import com.anggiat.jetpackmoviecatalogue.utils.LiveDataTestUtil;
import com.anggiat.jetpackmoviecatalogue.utils.PagedListUtil;
import com.anggiat.jetpackmoviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = mock(RemoteDataSource.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);

    private FakeMovieRepository fakeMovieRepository = new FakeMovieRepository(remote, local, appExecutors);

    private ArrayList<MovieResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private String movieId = movieResponses.get(0).getId();
    private ArrayList<TvShowResponse> tvShowResponses = DataDummy.generateRemoteDummyTvShows();
    private String tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllMovies()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getAllMovies();

        Resource<PagedList<MovieEntity>> moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovies()));
        verify(local).getAllMovies();
        assertNotNull(moviesEntities.data);
        assertEquals(movieResponses.size(), moviesEntities.data.size());
    }

    @Test
    public void getMoviesById() {
        MutableLiveData<MovieEntity> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(DataDummy.generateDataMovies().get(0));
        when(local.getMoviesById(movieId)).thenReturn(dummyMovies);

        Resource<MovieEntity> movieEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getMoviesById(movieId));
        verify(local).getMoviesById(movieId);
        assertNotNull(movieEntities.data);
        assertNotNull(movieEntities.data.getTitle());
        assertNotNull(movieResponses.get(0).getTitle(), movieEntities.data.getTitle());
    }

    @Test
    public void getAllTvShow() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllTvShow()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getAllTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShows()));
        verify(local).getAllTvShow();
        assertNotNull(tvShowEntities.data);
        assertEquals(tvShowResponses.size(), tvShowEntities.data.size());
    }

    @Test
    public void getTvShowById() {
        MutableLiveData<TvShowEntity> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(DataDummy.generateDataTvShows().get(0));
        when(local.getTvShowById(tvShowId)).thenReturn(dummyTvShow);

        Resource<TvShowEntity> tvEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getTvShowById(tvShowId));
        verify(local).getTvShowById(tvShowId);
        assertNotNull(tvEntities.data);
        assertNotNull(tvEntities.data.getName());
        assertNotNull(tvShowResponses.get(0).getName(), tvEntities.data.getName());
    }

    @Test
    public void getFavoriteMovie() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoriteMovie()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getFavoriteMovie();

        Resource<PagedList<MovieEntity>> moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovies()));
        verify(local).getFavoriteMovie();
        assertNotNull(moviesEntities);
        assertEquals(movieResponses.size(), moviesEntities.data.size());
    }

    @Test
    public void getFavoriteTvShow() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoriteTvShow()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getFavoriteTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShows()));
        verify(local).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(tvShowResponses.size(), tvShowEntities.data.size());
    }


}