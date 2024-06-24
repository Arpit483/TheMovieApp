package com.example.themovieapp.model;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.themovieapp.R;
import com.example.themovieapp.databinding.ActivityMainBinding;
import com.example.themovieapp.view.MovieAdapter;
import com.example.themovieapp.viewmodal.MainActivityViewModal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private MainActivityViewModal viewModal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        viewModal = new ViewModelProvider(this)
                .get(MainActivityViewModal.class);

        getPopularMovie();

        swipeRefreshLayout = binding.swipelayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovie();

            }
        });

    }

    private void getPopularMovie() {

        viewModal.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies =(ArrayList<Movie>) moviesFromLiveData;
                displayMoviesInRecyclerView();

            }
        });

    }

    private void displayMoviesInRecyclerView() {
        recyclerView = binding.recyclerView;
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        movieAdapter.notifyDataSetChanged();

    }
}