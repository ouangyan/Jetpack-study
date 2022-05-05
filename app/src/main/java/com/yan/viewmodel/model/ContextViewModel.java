package com.yan.viewmodel.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * 支持Context的ViewModel
 */
public class ContextViewModel extends AndroidViewModel {
    private Application application;

    public ContextViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public int number;
}
