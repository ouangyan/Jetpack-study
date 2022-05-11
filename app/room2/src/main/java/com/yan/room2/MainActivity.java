package com.yan.room2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        studentViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(StudentViewModel.class);
        studentViewModel.queryStudentAllLive().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setDataSource(students);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void insertStudent(View view) {
        Student s1 = new Student("Jordan",59);
        Student s2 = new Student("Kobe",44);
        studentViewModel.insertStudent(s1,s2);
    }

    public void deleteStudent(View view) {
        Student s1 = new Student(21);
        studentViewModel.deleteStudent(s1);
    }

    public void updateStudent(View view) {
        Student s1 = new Student(22,"LeBron",38);
        studentViewModel.updateStudent(s1);
    }

    public void deleteAllStudent(View view) {
        studentViewModel.deleteAllStudent();
    }
}