package com.yan.room2;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;

    public StudentViewModel(Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public void insertStudent(Student... students){
        studentRepository.insertStudent(students);
    }

    public LiveData<List<Student>> queryStudentAllLive(){
        return studentRepository.queryAllStudentLive();
    }

    public void deleteAllStudent(){
        studentRepository.deleteAllStudent();
    }

    public void updateStudent(Student... students){
        studentRepository.updateStudent(students);
    }

    public void deleteStudent(Student... students){
        studentRepository.deleteStudent(students);
    }
}
