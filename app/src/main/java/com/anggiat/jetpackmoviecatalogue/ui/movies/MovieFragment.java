package com.anggiat.jetpackmoviecatalogue.ui.movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;

import java.util.List;

public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;

    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
//            List<MovieEntity> movies = movieViewModel.getMovies();

            MovieAdapter movieAdapter = new MovieAdapter();
            movieAdapter.setMovies(movieViewModel.getMovies());

            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(movieAdapter);

        }
    }
}
