package com.anggiat.jetpackmoviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
public abstract class MoviesRoomDatabase extends RoomDatabase {
    public abstract MoviesDao movieDao();

    private static volatile MoviesRoomDatabase INSTANCE;

    public static MoviesRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesRoomDatabase.class, "movies.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
