package com.anggiat.jetpackmoviecatalogue.ui.favorite.tvshow;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {
    private RecyclerView rvFavoriteTvShow;
    private ProgressBar progressBar;

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavoriteTvShow = view.findViewById(R.id.rv_favorite_tv_show);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            FavoriteTvShowViewModel viewModel = new ViewModelProvider(this, factory).get(FavoriteTvShowViewModel.class);
            FavoriteTvShowAdapter adapter = new FavoriteTvShowAdapter();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getFavoriteTvShow().observe(this, tvShowEntities -> {
                progressBar.setVisibility(View.GONE);
                adapter.submitList(tvShowEntities);
                adapter.notifyDataSetChanged();
            });
            rvFavoriteTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavoriteTvShow.setHasFixedSize(true);
            rvFavoriteTvShow.setAdapter(adapter);
        }
    }
}
