package com.architecture.movies.ui.movie;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.movies.R;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.databinding.MovieListItemBinding;
import com.architecture.movies.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity> {

    private List<MovieEntity> movieList;
    private final MovieListCallback movieListCallback;

    public MovieListAdapter(MovieListCallback movieListCallback) {
        this.movieListCallback = movieListCallback;
        movieList = new ArrayList<>();
    }

    @Override
    public void setData(List<MovieEntity> movieEntities) {
        this.movieList = movieEntities;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MovieViewHolder.create(LayoutInflater.from(parent.getContext()), parent, movieListCallback);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.onBind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        MovieListItemBinding binding;

        public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent, MovieListCallback callback) {
            MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, parent, false);
            return new MovieViewHolder(movieListItemBinding, callback);
        }

        public MovieViewHolder(MovieListItemBinding binding, MovieListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            int position = this.getLayoutPosition();
            binding.getRoot().setOnClickListener(v -> callback.onMovieClicked(binding.getMovie(), binding.movieTitle));
        }

        public void onBind(MovieEntity movieEntity) {
            binding.setMovie(movieEntity);
            binding.executePendingBindings();
        }
    }
}
