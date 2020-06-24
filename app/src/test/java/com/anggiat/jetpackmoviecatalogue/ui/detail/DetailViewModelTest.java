package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;
import com.anggiat.jetpackmoviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel detailViewModel;
    private Resource<MovieEntity> dummyMovie = Resource.success(DataDummy.generateDataMovies().get(0));
    private Resource<TvShowEntity> dummyTvShow = Resource.success(DataDummy.generateDataTvShows().get(0));
    private String movieId = dummyMovie.data.getId();
    private String tvShowId = dummyTvShow.data.getId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutor = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<Resource<MovieEntity>> movieObserver;

    @Mock
    private Observer<Resource<TvShowEntity>> tvShowObserver;

    @Before
    public void setUp() {
        detailViewModel = new DetailViewModel(movieRepository);
        detailViewModel.setMovieId(movieId);
        detailViewModel.setTvShowId(tvShowId);
    }


    @Test
    public void getMovie() {
        MutableLiveData<Resource<MovieEntity>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(movieRepository.getMoviesById(movieId)).thenReturn(movie);

        movieObserver = mock(Observer.class);

        detailViewModel.movieDetail.observeForever(movieObserver);
        verify(movieObserver).onChanged(dummyMovie);
    }

    @Test
    public void getTvShow() {
        MutableLiveData<Resource<TvShowEntity>> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);

        when(movieRepository.getTvShowById(tvShowId)).thenReturn(tvShow);
        tvShowObserver = mock(Observer.class);

        detailViewModel.tvShowDetail.observeForever(tvShowObserver);
        verify(tvShowObserver).onChanged(dummyTvShow);
    }
}