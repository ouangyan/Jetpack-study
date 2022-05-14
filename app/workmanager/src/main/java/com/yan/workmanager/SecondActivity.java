package com.yan.workmanager;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class SecondActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startWork();
    }

    /**
     * 执行多个任务
     */
    private void startWork() {
        OneTimeWorkRequest aWork = new OneTimeWorkRequest.Builder(AWork.class).build();
        OneTimeWorkRequest bWork = new OneTimeWorkRequest.Builder(BWork.class).build();

        WorkManager.getInstance(this)
                .beginWith(aWork)
                .then(bWork)
                .enqueue();
    }
}
