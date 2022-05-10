package com.yan.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.yan.databinding.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding dataBindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);
        Idol idol = new Idol("迈克尔.乔丹","五星");
        dataBindingBinding.setIdol(idol);
        dataBindingBinding.setHandleEventListenter(new HandlerEventListener(this));
    }
}