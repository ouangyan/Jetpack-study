package com.yan.databinding4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yan.databinding4.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(generateData());
        activityMainBinding.recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView1.setAdapter(recyclerViewAdapter);
    }

    private List<User> generateData(){
        List<User> userList = new ArrayList<>();
        Random random = new Random();
        User user = null;
        for(int i=0;i<20;i++){
            user = new User();
            int num = random.nextInt(38);
            user.name = "Air Jordan"+num;
            user.cnName = "乔丹"+num;
            userList.add(user);
        }
        return userList;
    }
}