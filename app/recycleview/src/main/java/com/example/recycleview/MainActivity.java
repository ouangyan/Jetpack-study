package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.example.recycleview.recycle.RecycleViewGlideDownObserver;
import com.example.recycleview.recycle.RecycleViewPullUpObserver;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
//        getLifecycle().addObserver(new RecycleViewPullUpObserver(this,recyclerView));
        getLifecycle().addObserver(new RecycleViewGlideDownObserver(this,recyclerView));
    }
}