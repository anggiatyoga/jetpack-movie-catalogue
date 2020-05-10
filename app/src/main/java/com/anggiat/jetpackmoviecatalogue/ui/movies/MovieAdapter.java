package com.anggiat.jetpackmoviecatalogue.ui.movies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anggiat.jetpackmoviecatalogue.BuildConfig;
import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieEntity> movieEntityList = new ArrayList<>();
    private static final String urlImage = BuildConfig.URL_IMAGE;

    void setMovies(List<MovieEntity> movieEntityList) {
        if (movieEntityList == null) return;
        this.movieEntityList.clear();
        this.movieEntityList.addAll(movieEntityList);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        MovieEntity movie = movieEntityList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieEntityList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvGenreOne;
        final TextView tvGenreTwo;
        final TextView tvRuntime;
        final TextView tvLanguage;
        final TextView tvVoteAverage;
        final ImageView imgPoster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvGenreOne = itemView.findViewById(R.id.tv_item_genre_one);
            tvGenreTwo = itemView.findViewById(R.id.tv_item_genre_two);
            tvRuntime = itemView.findViewById(R.id.tv_runtime_detail);
            tvLanguage = itemView.findViewById(R.id.tv_language_detail);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_vote_average);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(MovieEntity movie) {
            tvTitle.setText(movie.getTitle());
            tvGenreOne.setText(movie.getGenreOne());
            tvGenreTwo.setText(movie.getGenreTwo());
            tvRuntime.setText(movie.getRuntime());
            tvLanguage.setText(movie.getSpokenLanguages());
            tvVoteAverage.setText(movie.getVoteAverage());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(urlImage+movie.getPosterPath())
                    .fitCenter()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }

    }
}
