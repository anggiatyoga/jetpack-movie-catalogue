package com.anggiat.jetpackmoviecatalogue.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshow_entities")
public class TvShowEntity implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "genre_one")
    private String genreOne;

    @ColumnInfo(name = "genre_two")
    private String genreTwo;

    @ColumnInfo(name = "number_of_seasons")
    private String numberOfSeasons;

    @ColumnInfo(name = "number_of_episodes")
    private String numberOfEpisodes;

    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "first_air_date")
    private String firstAirDate;

    @ColumnInfo(name = "key_trailer")
    private String keyTrailer;

    @ColumnInfo(name = "status")
    private boolean status = false;

    public TvShowEntity() {
    }

    public TvShowEntity(String id, String posterPath, String name, String genreOne, String genreTwo, String numberOfSeasons, String numberOfEpisodes, String voteAverage, String overview, String backdropPath, String firstAirDate, String keyTrailer, boolean status) {
        this.id = id;
        this.posterPath = posterPath;
        this.name = name;
        this.genreOne = genreOne;
        this.genreTwo = genreTwo;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
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

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
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
        dest.writeString(this.name);
        dest.writeString(this.genreOne);
        dest.writeString(this.genreTwo);
        dest.writeString(this.numberOfSeasons);
        dest.writeString(this.numberOfEpisodes);
        dest.writeString(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.backdropPath);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.keyTrailer);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
    }

    protected TvShowEntity(Parcel in) {
        this.id = in.readString();
        this.posterPath = in.readString();
        this.name = in.readString();
        this.genreOne = in.readString();
        this.genreTwo = in.readString();
        this.numberOfSeasons = in.readString();
        this.numberOfEpisodes = in.readString();
        this.voteAverage = in.readString();
        this.overview = in.readString();
        this.backdropPath = in.readString();
        this.firstAirDate = in.readString();
        this.keyTrailer = in.readString();
        this.status = in.readByte() != 0;
    }

    public static final Creator<TvShowEntity> CREATOR = new Creator<TvShowEntity>() {
        @Override
        public TvShowEntity createFromParcel(Parcel source) {
            return new TvShowEntity(source);
        }

        @Override
        public TvShowEntity[] newArray(int size) {
            return new TvShowEntity[size];
        }
    };
}


