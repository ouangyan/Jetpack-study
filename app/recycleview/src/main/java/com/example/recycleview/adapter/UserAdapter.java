package com.example.recycleview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleview.R;
import com.example.recycleview.model.User;
import com.example.recycleview.model.UserDataSource;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolderView> {
    private List<User> dataSource = new ArrayList<>();
    private RecyclerView recyclerView;

    public UserAdapter(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    public void setData(List<User> users){
        dataSource.addAll(users);
        notifyDataSetChanged();
    }

    public void setDataToHead(List<User> users){
        User prePageFirstUser = dataSource.get(0);
        for(int i=users.size()-1; i>=0; i--){
            User user = users.get(i);
            dataSource.add(0,user);
        }
        int index = dataSource.indexOf(prePageFirstUser);
        notifyDataSetChanged();
        // 平缓滚动到上一页结束位置
        recyclerView.scrollToPosition(index);
    }

    public void setDataToTail(List<User> users){
        for(User user:users){
            dataSource.add(user);
        }
        notifyDataSetChanged();
    }

    public User getLastUser(){
        User user = dataSource.get(dataSource.size()-1);
        return user;
    }

    public User getFirstUser(){
        User user = dataSource.get(0);
        return user;
    }

    @Override
    public UserAdapter.UserHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new UserHolderView(root);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserHolderView holder, int position) {
        User user = dataSource.get(position);
        holder.textView.setText(user.name);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class UserHolderView extends RecyclerView.ViewHolder {
        private TextView textView;
        public UserHolderView(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
