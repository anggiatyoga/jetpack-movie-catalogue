package com.anggiat.jetpackmoviecatalogue.ui.favorite.tvshow;

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
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity.urlImage;

public class FavoriteTvShowAdapter extends PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.FavoriteTvShowViewHolder> {

    FavoriteTvShowAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TvShowEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public FavoriteTvShowAdapter.FavoriteTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show, parent, false);
        return new FavoriteTvShowViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvShowAdapter.FavoriteTvShowViewHolder holder, int position) {
        TvShowEntity tvShow = getItem(position);
        if (tvShow != null) {
            holder.bind(tvShow);
        }
    }

    class FavoriteTvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvGenreOne;
        final TextView tvGenreTwo;
        final TextView tvRuntime;
        final TextView tvLanguage;
        final TextView tvVoteAverage;
        final ImageView imgPoster;

        FavoriteTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvGenreOne = itemView.findViewById(R.id.tv_item_genre_one);
            tvGenreTwo = itemView.findViewById(R.id.tv_item_genre_two);
            tvRuntime = itemView.findViewById(R.id.tv_runtime_detail);
            tvLanguage = itemView.findViewById(R.id.tv_language_detail);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_vote_average);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(TvShowEntity tvShow) {
            tvTitle.setText(tvShow.getName());
            tvGenreOne.setText(tvShow.getGenreOne());
            tvGenreTwo.setText(tvShow.getGenreTwo());
            tvRuntime.setText(tvShow.getNumberOfSeasons());
            tvLanguage.setText(tvShow.getNumberOfEpisodes());
            tvVoteAverage.setText(tvShow.getVoteAverage());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TV_SHOW, tvShow);
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(urlImage+tvShow.getPosterPath())
                    .fitCenter()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }

    }
}
