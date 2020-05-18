package com.anggiat.jetpackmoviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.response.TvShowResponse;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;
import com.anggiat.jetpackmoviecatalogue.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeMovieRepository fakeMovieRepository = new FakeMovieRepository(remote);

    private ArrayList<MovieResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private String movieId = movieResponses.get(0).getId();
    private ArrayList<TvShowResponse> tvShowResponses = DataDummy.generateRemoteDummyTvShows();
    private String tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieCallback) invocation.getArguments()[0])
                    .onAllMovieReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteDataSource.LoadMovieCallback.class));
        List<MovieEntity> movieEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getAllMovies());
        verify(remote).getAllMovies(any(RemoteDataSource.LoadMovieCallback.class));
        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.size());
    }

    @Test
    public void getMoviesById() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieCallback) invocation.getArguments()[0])
                    .onAllMovieReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteDataSource.LoadMovieCallback.class));
        MovieEntity movieEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getMoviesById(movieId));
        verify(remote).getAllMovies(any(RemoteDataSource.LoadMovieCallback.class));
        assertNotNull(movieEntities);
        assertNotNull(movieEntities.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), movieEntities.getTitle());
    }

    @Test
    public void getAllTvShow() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvShowCallback) invocation.getArguments()[0])
                    .onAllTvShowReceived(tvShowResponses);
            return null;
        }).when(remote).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));
        List<TvShowEntity> tvShowEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getAllTvShow());
        verify(remote).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));
        assertNotNull(tvShowEntities);
        assertEquals(tvShowResponses.size(), tvShowEntities.size());
    }

    @Test
    public void getTvShowById() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvShowCallback) invocation.getArguments()[0])
                    .onAllTvShowReceived(tvShowResponses);
            return null;
        }).when(remote).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));

        TvShowEntity tvShowEntities = LiveDataTestUtil.getValue(fakeMovieRepository.getTvShowById(tvShowId));
        verify(remote).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));
        assertNotNull(tvShowEntities);
        assertNotNull(tvShowEntities.getName());
        assertEquals(tvShowResponses.get(0).getName(), tvShowEntities.getName());
    }


}