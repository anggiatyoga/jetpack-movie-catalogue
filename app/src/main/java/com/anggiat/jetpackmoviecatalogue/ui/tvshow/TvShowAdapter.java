package com.anggiat.jetpackmoviecatalogue.ui.tvshow;

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
import com.anggiat.jetpackmoviecatalogue.data.source.local.entity.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private List<TvShowEntity> tvShowEntitiesList = new ArrayList<>();
    private static final String urlImage = BuildConfig.URL_IMAGE;

    void setTvShow(List<TvShowEntity> tvShowEntitiesList) {
        if (tvShowEntitiesList == null) return;
        this.tvShowEntitiesList.clear();
        this.tvShowEntitiesList.addAll(tvShowEntitiesList);
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, int position) {
        TvShowEntity tvShow = tvShowEntitiesList.get(position);
        holder.bind(tvShow);
    }

    @Override
    public int getItemCount() {
        return tvShowEntitiesList.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvGenreOne;
        final TextView tvGenreTwo;
        final TextView tvRuntime;
        final TextView tvLanguage;
        final TextView tvVoteAverage;
        final ImageView imgPoster;

        TvShowViewHolder(@NonNull View itemView) {
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
