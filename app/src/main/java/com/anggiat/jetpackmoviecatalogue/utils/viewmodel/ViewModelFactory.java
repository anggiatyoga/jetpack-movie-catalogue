package com.anggiat.jetpackmoviecatalogue.utils.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.di.Injection;
import com.anggiat.jetpackmoviecatalogue.ui.detail.DetailViewModel;
import com.anggiat.jetpackmoviecatalogue.ui.movies.MovieViewModel;
import com.anggiat.jetpackmoviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository mMovieRepository;

    public ViewModelFactory(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            return (T) new TvShowViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mMovieRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: "+modelClass.getName());
    }

}
