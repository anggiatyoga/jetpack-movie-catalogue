package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TvShowViewModelTest {
    private TvShowViewModel tvShowViewModel;

    @Before
    public void setUp() {
        tvShowViewModel = new TvShowViewModel();
    }

    @Test
    public void getTvShows() {
        List<TvShowEntity> tvShowEntities = tvShowViewModel.getTvShows();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());
    }
}