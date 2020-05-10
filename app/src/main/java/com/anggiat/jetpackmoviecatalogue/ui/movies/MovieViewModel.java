package com.anggiat.jetpackmoviecatalogue.ui.movies;

import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {

    List<MovieEntity> getMovies(){
        return DataDummy.generateDataMovies();
    }

}
