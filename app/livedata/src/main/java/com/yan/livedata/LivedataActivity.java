package com.yan.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LivedataActivity extends AppCompatActivity {

    private TextView textView;
    private MyLiveDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livedata);
        textView = findViewById(R.id.text1);
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyLiveDataViewModel.class);
        textView.setText(String.valueOf(viewModel.getCurrentSecond().getValue()));
        viewModel.getCurrentSecond().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                textView.setText(String.valueOf(i));
            }
        });
        startTimer();
    }

    private void startTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                viewModel.getCurrentSecond().postValue(viewModel.getCurrentSecond().getValue()+1);
            }
        },1000,1000);
    }


}