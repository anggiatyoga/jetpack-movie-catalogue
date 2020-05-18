package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel detailViewModel;
    private MovieEntity dummyMovie = DataDummy.generateDataMovies().get(0);
    private TvShowEntity dummyTvShow = DataDummy.generateDataTvShows().get(0);
    private String movieId = dummyMovie.getId();
    private String tvShowId = dummyTvShow.getId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutor = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<MovieEntity> movieObserver;

    @Mock
    private Observer<TvShowEntity> tvShowObserver;

    @Before
    public void setUp() {
        detailViewModel = new DetailViewModel(movieRepository);
        detailViewModel.setSelectedMovie(movieId);
        detailViewModel.setSelectedTvShow(tvShowId);
    }


    @Test
    public void getMovie() {
        MutableLiveData<MovieEntity> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);
        when(movieRepository.getMoviesById(movieId)).thenReturn(movie);
        MovieEntity movieEntity = detailViewModel.getMovie().getValue();
        verify(movieRepository).getMoviesById(movieId);
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

        detailViewModel.getMovie().observeForever(movieObserver);
        verify(movieObserver).onChanged(dummyMovie);
    }

    @Test
    public void getTvShow() {
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);
        when(movieRepository.getTvShowById(tvShowId)).thenReturn(tvShow);
        TvShowEntity tvShowEntity = detailViewModel.getTvShow().getValue();
        verify(movieRepository).getTvShowById(tvShowId);
        assertNotNull(tvShowEntity);
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

        detailViewModel.getTvShow().observeForever(tvShowObserver);
        verify(tvShowObserver).onChanged(dummyTvShow);
    }
}