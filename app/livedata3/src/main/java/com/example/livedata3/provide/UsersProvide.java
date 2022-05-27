package com.example.livedata3.provide;

import com.example.livedata3.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UsersProvide {
    private static List<UserModel> dataSource = new ArrayList<>();

    static {
        initUsers();
    }

    private static void initUsers(){
        UserModel userModel = null;
        for(int i=0;i<100;i++){
            userModel = new UserModel();
            userModel.id = i+1;
            userModel.name = "Jordan"+(i+1);
            userModel.address = "黄埔区广新路"+(i+1)+"号";
            dataSource.add(userModel);
        }
    }

    public static List<UserModel> getUsers(int start,int count){
        List<UserModel> userModels = dataSource.subList(start,start+count);
        return userModels;
    }
}
