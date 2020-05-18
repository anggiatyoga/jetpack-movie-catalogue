package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

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

public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;
    private ProgressBar progressBar;

    public TvShowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            TvShowViewModel tvShowViewModel = new ViewModelProvider(this, factory).get(TvShowViewModel.class);
            TvShowAdapter tvShowAdapter = new TvShowAdapter();
            progressBar.setVisibility(View.VISIBLE);
            tvShowViewModel.getTvShows().observe(this, tvShowEntities -> {
                progressBar.setVisibility(View.GONE);
                tvShowAdapter.setTvShow(tvShowEntities);
                tvShowAdapter.notifyDataSetChanged();
            });

            rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShow.setHasFixedSize(true);
            rvTvShow.setAdapter(tvShowAdapter);
        }
    }
}
