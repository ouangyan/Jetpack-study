package com.yan.paging.paging;

import androidx.paging.DataSource;
import com.yan.paging.Movie;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {
    @Override
    public DataSource<Integer, Movie> create() {
        return new MovieDataSource();
    }
}
