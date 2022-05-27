package com.example.recycleview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataSource {
    public static List<User> dataSource = new ArrayList<>();
    static{
        for(int i=0;i<110;i++){
            User user = new User();
            user.id = i+1;
            user.name = "Michael.Jordan"+(i+1);
            dataSource.add(user);
        }
    }
}
