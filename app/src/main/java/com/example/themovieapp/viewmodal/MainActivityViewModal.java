package com.example.themovieapp.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.themovieapp.model.Movie;
import com.example.themovieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModal extends AndroidViewModel {

private MovieRepository repository;

    public MainActivityViewModal(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    //Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }
}
