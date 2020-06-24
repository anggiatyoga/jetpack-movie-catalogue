package com.anggiat.jetpackmoviecatalogue.ui.favorite.movie;

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
import android.widget.ProgressBar;

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.utils.viewmodel.ViewModelFactory;

public class FavoriteMovieFragment extends Fragment {
    private RecyclerView rvFavoriteMovie;
    private ProgressBar progressBar;

    public FavoriteMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavoriteMovie = view.findViewById(R.id.rv_favorite_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            FavoriteMovieViewModel viewModel = new ViewModelProvider(this, factory).get(FavoriteMovieViewModel.class);
            FavoriteMovieAdapter adapter = new FavoriteMovieAdapter();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getFavoriteMovies().observe(this, movieEntities -> {
                progressBar.setVisibility(View.GONE);
                adapter.submitList(movieEntities);
                adapter.notifyDataSetChanged();
            });

            rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavoriteMovie.setHasFixedSize(true);
            rvFavoriteMovie.setAdapter(adapter);
        }
    }
}
