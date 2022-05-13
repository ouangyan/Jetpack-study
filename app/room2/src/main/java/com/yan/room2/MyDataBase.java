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
@Database(entities = {Student.class}, version = 3, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_db.db";
    public static MyDataBase mInstance;

    public static synchronized MyDataBase getInstance(Context context){
        if(mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
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

    // 表的销毁和重建策略  场景：更改表字段类型  1.创建临时表  2.复制数据到临时表  3.删除原先表  4.重命名临时表
    static Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // 创建临时表
            database.execSQL("CREATE TABLE temp_student(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name TEXT," +
                    "age INTEGER NOT NULL," +
                    "sex TEXT DEFAULT 'M')");

            // 复制数据到临时表
            database.execSQL("INSERT INTO temp_student (name,age,sex)"  +
                    "SELECT name,age,sex from student");

            // 删除原先表
            database.execSQL("DROP TABLE student");

            // 重命名临时表
            database.execSQL("ALTER TABLE temp_student RENAME TO student");
        }
    };

    /**
     * room自动实现
     * @return
     */
    public abstract StudentDao getStudentDao();
}
