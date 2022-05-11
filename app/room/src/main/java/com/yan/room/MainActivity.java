package com.yan.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grant();

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        studentDao = MyDataBase.getInstance(this).getStudentDao();
    }

    private void grant(){
        if (Build.VERSION.SDK_INT>=23 &&
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    public void insertStudent(View view) {
        Student s1 = new Student("Jordan",59);
        new InsertStudentTask(studentDao).execute(s1);
    }

    class InsertStudentTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;
        public InsertStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    public void deleteStudent(View view) {

    }

    public void updateStudent(View view) {

    }

    public void queryStudent(View view) {
        new QueryStudentTask(studentDao).execute();
    }

    class QueryStudentTask extends AsyncTask<Void,Void,Void>{
        private StudentDao studentDao;

        public QueryStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Student> studentList = studentDao.queryAllStudent();
            adapter.setDataSource(studentList);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}