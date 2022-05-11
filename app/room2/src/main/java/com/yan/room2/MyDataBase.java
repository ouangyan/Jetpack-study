package com.yan.room2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 必须是抽象类
 */
@Database(entities = {Student.class}, version = 2, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_db.db";
    public static MyDataBase mInstance;

    public static synchronized MyDataBase getInstance(Context context){
        if(mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration() // 数据库升级过程中失败，保证程序正常运行，但会清空所有数据
                    .build();
        return mInstance;
    }

    // 数据库升级  参数1：对应当前版本  参数2：对应升级后的版本
    static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN sex INTEGER NOT NULL DEFAULT 1");
        }
    };

    /**
     * room自动实现
     * @return
     */
    public abstract StudentDao getStudentDao();
}
