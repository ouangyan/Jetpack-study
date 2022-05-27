package com.example.livedata3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.livedata3.model.UserModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    public MutableLiveData<List<UserModel>> userMutableLiveData;

    public UserViewModel() {
        if(userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }
    }

    public void getUsers(){

    }
}
