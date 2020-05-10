package com.anggiat.jetpackmoviecatalogue.ui.detail;

import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailViewModelTest {
    private DetailViewModel detailViewModel;
    private MovieEntity dummyMovie = DataDummy.generateDataMovies().get(0);
    private TvShowEntity dummyTvShow = DataDummy.generateDataTvShows().get(0);
    private String movieId = dummyMovie.getId();
    private String tvShowId = dummyTvShow.getId();


    @Before
    public void setUp() {
        detailViewModel = new DetailViewModel();
        detailViewModel.setSelectedMovie(movieId);
        detailViewModel.setSelectedTvShow(tvShowId);
    }


    @Test
    public void getMovie() {
        detailViewModel.setSelectedMovie(dummyMovie.getId());
        MovieEntity movieEntity = detailViewModel.getMovie();

        assertNotNull(movieEntity);
        assertEquals(dummyMovie.getId(), movieEntity.getId());
        assertEquals(dummyMovie.getPosterPath(), movieEntity.getPosterPath());
        assertEquals(dummyMovie.getTitle(), movieEntity.getTitle());
        assertEquals(dummyMovie.getGenreOne(), movieEntity.getGenreOne());
        assertEquals(dummyMovie.getGenreTwo(), movieEntity.getGenreTwo());
        assertEquals(dummyMovie.getRuntime(), movieEntity.getRuntime());
        assertEquals(dummyMovie.getSpokenLanguages(), movieEntity.getSpokenLanguages());
        assertEquals(dummyMovie.getVoteAverage(), movieEntity.getVoteAverage());
        assertEquals(dummyMovie.getOverview(), movieEntity.getOverview());
        assertEquals(dummyMovie.getBackdropPath(), movieEntity.getBackdropPath());
        assertEquals(dummyMovie.getReleaseDate(), movieEntity.getReleaseDate());
        assertEquals(dummyMovie.getKeyTrailer(), movieEntity.getKeyTrailer());
    }

    @Test
    public void getTvShow() {
        detailViewModel.setSelectedTvShow(dummyTvShow.getId());
        TvShowEntity tvShowEntity = detailViewModel.getTvShow();

        assertEquals(dummyTvShow.getId(), tvShowEntity.getId());
        assertEquals(dummyTvShow.getPosterPath(), tvShowEntity.getPosterPath());
        assertEquals(dummyTvShow.getName(), tvShowEntity.getName());
        assertEquals(dummyTvShow.getGenreOne(), tvShowEntity.getGenreOne());
        assertEquals(dummyTvShow.getGenreTwo(), tvShowEntity.getGenreTwo());
        assertEquals(dummyTvShow.getNumberOfSeasons(), tvShowEntity.getNumberOfSeasons());
        assertEquals(dummyTvShow.getNumberOfEpisodes(), tvShowEntity.getNumberOfEpisodes());
        assertEquals(dummyTvShow.getVoteAverage(), tvShowEntity.getVoteAverage());
        assertEquals(dummyTvShow.getOverview(), tvShowEntity.getOverview());
        assertEquals(dummyTvShow.getBackdropPath(), tvShowEntity.getBackdropPath());
        assertEquals(dummyTvShow.getFirstAirDate(), tvShowEntity.getFirstAirDate());
        assertEquals(dummyTvShow.getKeyTrailer(), tvShowEntity.getKeyTrailer());
    }
}