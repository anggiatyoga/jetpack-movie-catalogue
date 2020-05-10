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

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;

import java.util.List;

public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TvShowViewModel tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
//            List<TvShowEntity> tvShow = tvShowViewModel.getTvShows();

            TvShowAdapter tvShowAdapter = new TvShowAdapter();
            tvShowAdapter.setTvShow(tvShowViewModel.getTvShows());

            rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShow.setHasFixedSize(true);
            rvTvShow.setAdapter(tvShowAdapter);
        }
    }
}
