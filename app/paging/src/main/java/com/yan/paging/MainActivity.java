package com.yan.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.yan.paging.adapter.MoviePagedListAdapter;
import com.yan.paging.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MoviePagedListAdapter moviePagedListAdapter = new MoviePagedListAdapter(this);
        recyclerView.setAdapter(moviePagedListAdapter);

        MovieViewModel movieViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MovieViewModel.class);
        movieViewModel.moviePagedListLiveData.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                moviePagedListAdapter.submitList(movies);
            }
        });
    }
}