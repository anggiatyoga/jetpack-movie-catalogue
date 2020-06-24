package com.anggiat.jetpackmoviecatalogue.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity.urlImage;

public class FavoriteMovieAdapter extends PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    FavoriteMovieAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public FavoriteMovieAdapter.FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.FavoriteMovieViewHolder holder, int position) {
        MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        }
    }

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvGenreOne;
        final TextView tvGenreTwo;
        final TextView tvRuntime;
        final TextView tvLanguage;
        final TextView tvVoteAverage;
        final ImageView imgPoster;

        FavoriteMovieViewHolder(@NonNull View itemView) {
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
