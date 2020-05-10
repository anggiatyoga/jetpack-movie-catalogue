package com.anggiat.jetpackmoviecatalogue.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.anggiat.jetpackmoviecatalogue.BuildConfig;
import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    public static final String urlImage = BuildConfig.URL_IMAGE;
    private ImageView imageBackdrop;
    private ImageView imagePoster;
    private TextView textOverview;
    private TextView textTitle;
    private TextView textVoteAverage;
    private TextView textGenreOne;
    private TextView textGenreTwo;
    private TextView textReleaseDate;
    private TextView textRuntime;
    private TextView textLanguage;
    private FloatingActionButton fabPlayButton;
    private TextView textLabelRuntime;
    private TextView textLabelLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imageBackdrop = findViewById(R.id.img_backdrop_detail);
        textOverview = findViewById(R.id.tv_overview_detail);
        imagePoster = findViewById(R.id.img_poster_detail);
        textTitle = findViewById(R.id.tv_title_detail);
        textVoteAverage = findViewById(R.id.tv_vote_average_detail);
        textGenreOne = findViewById(R.id.tv_item_genre_one);
        textGenreTwo = findViewById(R.id.tv_item_genre_two);
        textReleaseDate = findViewById(R.id.tv_release_date_detail);
        textRuntime = findViewById(R.id.tv_runtime_detail);
        textLanguage = findViewById(R.id.tv_language_detail);
        fabPlayButton = findViewById(R.id.fab_play_detail);
        textLabelRuntime = findViewById(R.id.text_runtime_detail);
        textLabelLanguage = findViewById(R.id.text_language_detail);

        DetailViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        MovieEntity movies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TvShowEntity tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        if (movies != null && tvShow == null) {
            viewModel.setSelectedMovie(movies.getId());
            initViewMovies(viewModel.getMovie());
            playTrailer(viewModel.getMovie().getKeyTrailer());
        } else if (tvShow != null && movies == null) {
            viewModel.setSelectedTvShow(tvShow.getId());
            initViewTvShow(viewModel.getTvShow());
            playTrailer(viewModel.getTvShow().getKeyTrailer());
        } else {
            System.out.println("Data not found");
        }

    }

    void initViewMovies(MovieEntity movies) {
        textOverview.setText(movies.getOverview());
        textTitle.setText(movies.getTitle());
        textVoteAverage.setText(movies.getVoteAverage());
        textGenreOne.setText(movies.getGenreOne());
        textGenreTwo.setText(movies.getGenreTwo());
        textReleaseDate.setText(movies.getReleaseDate());
        textRuntime.setText(movies.getRuntime());
        textLanguage.setText(movies.getSpokenLanguages());
        textLabelRuntime.setText(R.string.text_runtime_label);
        textLabelLanguage.setText(R.string.text_language_label);


        Glide.with(this)
                .load(urlImage+movies.getBackdropPath())
                .into(imageBackdrop);
        Glide.with(this)
                .load(urlImage+movies.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error).transform(new CenterCrop(), new RoundedCorners(50)))
                .into(imagePoster);
    }

    void initViewTvShow(TvShowEntity tvShow) {
        textOverview.setText(tvShow.getOverview());
        textTitle.setText(tvShow.getName());
        textVoteAverage.setText(tvShow.getVoteAverage());
        textGenreOne.setText(tvShow.getGenreOne());
        textGenreTwo.setText(tvShow.getGenreTwo());
        textReleaseDate.setText(tvShow.getFirstAirDate());
        textRuntime.setText(tvShow.getNumberOfSeasons());
        textLanguage.setText(tvShow.getNumberOfEpisodes());
        textLabelRuntime.setText(R.string.text_season_label);
        textLabelLanguage.setText(R.string.text_episode_label);


        Glide.with(this)
                .load(urlImage+tvShow.getBackdropPath())
                .into(imageBackdrop);
        Glide.with(this)
                .load(urlImage+tvShow.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error).transform(new CenterCrop(), new RoundedCorners(50)))
                .into(imagePoster);
    }

    void playTrailer(String keyTrailer) {
        fabPlayButton.setClickable(true);
        fabPlayButton.setOnClickListener(v -> {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+keyTrailer));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+ keyTrailer));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException e) {
                startActivity(webIntent);
            }
        });
    }

}
