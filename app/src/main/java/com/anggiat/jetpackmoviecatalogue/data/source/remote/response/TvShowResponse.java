package com.anggiat.jetpackmoviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowResponse implements Parcelable {
    private String id;
    private String posterPath;
    private String name;
    private String genreOne;
    private String genreTwo;
    private String numberOfSeasons;
    private String numberOfEpisodes;
    private String voteAverage;
    private String overview;
    private String backdropPath;
    private String firstAirDate;
    private String keyTrailer;

    public TvShowResponse(String id, String posterPath, String name, String genreOne, String genreTwo, String numberOfSeasons, String numberOfEpisodes, String voteAverage, String overview, String backdropPath, String firstAirDate, String keyTrailer) {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenreOne() {
        return genreOne;
    }

    public String getGenreTwo() {
        return genreTwo;
    }


    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }


    public String getKeyTrailer() {
        return keyTrailer;
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
    }

    protected TvShowResponse(Parcel in) {
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
    }

    public static final Parcelable.Creator<TvShowResponse> CREATOR = new Parcelable.Creator<TvShowResponse>() {
        @Override
        public TvShowResponse createFromParcel(Parcel source) {
            return new TvShowResponse(source);
        }

        @Override
        public TvShowResponse[] newArray(int size) {
            return new TvShowResponse[size];
        }
    };
}
