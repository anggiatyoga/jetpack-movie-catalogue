package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
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
public class TvShowViewModelTest {
    private TvShowViewModel tvShowViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<List<TvShowEntity>> observer;

    @Before
    public void setUp() {
        tvShowViewModel = new TvShowViewModel(movieRepository);
    }

    @Test
    public void getTvShows() {
        ArrayList<TvShowEntity> dummyTvShows = DataDummy.generateDataTvShows();
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShows);

        when(movieRepository.getAllTvShow()).thenReturn(tvShows);
        List<TvShowEntity> tvShowEntities = tvShowViewModel.getTvShows().getValue();
        verify(movieRepository).getAllTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());

        tvShowViewModel.getTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShows);
    }
}