package com.yan.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWork extends Worker {
    public MyWork(Context context,WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        Data inputData = getInputData();
        String input_data = inputData.getString("input_data");
        Log.i("1qaz2wsx","input_data:"+input_data);

        // 执行成功返回结果
        Data outputData = new Data
                .Builder()
                .putString("output_data","Kobe And LeBron")
                .build();
        return Result.success(outputData);
    }
}
