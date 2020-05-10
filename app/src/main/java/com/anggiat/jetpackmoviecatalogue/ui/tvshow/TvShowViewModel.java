package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    List<TvShowEntity> getTvShows() {
        return DataDummy.generateDataTvShows();
    }

}
