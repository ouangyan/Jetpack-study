package com.example.recycleview.recycle;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recycleview.R;
import com.example.recycleview.adapter.UserAdapter;
import com.example.recycleview.model.User;
import com.example.recycleview.model.UserDataSource;

import java.util.List;

/**
 * 下滑加载
 */
public class RecycleViewGlideDownObserver implements LifecycleObserver {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;

    public RecycleViewGlideDownObserver(Context context, RecyclerView recyclerView,SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
        userAdapter = new UserAdapter(recyclerView);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        slideBind();
        slideDownLoadingAnimation();
    }

    private void slideBind(){
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        userAdapter.setData(UserDataSource.dataSource.subList(50,70));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // newState==0 没有滚动   newState==1 正在滚动  newState==2 自动滚动
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!recyclerView.canScrollVertically(1)){ // 上拉
                    User user = userAdapter.getLastUser();
                    int index = UserDataSource.dataSource.indexOf(user);
                    // 过滤前一页最后一条
                    index += 1;
                    int subIndex = 0;
                    if(UserDataSource.dataSource.size()>=index+20){
                        subIndex = index+20;
                    }else{
                        subIndex = UserDataSource.dataSource.size();
                    }
                    if(UserDataSource.dataSource.size()>index){
                        Log.i("1qaz2wsx","上拉加载数据");
                        List<User> userList = UserDataSource.dataSource.subList(index,subIndex);
                        userAdapter.setDataToTail(userList);
                    }
                }/*else if(!recyclerView.canScrollVertically(-1) && newState == 1){ // 下滑且是正在滚动中
                    User user = userAdapter.getFirstUser();
                    int index = UserDataSource.dataSource.indexOf(user);
                    int subIndex = -1;
                    if(index>=0 && index-20>=0){
                        subIndex = index-20;
                    }else if(index>=0 && index-20<0){
                        subIndex = 0;
                    }

                    if(subIndex>=0 && index-1>0){
                        Log.i("1qaz2wsx","下滑加载数据");
                        List<User> userList = UserDataSource.dataSource.subList(subIndex,index);
                        userAdapter.setDataToHead(userList);
                    }
                }*/
            }

            // dx 横向滚动  dx > 0 手指向左滑动，dx < 0 手指向右滑动
            // dy 纵向滚动  dy > 0 手指向上滑动，dy < 0 手指向下滑动
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 下滑加载带动画效果
     */
    private void slideDownLoadingAnimation(){
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                User user = userAdapter.getFirstUser();
                int index = UserDataSource.dataSource.indexOf(user);
                int subIndex = -1;
                if(index>=0 && index-20>=0){
                    subIndex = index-20;
                }else if(index>=0 && index-20<0){
                    subIndex = 0;
                }

                if(subIndex>=0 && index-1>0){
                    List<User> userList = UserDataSource.dataSource.subList(subIndex,index);
                    userAdapter.setDataToHead(userList);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
