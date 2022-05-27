package com.example.recycleview.recycle;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.adapter.UserAdapter;
import com.example.recycleview.model.User;
import com.example.recycleview.model.UserDataSource;

import java.util.List;

/**
 * 上拉加载
 */
public class RecycleViewPullUpObserver implements LifecycleObserver {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context context;

    public RecycleViewPullUpObserver(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        userAdapter = new UserAdapter(recyclerView);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        userAdapter.setData(UserDataSource.dataSource.subList(0,20));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            // dx 横向滚动  dx > 0 手指向左滑动，dx < 0 手指向右滑动
            // dy 纵向滚动  dy > 0 手指向上滑动，dy < 0 手指向下滑动
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    // Adapter中的item数量
                    int totalItemCount = linearLayoutManager.getItemCount();
                    // Adapter中可见的item数量
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    // Adapter中第一个可见视图在的位置
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if(visibleItemCount + firstVisibleItemPosition >= totalItemCount){
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
                            List<User> userList = UserDataSource.dataSource.subList(index,subIndex);
                            userAdapter.setDataToTail(userList);
                        }
                    }
                }
            }
        });
    }
}
