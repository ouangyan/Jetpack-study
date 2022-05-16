package com.yan.paging.api;

import com.yan.paging.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/")
    Call<Movies> getMovies(@Query("start") int start, @Query("count") int count);
}
