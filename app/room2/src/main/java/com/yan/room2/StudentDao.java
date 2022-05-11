package com.yan.room2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student... students);

    @Delete
    void deleteStudent(Student... students);

    @Query("DELETE FROM student")
    void deleteAllStudent();

    @Update
    void updateStudent(Student... students);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> queryAllStudentLive();

    @Query("SELECT * FROM student WHERE id = :id")
    Student queryStudentById(int id);
}
