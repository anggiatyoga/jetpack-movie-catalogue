package com.anggiat.jetpackmoviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponse implements Parcelable {
    private String id;
    private String posterPath;
    private String title;
    private String genreOne;
    private String genreTwo;
    private String runtime;
    private String spokenLanguages;
    private String voteAverage;
    private String overview;
    private String backdropPath;
    private String releaseDate;
    private String keyTrailer;

    public MovieResponse(String id, String posterPath, String title, String genreOne, String genreTwo, String runtime, String spokenLanguages, String voteAverage, String overview, String backdropPath, String releaseDate, String keyTrailer) {
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
    }

    protected MovieResponse(Parcel in) {
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
    }

    public static final Parcelable.Creator<MovieResponse> CREATOR = new Parcelable.Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel source) {
            return new MovieResponse(source);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };
}
