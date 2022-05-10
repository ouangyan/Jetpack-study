package com.yan.databinding3;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.ObservableField;

public class UserViewModel {
    private ObservableField<User> userObservableField;

    public UserViewModel() {
        User user = new User("Jack");
        userObservableField = new ObservableField<>();
        userObservableField.set(user);
    }

    public void setUserName(String name){
        if(!TextUtils.isEmpty(name) && !name.equals(userObservableField.get().name)){
            Log.i("1qaz2wsx",name);
            userObservableField.get().name = name;
        }
    }

    public String getUserName(){
        return userObservableField.get().name;
    }
}
