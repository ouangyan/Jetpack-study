package com.yan.room2;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentRepository {
    private StudentDao studentDao;

    public StudentRepository(Context context) {
        MyDataBase myDataBase = MyDataBase.getInstance(context);
        this.studentDao = myDataBase.getStudentDao();
    }

    public void insertStudent(Student... students){
        Observable.just(students)
            .map(new Function<Student[], Object>() {
                @Override
                public Object apply(Student[] students) throws Throwable {
                    studentDao.insertStudent(students);
                    return students;
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Throwable {

                }
            });
    }

    public LiveData<List<Student>> queryAllStudentLive(){
        return studentDao.queryAllStudentLive();
    }

    public void deleteAllStudent(){
        Observable.just("")
            .map(new Function<Object, Object>() {
                @Override
                public Object apply(Object o) throws Throwable {
                    studentDao.deleteAllStudent();
                    return o;
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Throwable {

                }
            });
    }

    public void updateStudent(Student... students){
        Observable.just(students)
            .map(new Function<Student[], Object>() {
                @Override
                public Object apply(Student[] students) throws Throwable {
                    studentDao.updateStudent(students);
                    return students;
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Throwable {

                }
            });
    }

    public void deleteStudent(Student... students){
        Observable.just(students)
            .map(new Function<Student[], Object>() {
                @Override
                public Object apply(Student[] students) throws Throwable {
                    studentDao.deleteStudent(students);
                    return students;
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Throwable {

                }
            });
    }
}
