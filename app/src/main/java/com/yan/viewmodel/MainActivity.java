package com.yan.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yan.viewmodel.model.MyViewModel;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        textView.setText(String.valueOf(viewModel.number));
    }

    public void plusNumber(View view) {
        textView.setText(String.valueOf(++viewModel.number));
    }
}