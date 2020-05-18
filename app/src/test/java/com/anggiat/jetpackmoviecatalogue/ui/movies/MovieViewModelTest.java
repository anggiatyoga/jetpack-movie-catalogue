package com.anggiat.jetpackmoviecatalogue.ui.movies;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel movieViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<List<MovieEntity>> observer;

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<MovieEntity> dummyMovies = DataDummy.generateDataMovies();
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(movieRepository.getAllMovies()).thenReturn(movies);
        List<MovieEntity> movieEntities = movieViewModel.getMovies().getValue();
        verify(movieRepository).getAllMovies();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());

        movieViewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}