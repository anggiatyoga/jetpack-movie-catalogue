package com.anggiat.jetpackmoviecatalogue.di;

import android.content.Context;

import com.anggiat.jetpackmoviecatalogue.data.source.MovieRepository;
import com.anggiat.jetpackmoviecatalogue.data.source.local.LocalDataSource;
import com.anggiat.jetpackmoviecatalogue.data.source.local.room.MoviesRoomDatabase;
import com.anggiat.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.anggiat.jetpackmoviecatalogue.utils.AppExecutors;
import com.anggiat.jetpackmoviecatalogue.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Context context) {
        MoviesRoomDatabase database = MoviesRoomDatabase.getInstance(context);
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.movieDao());
        AppExecutors appExecutors = new AppExecutors();
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
