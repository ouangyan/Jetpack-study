package com.yan.paging.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.yan.paging.Movie;
import com.yan.paging.paging.MovieDataSource;
import com.yan.paging.paging.MovieDataSourceFactory;

public class MovieViewModel extends ViewModel {
    public LiveData<PagedList<Movie>> moviePagedListLiveData;
    public MovieViewModel() {
        PagedList.Config config = new PagedList.Config.Builder()
                // 设置控件占位
                .setEnablePlaceholders(false)
                .setMaxSize(66536 * MovieDataSource.count)
                .setPageSize(MovieDataSource.count)
                // 设置距离底部还有多少条数据时开始加载下一页
                .setPrefetchDistance(3)
                // 设置首次加载的数量
                .setInitialLoadSizeHint(MovieDataSource.count * 2)
                .build();

        moviePagedListLiveData = new LivePagedListBuilder<>(new MovieDataSourceFactory(), config).build();
    }
}
