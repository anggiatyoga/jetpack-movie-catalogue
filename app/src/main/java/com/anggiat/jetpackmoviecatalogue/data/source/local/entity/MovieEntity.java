package com.anggiat.jetpackmoviecatalogue.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_entities")
public class MovieEntity implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "genre_one")
    private String genreOne;

    @ColumnInfo(name = "genre_two")
    private String genreTwo;

    @ColumnInfo(name = "runtime")
    private String runtime;

    @ColumnInfo(name = "spoken_language")
    private String spokenLanguages;

    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "key_trailer")
    private String keyTrailer;

    @ColumnInfo(name = "status")
    private boolean status = false;

    public MovieEntity() {
    }

    public MovieEntity(String id, String posterPath, String title, String genreOne, String genreTwo, String runtime, String spokenLanguages, String voteAverage, String overview, String backdropPath, String releaseDate, String keyTrailer, boolean status) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.genreOne = genreOne;
        this.genreTwo = genreTwo;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.keyTrailer = keyTrailer;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenreOne() {
        return genreOne;
    }

    public void setGenreOne(String genreOne) {
        this.genreOne = genreOne;
    }

    public String getGenreTwo() {
        return genreTwo;
    }

    public void setGenreTwo(String genreTwo) {
        this.genreTwo = genreTwo;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(String spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getKeyTrailer() {
        return keyTrailer;
    }

    public void setKeyTrailer(String keyTrailer) {
        this.keyTrailer = keyTrailer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeString(this.genreOne);
        dest.writeString(this.genreTwo);
        dest.writeString(this.runtime);
        dest.writeString(this.spokenLanguages);
        dest.writeString(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeString(this.keyTrailer);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
    }

    protected MovieEntity(Parcel in) {
        this.id = in.readString();
        this.posterPath = in.readString();
        this.title = in.readString();
        this.genreOne = in.readString();
        this.genreTwo = in.readString();
        this.runtime = in.readString();
        this.spokenLanguages = in.readString();
        this.voteAverage = in.readString();
        this.overview = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.keyTrailer = in.readString();
        this.status = in.readByte() != 0;
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
