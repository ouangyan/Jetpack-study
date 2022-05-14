package com.yan.workmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.time.Duration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startWork();
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startWork(){
        // 满足条件才会执行
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        // 配置参数
        Data data = new Data
                .Builder()
                .putString("input_data","Jordan")
                .build();

        // 配置任务
        // 一次性执行的任务
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(MyWork.class)
                // 触发条件
                .setConstraints(constraints)
                // 指数退避策略  俗称执行失败重试策略
                .setBackoffCriteria(BackoffPolicy.LINEAR, Duration.ofSeconds(3))
                // 延迟执行
                .setInitialDelay(Duration.ofSeconds(5))
                // 设置Tag标签
                .addTag("workRequest")
                .setInputData(data)
                .build();
        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(workRequest);

//         通过id监听任务变化
        workManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                // 获取执行后的成功结果
                if(workInfo!=null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
                    String output_data = workInfo.getOutputData().getString("output_data");
                    Log.i("1qaz2wsx","output_data:"+output_data);
                }
            }
        });

//        // 周期性任务
//        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest
//                .Builder(MyWork.class, Duration.ofMinutes(5))
//                .setInputData(data)
//                .build();
//        WorkManager workManager = WorkManager.getInstance(this);
//        workManager.enqueueUniquePeriodicWork("periodicWorkRequest",ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
//
//        // 通过id监听任务变化
//        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                // 获取执行后的成功结果
//                if(workInfo!=null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
//                    String output_data = workInfo.getOutputData().getString("output_data");
//                    Log.i("1qaz2wsx","output_data:"+output_data);
//                }
//            }
//        });

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // 取消任务
//                workManager.cancelWorkById(workRequest.getId());
//            }
//        },2000);
    }
}