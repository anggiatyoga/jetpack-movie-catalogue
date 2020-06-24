package com.anggiat.jetpackmoviecatalogue.ui.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteTvShowViewModelTest {
    private FavoriteTvShowViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<PagedList<TvShowEntity>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new FavoriteTvShowViewModel(movieRepository);
    }

    @Test
    public void getFavoriteTvShow() {
        PagedList<TvShowEntity> dummyTvShow = pagedList;
        when(dummyTvShow.size()).thenReturn(10);
        MutableLiveData<PagedList<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShow);

        when(movieRepository.getFavoriteTvShow()).thenReturn(tvShows);
        List<TvShowEntity> tvShowEntities = viewModel.getFavoriteTvShow().getValue();
        verify(movieRepository).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());

        viewModel.getFavoriteTvShow().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);

    }



}