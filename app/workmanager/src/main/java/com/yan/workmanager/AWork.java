package com.yan.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class AWork extends Worker {
    public AWork(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public ListenableWorker.Result doWork() {
        Log.i("1qaz2wsx",this.getClass().getSimpleName()+">>>doWork()");
        return Result.success();
    }
}
