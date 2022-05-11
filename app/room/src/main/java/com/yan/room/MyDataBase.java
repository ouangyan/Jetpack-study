package com.yan.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 必须是抽象类
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_db.db";
    public static MyDataBase mInstance;

    public static synchronized MyDataBase getInstance(Context context){
        if(mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME)
                    .build();
        return mInstance;
    }

    /**
     * room自动实现
     * @return
     */
    public abstract StudentDao getStudentDao();
}
