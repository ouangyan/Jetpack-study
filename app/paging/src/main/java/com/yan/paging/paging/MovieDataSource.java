package com.yan.paging.paging;

import android.util.Log;
import androidx.paging.PositionalDataSource;
import com.yan.paging.Movie;
import com.yan.paging.Movies;
import com.yan.paging.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PositionalDataSource<Movie> {
    // 每页显示的数据量
    public static final int count = 10;
    /**
     * 初始化加载
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(PositionalDataSource.LoadInitialParams params,PositionalDataSource.LoadInitialCallback callback) {
        int start = 0;
        RetrofitClient.getInstance().getApi()
            .getMovies(start,count)
            .enqueue(new Callback<Movies>() {
                @Override
                public void onResponse(Call<Movies> call, Response<Movies> response) {
                    if(response.body() != null && response.isSuccessful()){
                        Movies movies = response.body();
                        callback.onResult(movies.moiveList,movies.start,movies.total);
                        Log.i("1qaz2wsx","loadInitial:"+movies.moiveList);
                    }
                }

                @Override
                public void onFailure(Call<Movies> call, Throwable t) {

                }
            });
    }

    /**
     * 下一页加载
     * @param params
     * @param callback
     */
    @Override
    public void loadRange(PositionalDataSource.LoadRangeParams params,PositionalDataSource.LoadRangeCallback callback) {
        RetrofitClient.getInstance().getApi()
            .getMovies(params.startPosition,count)
            .enqueue(new Callback<Movies>() {
                @Override
                public void onResponse(Call<Movies> call, Response<Movies> response) {
                    if(response.body() != null && response.isSuccessful()){
                        callback.onResult(response.body().moiveList);
                        Log.i("1qaz2wsx","loadRange:"+response.body().moiveList);
                    }
                }

                @Override
                public void onFailure(Call<Movies> call, Throwable t) {

                }
            });
    }
}
