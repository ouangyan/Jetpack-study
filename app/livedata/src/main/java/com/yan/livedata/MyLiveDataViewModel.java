package com.yan.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyLiveDataViewModel extends ViewModel {
    private MutableLiveData<Integer> currentSecond;
    public MutableLiveData<Integer> getCurrentSecond(){
        if(currentSecond==null) {
            currentSecond = new MutableLiveData<>();
            currentSecond.setValue(0);
        }
        return currentSecond;
    }
}
