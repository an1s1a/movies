package com.architecture.movies.ui.movie;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.movies.R;
import com.architecture.movies.data.Resource;
import com.architecture.movies.data.local.entity.MovieEntity;
import com.architecture.movies.databinding.MovieListFragmentBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MovieListFragment extends Fragment implements LifecycleOwner, MovieListCallback {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MovieViewModel viewModel;
    MovieListFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(new MovieListAdapter(this));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);

        LiveData<Resource<List<MovieEntity>>> movieList = viewModel.getMovies();
        movieList.observe(this, resource -> binding.setResource(resource));
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return getActivity().getLifecycle();
    }


    @Override
    public void onMovieClicked(MovieEntity movieEntity, View sharedView) {
        int id = movieEntity.getId();
        startActivity(MovieDetailActivity.newIntent(getActivity(), id));
    }
}
