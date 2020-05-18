package com.anggiat.jetpackmoviecatalogue.di;

import android.content.Context;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Context context) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        return MovieRepository.getInstance(remoteDataSource);
    }
}
